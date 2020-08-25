package com.epam.anatolii.ageev.eshop.servers.client;

/**
 * Class to start client tread.
 */
public class TcpClientApp {
    public static void main(String[] args) {
        Thread clientThread = new Thread(new TcpClient());
        clientThread.start();
        try {
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
