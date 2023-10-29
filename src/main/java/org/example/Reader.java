package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private static final String USERNAME = "dist_user";     //username for dockerized database
    private static final String PASSWORD = "dist_pass_123";  //password for dockerized database

    public static void main(String[] args) {

        List<String> ipOfData = new ArrayList<>(); //list of ips
        ipOfData.add("127.0.0.1");
        //foreach
        for (String ip : ipOfData
        ) {
            new Thread(() -> {
                while (true) {

                    final String url = "jdbc:postgresql://" + ip + ":5432/postgres";   //url that helps us to make a connection with DB

                    try (Connection c = DriverManager.getConnection(url, USERNAME, PASSWORD)) {   //takes credentials to make connection
                        String select = "SELECT id, SENDER_NAME, MESSAGE, SENT_TIME FROM ASYNC_MESSAGE WHERE RECEIVED_TIME IS NULL AND SENDER_NAME != ? LIMIT 1 FOR UPDATE";   //String query that block record because of many readers

                        try (PreparedStatement preparedStatement = c.prepareStatement(select)) {
                            preparedStatement.setString(1, "ZAUR");
                            ResultSet data = preparedStatement.executeQuery();

                            if (data.next()) {
                                System.out.printf("Sender " + data.getString("SENDER_NAME") + " sent " + data.getString("MESSAGE") + " at time " + data.getTimestamp("SENT_TIME") + "%n"); //it prints message in format which is Sender XXX sent XXX at time XXXX.

                                String update = "UPDATE ASYNC_MESSAGE SET RECEIVED_TIME = CURRENT_TIMESTAMP WHERE id = ?";  //it sets received time = current time
                                try (PreparedStatement statementToUpdate = c.prepareStatement(update)) {
                                    statementToUpdate.setInt(1, data.getInt("id"));
                                    int updated = statementToUpdate.executeUpdate();

                                    if (updated > 0) {        //checks if it is updated
                                        System.out.print("Message is set with Current time!");
                                    } else {
                                        System.out.print("Unable to set current time!");
                                    }
                                }
                            }

                            Thread.sleep(3000);  //3s
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

