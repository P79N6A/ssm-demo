package com.fc.service;

import com.fc.bean.FileDO;

/**
 * @author fangcong on 2017/4/11.
 */
public interface FileUploadManager {

    /**
     * 上传文件到tfs
     *
     * @param bytes
     * @param tfsName
     * @return
     */
    String[] uploadFile(byte[] bytes, String tfsName);

    /**
     * 记录文件信息
     *
     * @param fileDO
     * @return
     */
    boolean insertInfo(FileDO fileDO);
}
