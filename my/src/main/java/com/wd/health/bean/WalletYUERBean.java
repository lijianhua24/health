package com.wd.health.bean;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 11:42
 */
public class WalletYUERBean {

    /**
     * result : 800
     * message : 查询成功
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
