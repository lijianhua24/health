package com.wd.health.bean;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/17 16:02
 */
public class ModifyHeadPicBean {

    /**
     * result : http://172.17.8.100/images/health/user/head_pic/2019-04-03/20190403110412.jpg
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
