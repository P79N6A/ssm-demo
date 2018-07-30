package com.demo.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 从fileChannel读取数据到buffer
 *
 * @author fangcong on 2018/7/13.
 */
public class FileChannelToBuffer {

    public static void main(String[] args) throws Exception {
        //获取channel
        RandomAccessFile randomAccessFile = new RandomAccessFile("F:/README.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        int bytesRead;
        //读取到buffer
        while ((bytesRead = fileChannel.read(byteBuffer)) != -1) {
            System.out.println("Read:" + bytesRead);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char)byteBuffer.get());
            }
            byteBuffer.clear();
        }
        randomAccessFile.close();
    }
}
