package com.taotao.pojo;

/**
 * @author yq
 * @description 富文本编辑器上传图片所用的POJO
 * @create 2019-16-17-44
 **/
public class PictureUploadResult {

    /**
     * 成功为0，失败为1
     */
    private int error;

    /**
     * 上传之后的图片url
     */
    private String url;

    private String message;

    private PictureUploadResult(int error, String url, String message) {
        this.error = error;
        this.url = url;
        this.message = message;
    }

    public static PictureUploadResult ok(String url) {
        return new PictureUploadResult(0, url, null);
    }

    public static PictureUploadResult error(String message) {
        return new PictureUploadResult(1, null, message);
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}