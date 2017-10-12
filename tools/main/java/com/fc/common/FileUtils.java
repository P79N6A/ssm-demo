package com.fc.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {
    public static Map<String, String> mFileTypes = new HashMap<String, String>();

    static {
        mFileTypes.put("FFD8FFE0", ".jpg");
        mFileTypes.put("89504E47", ".png");
        mFileTypes.put("47494638", ".gif");
        mFileTypes.put("35343135", ".txt");
        mFileTypes.put("0D0A0D0A", ".txt");
        mFileTypes.put("0D0A2D2D", ".txt");
        mFileTypes.put("0D0AB4B4", ".txt");
        mFileTypes.put("B4B4BDA8", ".txt");
        mFileTypes.put("73646673", ".txt");
        mFileTypes.put("32323232", ".txt");
        mFileTypes.put("0D0A09B4", ".txt");
        mFileTypes.put("3132330D", ".txt");
        mFileTypes.put("504B0304", ".xlsx");
    }

    public static String getFileType(InputStream is, String fileName) {
        byte[] b = new byte[4];
        if (fileName.endsWith("txt") || fileName.endsWith("csv")|| fileName.endsWith("js")) {
            return "SUCCESS";
        }
        if (is != null) {
            try {
                is.read(b, 0, b.length);
            } catch (IOException e) {
                return "ERROR";
            }
        }

        String type = mFileTypes.get(getFileHeader(b));
        if (type == null) {
            return "ERROR";
        } else {
            return type;
        }
    }

    public static String getFileHeader(byte[] src) {
        /*return bytesToHexString(b);*/
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    /*private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }*/

    /**
     * 获取图片字节数组
     *
     * @param inputStream 图片输入流
     * @param suffix      后缀
     * @return
     * @throws IOException
     */
    public static byte[] getImageByte(InputStream inputStream, String suffix) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            BufferedImage image = ImageIO.read(inputStream);
            ImageIO.write(image, suffix, out);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void download(HttpServletResponse response, byte[] bytes, String suffixName, String fileName) throws IOException {
        response.reset();
        response.setContentType("application/" + suffixName);
        String name = fileName + "." + suffixName;
        name = new String(name.getBytes("GBK"), "ISO8859_1");
        response.setHeader("Content-Disposition", "attachment;" + "filename=" + name);

        OutputStream output = response.getOutputStream();
        output.write(bytes);

        output.flush();
        output.close();
        response.flushBuffer();
    }
}
