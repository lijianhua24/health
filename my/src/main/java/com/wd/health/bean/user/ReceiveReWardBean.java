package com.wd.health.bean.user;

/**
 * @author: 张恩
 * @description:  领取奖励
 * @date :2019/12/19 15:27
 */
public class ReceiveReWardBean {

    /**
     * message : 领取成功
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
