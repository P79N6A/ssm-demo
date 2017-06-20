package com.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

@SuppressWarnings({ "serial", "deprecation" })
public class MulFileUpload extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=GB2312";

	// Process the HTTP Post request
	@Override
	@SuppressWarnings({ "rawtypes" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		try {
			DiskFileUpload fu = new DiskFileUpload();
			// 设置允许用户上传文件大小,单位:字节，这里设为2m
			fu.setSizeMax(2 * 1024 * 1024);
			// 设置最多只允许在内存中存储的数据,单位:字节
			fu.setSizeThreshold(4096);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			fu.setRepositoryPath("c://windows//temp");
			// 开始读取上传信息
			List fileItems = fu.parseRequest(request);
			// 依次处理每个上传的文件
			Iterator iter = fileItems.iterator();

			// 正则匹配，过滤路径取文件名
			String regExp = ".+////(.+)$";

			// 过滤掉的文件类型
			String[] errorType = { ".exe", ".com", ".cgi", ".asp" };
			Pattern p = Pattern.compile(regExp);
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 忽略其他不是文件域的所有表单信息
				if (!item.isFormField()) {
					String name = item.getName();
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0) {
						continue;
					}
					Matcher m = p.matcher(name);
					boolean result = m.find();
					if (result) {
						for (int temp = 0; temp < errorType.length; temp++) {
							if (m.group(1).endsWith(errorType[temp])) {
								throw new IOException(name + ": wrong type");
							}
						}
						try {
							// 保存上传的文件到指定的目录
							// 在下文中上传文件至数据库时，将对这里改写
							item.write(new File("d://" + m.group(1)));
							out.print(name + "  " + size + "");
						} catch (Exception e) {
							out.println(e);
						}
					} else {
						throw new IOException("fail to upload");
					}
				}
			}
		} catch (IOException e) {
			out.println(e);
		} catch (FileUploadException e) {
			out.println(e);
		}

	}
}