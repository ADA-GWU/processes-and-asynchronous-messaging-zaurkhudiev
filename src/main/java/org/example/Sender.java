package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sender {

    private static final String USERNAME = "dist_user";
    private static final String PASSWORD = "dist_pass_123";
    private static final String SENDER_NAME = "Zaur";


    public static void main(String[] args) {

        System.out.println("Hi");

        List<String> ipOfDatabases = List.of("192.168.1.1", "192.168.1.2");
        List<ThreadClass> threads = new ArrayList<>();

        for (String ip : ipOfDatabases) {
            ThreadClass threadClass = new ThreadClass(ip);
            threads.add(threadClass);
            new Thread(threadClass).start();
        }



    }

    public static class ThreadClass implements Runnable {
        private final String ipOfDatabase;

        public ThreadClass(String ipOfDatabase) {
            this.ipOfDatabase = ipOfDatabase;
        }

        public void send(String name, String message) {
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://" + ipOfDatabase + ":5432/postgres", USERNAME, PASSWORD)) {
                String insert = "INSERT INTO ASYNC_MESSAGE(SENDER_NAME, MESSAGE, SENT_TIME) VALUES(?, ?, CURRENT_TIMESTAMP)";
                try (PreparedStatement s = connection.prepareStatement(insert)) {

                    s.setString(1, name);
                    s.setString(2, message);
                    s.executeUpdate();


                }
                catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public void run() {

        }
    }
}
