package com.epam.anatolii.ageev.eshop.servers.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import static com.epam.anatolii.ageev.eshop.constants.ServerConstants.TCP_SERVER_PORT;

/**
 * Class for client thread.
 */
public class TcpClient implements Runnable{

    @Override
    public void run() {
        try(Socket s = new Socket(InetAddress.getLocalHost(), TCP_SERVER_PORT);
            PrintStream ps = new PrintStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                System.out.println("Number if items in the shop - get count");
                System.out.println("Get name and price - get item=ID");

                Scanner scanner = new Scanner(System.in);
                String request = scanner.nextLine();

                ps.println(request);

                String result = br.readLine();

                System.out.println(result);

        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }

}
