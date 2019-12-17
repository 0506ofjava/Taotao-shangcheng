package com.taotao.service;

import com.taotao.pojo.PictureUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    public PictureUploadResult uploadPicture(MultipartFile uploadFile);
}
