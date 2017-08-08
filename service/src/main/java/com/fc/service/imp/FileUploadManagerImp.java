package com.fc.service.imp;

import com.fc.bean.FileDO;
import com.fc.dao.UserDao;
import com.fc.service.FileUploadManager;
import com.taobao.common.tfs.TfsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by fangcong on 2017/4/11.
 */
@Service
public class FileUploadManagerImp implements FileUploadManager {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadManagerImp.class);

    @Resource
    private TfsManager tfsManager;
    @Resource
    private UserDao userDao;

    @Override
    public String[] uploadFile(byte[] bytes, String tfsName) {
        String imageName = null;
        try {
            imageName = tfsManager.saveFile(bytes, tfsName, null);
            return new String[]{imageName};
        }catch (Exception e) {
            logger.error("tfsManager.saveFile", e);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean insertInfo(FileDO fileDO) {
        return userDao.insertFileInfo(fileDO) > 0;
    }
}