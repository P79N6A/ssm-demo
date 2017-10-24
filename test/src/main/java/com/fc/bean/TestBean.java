package com.fc.bean;

import java.io.Serializable;

/**
 * @author fangcong
 */
public class TestBean implements Serializable {

	/**
	 * 测试bean
	 */
	private static final long serialVersionUID = 923995213849600828L;

	private int id;
	private long startId;
	private String endStr;

	public TestBean() {
		super();
	}

	public TestBean(int id, long startId, String endStr) {
		super();
		this.id = id;
		this.startId = startId;
		this.endStr = endStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getStartId() {
		return startId;
	}

	public void setStartId(long startId) {
		this.startId = startId;
	}

	public String getEndStr() {
		return endStr;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}

	@Override
	public String toString() {
		return "TestBean [id=" + id + ", startId=" + startId + ", endStr="
				+ endStr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endStr == null) ? 0 : endStr.hashCode());
		result = prime * result + id;
		result = prime * result + (int) (startId ^ (startId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TestBean other = (TestBean) obj;
		if (endStr == null) {
			if (other.endStr != null) {
				return false;
			}
		} else if (!endStr.equals(other.endStr)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (startId != other.startId) {
			return false;
		}
		return true;
	}

}
