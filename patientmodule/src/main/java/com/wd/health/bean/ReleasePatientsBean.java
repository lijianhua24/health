package com.wd.health.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class ReleasePatientsBean {

    /**
     * result : 1731
     * message : 发表成功
     * status : 0000
     */

    private int result;
    private String message;
    private String status;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
