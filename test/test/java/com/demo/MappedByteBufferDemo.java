package com.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author fangcong on 2017/8/22.
 */
public class MappedByteBufferDemo {

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件对象
     */
    private File file;

    private MappedByteBuffer mappedByteBuffer;

    private FileChannel fileChannel;

    private boolean bundSuccess=false;
    /**
     * 文件大小
     */
    private final static long MAX_FILE_SIZE = 1024*1024*50;
    /**
     * 文件写入位置
     */
    private long writePosition = 0;
    /**
     * 最后一次刷数据的时间
     */
    private long lastFlushTime;
    /**
     * 上一次刷的文件位置
     */
    private long lastFlushFilePosition=0;
    /**
     * 最大的脏数据量，系统必须触发一次强制刷
     */
    private long maxFlushDataSize = 1024*512;
    /**
     * 最大的时间间隔，系统必须触发一次强制刷
     */
    private long maxFlushTimeGap = 1000;

    public MappedByteBufferDemo(String fileName, String filePath) {
        super();
        this.fileName = fileName;
        this.filePath = filePath;
        this.file = new File(filePath+"/"+fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 内存映射文件绑定
     * @return
     */
    public synchronized boolean boundChannelToByteBuffer(){
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            this.fileChannel = raf.getChannel();
        } catch (Exception e) {
            e.printStackTrace();
            this.bundSuccess=false;
            return false;
        }

        try {
            this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, MAX_FILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
            this.bundSuccess=false;
            return false;
        }

        this.bundSuccess=true;
        return true;
    }

    public synchronized boolean appendData(byte[] data) throws Exception{
        if(!bundSuccess){
            throw new Exception("内存映射文件没有建立,请检查...");
        }

        writePosition = writePosition + data.length;
        if(writePosition >= MAX_FILE_SIZE){
            flush();
            writePosition = writePosition - data.length;
            System.out.println("File="+file.toURI().toString()+" is writed full.");
            System.out.println("already write data length:"+writePosition+"max file size="+MAX_FILE_SIZE);
            return false;
        }

        this.mappedByteBuffer.put(data);
        //检测修改量
        if(writePosition-lastFlushFilePosition>this.maxFlushDataSize){
            flush();
        }
        //检测时间间隔
        if(System.currentTimeMillis()-lastFlushTime>this.maxFlushTimeGap && writePosition>lastFlushFilePosition){
            flush();
        }

        return true;
    }


    public synchronized void flush(){
        this.mappedByteBuffer.force();
        this.lastFlushTime=System.currentTimeMillis();
        this.lastFlushFilePosition=writePosition;
    }


    public long getLastFlushTime() {
        return lastFlushTime;
    }


    public String getFileName() {
        return fileName;
    }


    public String getFilePath() {
        return filePath;
    }


    public boolean isBundSuccess() {
        return bundSuccess;
    }


    public File getFile() {
        return file;
    }


    public static long getMaxFileSize() {
        return MAX_FILE_SIZE;
    }


    public long getWritePosition() {
        return writePosition;
    }


    public long getLastFlushFilePosition() {
        return lastFlushFilePosition;
    }


    public long getMaxFlushDataSize() {
        return maxFlushDataSize;
    }


    public long getMaxFlushTimeGap() {
        return maxFlushTimeGap;
    }

    /**
     * 实现文件复制,复制成功，删除返回失败
     * @param filename 文件名
     * @param srcpath   源路径
     * @param destpath  目标目录
     * @throws IOException
     */
    public static void copyFile(String filename,String srcpath,String destpath)throws IOException {
        File source = new File(srcpath+"/"+filename);
        File dest = new File(destpath+"/"+filename);
        FileChannel in = null, out = null;
        try {
            in = new FileInputStream(source).getChannel();
            out = new FileOutputStream(dest).getChannel();
            long size = in.size();
            MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
            out.write(buf);
            in.close();
            out.close();
            clean(buf);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }

    /**
     * 普通方式读写文件，实现文件复制
     * @param name
     * @param srcPath
     * @param destPath
     * @throws Exception
     */
    public static void copyFileNormal(String name, String srcPath, String destPath) throws Exception{
        File source = new File(srcPath + "/" + name);
        if (!source.isFile() && !source.exists()) {
            throw new FileNotFoundException();
        }
        File dest = new File(destPath + "/" + name);
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(dest);

        byte[] bytes = new byte[10*1024*1024];
        int i;
        while ((i = in.read(bytes)) != -1) {
            out.write(bytes, 0, i);
        }
        out.close();
        in.close();
    }

    public static void clean(final Object buffer) throws Exception {
        AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod("cleaner",new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner =(sun.misc.Cleaner)getCleanerMethod.invoke(buffer,new Object[0]);
                    cleaner.clean();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return null;}});

    }

    /**
     * 3915089  7264125
     * 731824   21149
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocate(10*1024*1024);
        byte[] bytes = new byte[10*1024*1024];

        FileInputStream fis = new FileInputStream("D:/logs/demo_log.log");
        FileOutputStream fos = new FileOutputStream("F:/test/1.txt");

        copyFile("demo_log.log", "D:/logs", "F:/test");
        copyFileNormal("demo_log.log", "D:/logs", "F:/test/beifen");

        FileChannel fileChannel = fis.getChannel();

        long start = System.nanoTime();
        //1.普通方式
        fileChannel.read(byteBuffer);
        //2.MapperByteBuffer读取方式
        MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_ONLY, 0, fileChannel.size());

        long end = System.nanoTime();
        System.out.println("read time : " + (end - start));

        start = System.nanoTime();
        mappedByteBuffer.flip();
        end = System.nanoTime();
        System.out.println("write time : " + (end -start));

        fileChannel.close();
        fos.close();
        fis.close();
    }
}
