package org.example;


import java.sql.*;
import java.util.List;

public class Reader {

    private static final String USERNAME = "dist_user";
    private static final String PASSWORD = "dist_pass_123";
    public static void main(String[] args) {

        List<String> ipOfDatabases = List.of("192.168.1.1", "192.168.1.2");


        for (String ip : ipOfDatabases
        ) {
            new Thread(() -> {
                while (true) {

                    final String url = "jdbc:postgresql://" + ip + ":5432/postgres";

                    try (Connection c = DriverManager.getConnection(url, USERNAME, PASSWORD)) {
                        String select = "SELECT id, SENDER_NAME, MESSAGE, SENT_TIME FROM ASYNC_MESSAGE WHERE RECEIVED_TIME IS NULL AND SENDER_NAME != ? LIMIT 1 FOR UPDATE";

                        try (PreparedStatement preparedStatement = c.prepareStatement(select)) {
                            preparedStatement.setString(1, "ZAUR");
                            ResultSet data = preparedStatement.executeQuery();

                            if (data.next()) {
                                System.out.printf("Sender " + data.getString("SENDER_NAME") + " sent " + data.getString("MESSAGE") + " at time " + data.getTimestamp("SENT_TIME"));

                                String update = "UPDATE ASYNC_MESSAGE SET RECEIVED_TIME = CURRENT_TIMESTAMP WHERE id = ?";
                                try (PreparedStatement statementToUpdate = c.prepareStatement(update)) {
                                    statementToUpdate.setInt(1, data.getInt("id"));
                                    int updated = statementToUpdate.executeUpdate();

                                    if (updated > 0) {
                                        System.out.print("Message is set with Current time!");
                                    } else {
                                        System.out.print("Unable to set current time!");
                                    }
                                }
                            }

                            Thread.sleep(3000);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

