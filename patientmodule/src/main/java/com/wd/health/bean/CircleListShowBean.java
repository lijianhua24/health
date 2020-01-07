package com.wd.health.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class CircleListShowBean {

    /**
     * result : [{"amount":0,"detail":"发烧","releaseTime":1575388800000,"sickCircleId":1623,"title":"病例"},{"amount":0,"detail":"??","releaseTime":1575388800000,"sickCircleId":1622,"title":"??"},{"amount":0,"detail":"????","releaseTime":1575302400000,"sickCircleId":1615,"title":"??"},{"amount":0,"detail":"????","releaseTime":1575302400000,"sickCircleId":1614,"title":"??"},{"amount":0,"detail":"????","releaseTime":1575302400000,"sickCircleId":1613,"title":"??"},{"amount":0,"detail":"555","releaseTime":1574611200000,"sickCircleId":1515,"title":"555"},{"amount":0,"detail":"555","releaseTime":1574611200000,"sickCircleId":1514,"title":"555"},{"amount":0,"detail":"555","releaseTime":1574611200000,"sickCircleId":1513,"title":"555"},{"amount":0,"detail":"555","releaseTime":1574611200000,"sickCircleId":1512,"title":"555"},{"amount":0,"detail":"555","releaseTime":1574611200000,"sickCircleId":1511,"title":"555"}]
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
         * amount : 0
         * detail : 发烧
         * releaseTime : 1575388800000
         * sickCircleId : 1623
         * title : 病例
         */

        private int amount;
        private int collectionNum;
        private int commentNum;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(int sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
