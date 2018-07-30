package com.fc.service;

import java.util.List;
import java.util.Set;

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
     * 上传图片得到tfsName
     *
     * @param bytes
     * @param ext
     * @return
     */
    String uploadImage(byte[] bytes, String ext);

    /**
     * 记录文件信息
     *
     * @param fileDO
     * @return
     */
    boolean insertInfo(FileDO fileDO);

    /**
     * 批量查询,in查询超过1000条时先拆分
     *
     * @return
     */
    List<FileDO> testForEach(List<Set<String>> list);
}
