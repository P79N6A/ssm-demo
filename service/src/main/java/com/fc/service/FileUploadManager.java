package com.fc.service;

import java.util.List;
import java.util.Set;

import com.fc.bean.FileDO;

/**
 * @author fangcong on 2017/4/11.
 */
public interface FileUploadManager {

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
     * @param list
     * @return
     */
    List<FileDO> testForEach(List<Set<String>> list);

    /**
     * 查询所有文件
     *
     * @return
     */
    List<FileDO> queryAllFiles();
}
