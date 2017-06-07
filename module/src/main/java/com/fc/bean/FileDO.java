package com.fc.bean;
import java.io.Serializable;

public class FileDO implements Serializable{
	private static final long serialVersionUID = 3828952714036125659L;

	private int id;
	
	private String tfsName;
	/**
	 * 文件大小
	 */
	private long size;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 上传前文件名称
	 */
	private String fileName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTfsName() {
		return tfsName;
	}

	public void setTfsName(String tfsName) {
		this.tfsName = tfsName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
