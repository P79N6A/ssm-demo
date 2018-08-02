package com.fc.qcodec;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 生成二维码
 *
 * @author fangcong on 2018/6/8.
 */
public class QcodecGenerator {

    /**
     * 二维码颜色
     */
    private static final int BLACK = 0xFF000000;

    /**
     * 二维码颜色
     */
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 编码
     */
    private static final String CHARSET = "UTF-8";

    /**
     * <span>ZXing 方式生成二维码</span>
     *
     * @param text       二维码内容
     * @param width      二维码宽
     * @param height     二维码高
     * @param outPutPath 二维码生成保存路径
     * @param imageType  二维码生成格式
     */
    public static void zxingCodeCreate(String text, int width, int height, String outPutPath, String imageType) {
        Map<EncodeHintType, String> his = new HashMap<>(16);
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, CHARSET);
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);

            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            //3、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //4、循环将二维码内容定入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            // 4、生成二维码到本地存储
            File outPutImage = new File(outPutPath);
            //如果图片不存在创建图片
            if (!outPutImage.exists()) {
                outPutImage.createNewFile();
            }
            //5、将二维码写入图片
            ImageIO.write(image, imageType, outPutImage);
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成二维码图片失败");
        }
    }

    /**
     * <span style="font-size:18px;font-weight:blod;">二维码解析</span>
     *
     * @param analyzePath 二维码路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Object zxingCodeAnalyze(String analyzePath) throws Exception {
        MultiFormatReader formatReader = new MultiFormatReader();
        Object result = null;
        try {
            File file = new File(analyzePath);
            if (!file.exists()) {
                return "二维码不存在";
            }
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap(16);
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
            result = formatReader.decode(binaryBitmap, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        QcodecGenerator.zxingCodeCreate("方聪", 300, 300, "F:/test/fc.jpg", "jpg");

        Thread.sleep(5000);

        Object object = QcodecGenerator.zxingCodeAnalyze("F:/test/fc.jpg");
        System.out.println(object.toString());
    }
}
