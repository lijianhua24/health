package com.wd.health.bean;

import java.util.List;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/15 19:43
 */
public class HealthyCurrencyBean {

    /**
     * result : [{"content":"恭喜您成功注册维度健康，注册奖励已发放到您的钱包，快去查看吧！","createTime":1575977301000,"id":332854}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 恭喜您成功注册维度健康，注册奖励已发放到您的钱包，快去查看吧！
         * createTime : 1575977301000
         * id : 332854
         */

        private String content;
        private long createTime;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
