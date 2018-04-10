package com.demo.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO改进
 *
 * @author fangcong on 2018/4/9.
 */
public class MultiThreadEchoServer {

    private Selector selector;

    private static ExecutorService service = Executors.newCachedThreadPool();

    public static Map<Socket, Long> timeMap = new HashMap<>(10240);

    class HandlerMsg implements Runnable {

        SelectionKey selectionKey;
        ByteBuffer byteBuffer;

        public HandlerMsg(SelectionKey selectionKey, ByteBuffer byteBuffer) {
            this.selectionKey = selectionKey;
            this.byteBuffer = byteBuffer;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient)selectionKey.attachment();
            echoClient.enqueue(byteBuffer);
            selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }

    private void doAccept(SelectionKey selectionKey) {
        ServerSocketChannel ssk = (ServerSocketChannel)selectionKey.channel();
        SocketChannel channel;

        try {
            channel = ssk.accept();
            channel.configureBlocking(false);

            SelectionKey sk = channel.register(selector, SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            sk.attach(echoClient);

            InetAddress address = channel.socket().getInetAddress();
            System.out.println("connected from : " + address.getHostAddress());
        } catch (IOException e) {
            System.out.println("failed to accept new client");
            e.printStackTrace();
        }
    }

    public void doRead(SelectionKey sk) {
        SocketChannel socketChannel = (SocketChannel)sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try {
            len = socketChannel.read(bb);
            if (len < 0) {
                sk.cancel();
                return;
            }
        } catch (IOException e) {
            System.out.println("failed to read");
            e.printStackTrace();
            sk.cancel();
            return;
        }

        bb.flip();
        service.execute(new HandlerMsg(sk, bb));
    }

    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel)sk.channel();
        EchoClient client = (EchoClient)sk.attachment();
        LinkedList<ByteBuffer> queue = client.getOutQueue();

        ByteBuffer bb = queue.getLast();
        try {
            int len = channel.write(bb);
            if (len == -1) {
                sk.cancel();
                return;
            }
            if (bb.remaining() == 0) {
                queue.removeLast();
            }
        } catch (IOException e) {
            System.out.println("Fail to write");
            e.printStackTrace();
            sk.cancel();
            return;
        }

        if (queue.size() == 0) {
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    private void startServer() throws Exception {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress isa = new InetSocketAddress(8000);
        ssc.socket().bind(isa);

        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        for (;;) {
            selector = selectionKey.selector();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = readyKeys.iterator();
            long e;

            while (it.hasNext()) {
                SelectionKey sk = it.next();
                it.remove();

                Socket socket = ((SocketChannel)sk.channel()).socket();
                if (sk.isAcceptable()) {
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {
                    if (!timeMap.containsKey(socket)) {
                        timeMap.put(socket, System.currentTimeMillis());
                        doRead(sk);
                    }
                } else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = timeMap.remove(socket);
                    System.out.println("spend:" + (e - b) + "ms");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MultiThreadEchoServer echoServer = new MultiThreadEchoServer();
        echoServer.startServer();
    }

}

class EchoClient {
    private LinkedList<ByteBuffer> list;

    EchoClient() {
        list = new LinkedList<>();
    }

    public LinkedList<ByteBuffer> getOutQueue() {
        return list;
    }

    public void enqueue(ByteBuffer byteBuffer) {
        list.addFirst(byteBuffer);
    }
}
