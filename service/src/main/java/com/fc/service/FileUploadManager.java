package com.fc.service;

import com.fc.bean.FileDO;

/**
 * Created by fangcong on 2017/4/11.
 */
public interface FileUploadManager {

    String[] uploadFile(byte[] bytes, String tfsName);

    boolean insertInfo(FileDO fileDO);
}
