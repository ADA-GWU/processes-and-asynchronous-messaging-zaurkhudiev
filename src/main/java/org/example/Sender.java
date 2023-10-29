package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sender {

    private static final String USERNAME = "dist_user";   // username for dockerized database
    private static final String PASSWORD = "dist_pass_123"; // password for dockerized database
    private static final String SENDER_NAME = "Zaur";  //my name - who sends the message


    public static void main(String[] args) {

        System.out.println("Hi");  //just checking if it works

        List<String> ipOfDatabases = List.of("192.168.1.1", "192.168.1.2"); //example ips
        List<ThreadClass> threads = new ArrayList<>();

        for (String ip : ipOfDatabases) {           //foreach that takes ips one by one
            ThreadClass threadClass = new ThreadClass(ip);
            threads.add(threadClass);
            new Thread(threadClass).start();
        }

        Scanner scan = new Scanner(System.in);


        boolean everythingOkay = true;
        String exit = "exit";

        while (everythingOkay) {                        //if it is true then user can write his message
            System.out.print("Enter your message: ");
            String message = scan.nextLine();
            if (exit.equalsIgnoreCase(message)) {           //if user writes exit as a input then, while takes false and the code is finished without any action

                everythingOkay = false;

            } else {                                        //else it chooses the thread

            int a = (int) (Math.random() * threads.size());
            ThreadClass thread1 = threads.get(a);
            thread1.send(SENDER_NAME, message);
        }
    }
    }

    public static class ThreadClass implements Runnable {
        private final String ipOfDatabase;

        public ThreadClass(String ipOfDatabase) {
            this.ipOfDatabase = ipOfDatabase;
        }

        public void send(String name, String senderMessage) {

            final String  url = "jdbc:postgresql://" + ipOfDatabase + ":5432/postgres";   //url that helps us to make a connection with DB

            try (Connection c = DriverManager.getConnection(url, USERNAME, PASSWORD)) {       //creates connection
                String insert = "INSERT INTO ASYNC_MESSAGE(SENDER_NAME, MESSAGE, SENT_TIME) VALUES(?, ?, CURRENT_TIMESTAMP)";  //it inserts message, sender name and current time to the async message table
                try (PreparedStatement s = c.prepareStatement(insert)) {

                    s.setString(1, name);
                    s.setString(2, senderMessage);
                    s.executeUpdate();

                    System.out.printf("Message " + senderMessage + "successfully inserted to " + ipOfDatabase + "!");




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
