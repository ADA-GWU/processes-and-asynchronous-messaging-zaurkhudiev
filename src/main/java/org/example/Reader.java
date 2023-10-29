package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Reader {

    private static final String USERNAME = "dist_user";
    private static final String PASSWORD = "dist_pass_123";
    private static final String SENDER_NAME = "Zaur";

    public static void main(String[] args) {

        List<String> ipOfDatabases = List.of("192.168.1.1", "192.168.1.2");


        for (String ip : ipOfDatabases
        ) {
            new Thread(() -> {
                while (true) {
                    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":5432/postgres", USERNAME, PASSWORD)) {


                    } catch (SQLException exception) {
                        throw new RuntimeException(exception);
                    }

                }

            }
        }
    }
    }
