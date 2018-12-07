package com.fc.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.fc.bean.FileDO;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author fangcong
 */
public class FileUtils {
    public static Map<String, String> mFileTypes = new HashMap<>();

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

    /**
     * 读取文件获取文件类型
     *
     * @param is
     * @return
     */
    public static String getFileType(InputStream is) {
        byte[] b = new byte[4];
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
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        // String hv; 转16进制方式替换
        for (int i = 0; i < src.length; i++) {
            /*hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }*/
            builder.append(String.format("%02X", src[i]));
        }
        return builder.toString();
    }

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

    /**
     * 下载文件
     *
     * @param response 响应
     * @param bytes    文件字节流
     * @param fileName 导出名称
     * @throws IOException
     */
    public static void download(HttpServletResponse response, byte[] bytes, String fileName)
        throws IOException {
        // 清空header缓充区数据
        response.reset();
        // 二进制流，不知道文件格式时使用
        response.setContentType("application/octet-stream");
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        OutputStream output = response.getOutputStream();
        output.write(bytes);

        output.flush();
        output.close();
        response.flushBuffer();
    }

    /**
     * 导出xlsx文件
     *
     * @param response
     * @param list
     * @param fileName
     */
    public static void exportData(HttpServletResponse response, String[] cols, List<FileDO> list, String fileName)
        throws Exception {
        response.reset();
        response.setContentType("application/msexcel");
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        OutputStream os = response.getOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("导出数据");
        // 1、设置表头
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < cols.length; i++) {
            row.createCell(i).setCellValue(cols[i]);
        }

        for (int rowIdx = 1; rowIdx <= list.size(); rowIdx++) {
            FileDO fileDO = list.get(rowIdx - 1);
            int colIdx = 0;
            row = sheet.createRow(rowIdx);
            row.createCell(colIdx++).setCellValue(fileDO.getId());
            row.createCell(colIdx++).setCellValue(fileDO.getFileName());
            row.createCell(colIdx++).setCellValue(fileDO.getTfsName());
            row.createCell(colIdx++).setCellValue(fileDO.getType());
            row.createCell(colIdx).setCellValue(fileDO.getSize());
        }

        workbook.write(os);
        workbook.close();
        os.close();
    }

    public static void main(String[] args) {
        File file = new File("D:/test/vedio");
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles(pathname -> {
                return pathname.getName().endsWith(".mp4");
            });
            Stream.of(files).forEach(file1 -> {
                System.out.println(file1.getName().substring(0, file1.getName().lastIndexOf(".")));
                System.out.println(file1.getPath());
            });
        }
    }
}
