package com.wd.doctor.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class ResidencyBean {

    /**
     * message : 该邮箱账号已存在
     * status : 8002
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
