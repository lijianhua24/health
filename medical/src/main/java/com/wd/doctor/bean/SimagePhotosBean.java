package com.wd.doctor.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 *     选择系统提供形象照
 */
public class SimagePhotosBean {


    /**
     * result : http://172.17.8.100/images/health/doctor/system_image_pic/system_image1.jpg
     * message : 上传成功
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}