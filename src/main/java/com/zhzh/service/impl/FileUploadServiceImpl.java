package com.zhzh.service.impl;


import com.zhzh.constants.Constants;
import com.zhzh.service.FileUploadService;
import com.zhzh.util.exception.GlobalException;
import com.zhzh.util.model.ResultVO;
import com.zhzh.util.model.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author : zhang sq
 * @date : 2020/6/30 11:30
 **/
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private Constants constants;

    @Override
    public ResultVO handleFileUpload(MultipartFile file) {
        log.info("文件上传");
        if(file==null){
            throw new GlobalException(Constants.PARAM_ERROR,"file文件为空");
        }
        // 获取旧文件名
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)){
            throw new GlobalException(Constants.PARAM_ERROR,"file文件为空");
        }
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        String UUIDName = UUID.randomUUID().toString().replace("-", "")+suffixName; //新文件名
        // 文件上传后的路径
        String filePath = constants.FILE_PATH;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //访问地址
            String fileUrl=constants.FILE_HEAD_URL+fileName;
            Map<String,Object> map=new HashMap<>();
            map.put("oldFileName",fileName);
            map.put("picName",fileName);
            map.put("url",fileUrl);
            return new ResultVO(StatusCode.RESULT_SUCCESS,map);
        } catch (Exception e) {
            log.error("文件上传出错");
            e.printStackTrace();
        }
        return new ResultVO(StatusCode.UPLOAD_FILE_ERROR);
    }

}
