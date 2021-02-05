package com.zhzh.service;

import com.zhzh.util.model.ResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : zhang sq
 * @date : 2020/6/30 11:30
 **/
public interface FileUploadService {

    //handleFileUpload
    ResultVO handleFileUpload(MultipartFile file);
}
