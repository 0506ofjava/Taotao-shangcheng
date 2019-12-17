package com.taotao.service.impl;

import com.taotao.pojo.PictureUploadResult;
import com.taotao.service.PictureService;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yq
 * @description
 * @create 2019-17-00-11
 **/
@Service
public class PictureServiceImpl implements PictureService {

    //@Value对应的属性不能为static,否则注入为null
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER_NAME}")
    private String FTP_USER_NAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;


    @Override
    public PictureUploadResult uploadPicture(MultipartFile uploadFile) {
        //判断上传图片是否为空
        if (null == uploadFile || uploadFile.isEmpty()) {
            return PictureUploadResult.error("上传图片为空");
        }

        //取文件扩展名
        String originalFilename = uploadFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

        //生成新文件名
        //可以使用uuid生成新文件名,UUID.randomUUID()
        //可以是时间+随机数生成文件名
        String imageName = IDUtils.genImageName();

        //把图片上传到ftp服务器（图片服务器）
        //文件在服务器的存放路径，应该使用日期分隔的目录结构
        DateTime dateTime = new DateTime();
        String filePath = dateTime.toString("/yyyy/MM/dd");

        try {
            boolean isSuccessful = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, FTP_PASSWORD,
                    FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());
            if (!isSuccessful) {
                return PictureUploadResult.error("FTP上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PictureUploadResult.error("FTP上传失败:" + ExceptionUtil.getStackTrace(e));
        }

        //返回结果，生成一个可以访问到图片的url
        return PictureUploadResult.ok(IMAGE_BASE_URL + filePath + "/" + imageName + ext);

    }
}