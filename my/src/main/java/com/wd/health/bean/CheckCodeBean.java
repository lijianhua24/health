package com.wd.health.bean;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 13:46
 */
public class CheckCodeBean {

    /**
     * message : 验证通过
     * status : 0000
     */

    private String message;
    private String status;

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
