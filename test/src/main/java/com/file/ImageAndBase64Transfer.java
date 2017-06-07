package com.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * @author fangcong
 */
public class ImageAndBase64Transfer {

	/**
	 * 将图片文件转成字节数组字符串，然后进行base64编码
	 * @param path
	 * @return
	 */
	public static String ImageToBase64(String path){
		byte[] data = null;
		try{
			InputStream in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		//对字节数组进行base64编码
		Base64 base64 = new Base64();
		return base64.encodeToString(data);
		/*BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);*/
	}
	
	/**
	 * 对字节数组进行base64解码并生成图片
	 * @param base64Str 
	 * @param path 图片生成地址
	 * @return
	 */
	public static boolean Base64ToImage(String base64Str,String path){
		if (base64Str == null){
			return false;
		}
		/*BASE64Decoder decoder = new BASE64Decoder();*/
		Base64 base64 = new Base64();
		try{
			/*byte[] bytes = decoder.decodeBuffer(base64Str);*/
			byte[] bytes = base64.decode(base64Str);
			//调整异常数据
			for (byte b : bytes) {
				if (b < 0){
					b += 256;
				}
			}
			//生成JPEG图片
			OutputStream out = new FileOutputStream(path);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		String path = Class.class.getClass().getResource("/").getPath() + "image.jpeg";
		String srcPath = ImageAndBase64Transfer.class.getResource("/").getPath() + "image.jpeg";
		System.out.println(srcPath);
		System.out.println(path);
		String base64Str = ImageToBase64(path);
		System.out.println(base64Str);
		boolean flag = Base64ToImage(base64Str, "F://test2/test.png");
		System.out.println(flag);
	}
}
