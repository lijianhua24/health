package com.wd.health.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class UploadPatientBean {

    /**
     * message : 上传成功
     * status : 0000
     */

    private String message;
    private String status;
    private int result;

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
