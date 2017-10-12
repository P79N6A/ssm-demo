package com.fc.bean;

import java.io.Serializable;

public class FileDO implements Serializable {
    private static final long serialVersionUID = 3828952714036125659L;

    private Integer id;

    private String tfsName;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 上传前文件名称
     */
    private String fileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileDO{" +
            "id=" + id +
            ", tfsName='" + tfsName + '\'' +
            ", size=" + size +
            ", type='" + type + '\'' +
            ", fileName='" + fileName + '\'' +
            '}';
    }
}
