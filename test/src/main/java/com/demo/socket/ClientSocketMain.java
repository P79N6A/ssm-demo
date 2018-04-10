package com.demo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author fangcong on 2018/4/9.
 */
public class ClientSocketMain {

    public static void main(String[] args) {
        Socket client = null;
        BufferedReader is = null;
        PrintWriter os = null;

        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost", 8000));
            os = new PrintWriter(client.getOutputStream(), true);
            os.println("hello");
            os.flush();

            is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server:" + is.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != is) {
                    is.close();
                }
                if (null != client) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
