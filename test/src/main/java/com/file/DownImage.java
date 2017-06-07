package com.file;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载图片
 * 
 * @author vinegar
 */
public class DownImage {

	public static void download(String urlString, String filename)
			throws Exception {
		System.out.println("download xml start.......");
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();

		byte[] bs = new byte[1024];
		int len;
		OutputStream os = new FileOutputStream(filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
		System.out.println("download xml over.......");
	}
	
	public static void main(String[] args) {
		try {
			download("http://img01.taobaocdn.com/tfscom/TB1rr5DLpXXXXbJXVXXxKNpFXXX", "F://test/image.jpeg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}