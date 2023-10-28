package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sender {

    private static final String USERNAME = "dist_user";
    private static final String PASSWORD = "dist_pass_123";
    private static final String SENDER_NAME = "Zaur";


    public static void main(String[] args) {
        List<String> ipOfDatabases = List.of("192.168.1.1", "192.168.1.2");
        List<Thread> threads = new ArrayList<>();





    }

    public static class Thread implements Runnable {
        private final String ipOfDatabase;

        public Thread(String ipOfDatabase) {
            this.ipOfDatabase = ipOfDatabase;
        }

        @Override
        public void run() {

        }
    }
}
