package com.fc.java.improve;

import java.io.*;

/**
 * 自定义类加载器，继承ClassLoader
 *
 * @author fangcong on 2017/4/13.
 */
public class CladdReloader extends ClassLoader {

    private String classPath;
    String className = "com.fc.java.improve.OgnlDemo";

    public CladdReloader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 构建数据
     *
     * @param className
     * @return
     */
    private byte[] getData(String className) {
        String path = classPath + className;

        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num;
            while ((num = is.read(buffer)) != -1) {
                bos.write(buffer, 0, num);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建类对象
     *
     * @param name
     * @return
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(className, classData, 0, classData.length);
        }
    }

    public static void main(String[] args) {
        String path = CladdReloader.class.getClass().getResource("/").getPath();
        path = path.substring(1) + "com/fc/java/improve/";
        try {

            CladdReloader c = new CladdReloader(path);
            Class r = c.findClass("OgnlDemo.class");
            System.out.println(r.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
