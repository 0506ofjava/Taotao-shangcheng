package com.taotao.controller;

import com.taotao.pojo.PictureUploadResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yq
 * @description
 * @create 2019-17-00-26
 **/
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public PictureUploadResult upload(MultipartFile uploadFile) {
        PictureUploadResult result = pictureService.uploadPicture(uploadFile);
        return result;
    }
}