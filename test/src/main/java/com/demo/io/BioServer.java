package com.demo.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞io
 *
 * @author fangcong on 2018/3/21.
 */
public class BioServer {

    private static final int MAX_INPUT = 10;

    private static final int PORT = 8090;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = null;
            boolean flag = true;
            while (flag) {
                //阻塞等待连接
                socket = serverSocket.accept();
                System.out.println("连接成功...");
                new Thread(new Handler(socket)).start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Handler implements Runnable {

        final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    byte[] input = new byte[MAX_INPUT];
                    System.out.println("线程" + Thread.currentThread().getId() + "..等待接收...");
                    //阻塞等待数据读完
                    socket.getInputStream().read(input);
                    System.out.println("接收到数据");
                    process(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void process(byte[] input){
            String s = new String(input);
            System.out.println("process: " + s + "over!!!");
        }
    }
}
