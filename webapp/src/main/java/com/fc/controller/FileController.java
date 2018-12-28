package com.fc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.fc.bean.FileDO;
import com.fc.common.AjaxResult;
import com.fc.common.FileUtils;
import com.fc.service.FileUploadManager;
import com.fc.file.ImageAndBase64Transfer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fangcong on 2017/4/11.
 */
@Controller
public class FileController {

    @Resource
    private FileUploadManager fileUploadManager;

    @ResponseBody
    @RequestMapping(value = "/file/upload.json")
    public AjaxResult fileUpload(@RequestParam("txt_file") MultipartFile[] files, String tfsName) {
        Map<String, Object> map = new HashMap<>(16);
        MultipartFile file = files[0];
        long size = file.getSize();
        try {
            String fileName = file.getOriginalFilename();
            String ext = FileUtils.getFileType(file.getInputStream());
            if (!fileName.endsWith(ext)) {
                return AjaxResult.getFailResult("E002", "文件格式错误");
            }
            String[] result = {"xxxx"};
            map.put("tfsName", result[0]);
            FileDO info = new FileDO();
            info.setFileName(fileName);
            info.setSize(size);
            info.setTfsName(result[0]);
            info.setType(ext.substring(1));
            fileUploadManager.insertInfo(info);
            return AjaxResult.getSuccessResult(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.getFailResult("E001", "上传失败");
    }

    @RequestMapping(value = "/file/getBase64Str.json", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getBase64Str(String app) {
        String path = "E:/project/maven_demo/test/target/classes/test.jpg";
        String base64Str = ImageAndBase64Transfer.imageToBase64(path);
        return AjaxResult.getSuccessResult(base64Str);
    }

    /**
     * 导出数据测试
     * 字节数组导出格式目前对csv支持较好，xls可导但都在1列,xlsx无法打开
     *
     * @param response
     */
    @RequestMapping(value = "/file/download.json", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response) {
        List<FileDO> list = fileUploadManager.queryAllFiles();
        StringBuilder sb = new StringBuilder();
        sb.append("id,tfsName,fileName,type,size").append("\r\n");
        list.forEach(fileDO -> {
            sb.append(fileDO.getId()).append(",");
            sb.append(fileDO.getTfsName()).append(",");
            sb.append(fileDO.getFileName()).append(",");
            sb.append(fileDO.getType()).append(",");
            sb.append(fileDO.getSize()).append("\r\n");
        });
        try {
            FileUtils.download(response, sb.toString().getBytes(), "导出数据.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
