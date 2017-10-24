package com.fc.file;

import com.fc.common.EncryptUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 * 文件读取时出现乱码分析
 * @author SYSTEM
 *
 */
public class FileEncoder {

	/**
	 * 写入内容到TXT文件中
	 * @param filePath
	 * @param charSet
	 * @param content
	 */
	public static void writeStrToTxt(String filePath,String charSet,String content){
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			if (StringUtils.isBlank(charSet)) {
				charSet = System.getProperty("file.encoding");
			}
			OutputStreamWriter writer = new OutputStreamWriter(out, charSet);
			writer.write(content);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取TXT文件中的内容
	 * @param filePath
	 * @param charset
	 */
	public static String readStrFromTxt(String filePath,String charset){
		StringBuffer sb = new StringBuffer();
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream in = new FileInputStream(file);
			InputStreamReader reader;
			if (StringUtils.isBlank(charset)){
				reader = new InputStreamReader(in);
			}else{
				reader = new InputStreamReader(in, charset);
			}
			char[] buf = new char[64];
			int count = 0;
			while((count = reader.read(buf)) != -1){
				sb.append(buf, 0, count);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


	public static void encode() {
		String name = "I am 君山";
		try {
			byte[] iso8859 = name.getBytes("ISO-8859-1");
			System.out.println(EncryptUtils.parseBytes2Hex(iso8859));
			byte[] gb2312 = name.getBytes("GB2312");
			System.out.println(EncryptUtils.parseBytes2Hex(gb2312));
			byte[] gbk = name.getBytes("GBK");
			System.out.println(EncryptUtils.parseBytes2Hex(gbk));
			byte[] utf16 = name.getBytes("UTF-16");
			System.out.println(EncryptUtils.parseBytes2Hex(utf16));
			byte[] utf8 = name.getBytes("UTF-8");
			System.out.println(EncryptUtils.parseBytes2Hex(utf8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		writeStrToTxt("F:/test/2.txt", "", "测试写字符到文件");
		String str = readStrFromTxt("F:/test/1.txt", "utf-8");
		String str2 = readStrFromTxt("F:/test/2.txt", "");
		System.out.println(str);
		System.out.println(str2);
		encode();
	}
}
