package com.wd.doctor.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class SuffererDetailBean {

    /**
     * result : [{"amount":0,"detail":"老了,不行了","releaseTime":1576512000000,"sickCircleId":1758,"title":"郭昊坤"},{"amount":0,"detail":"不行了,快死了","releaseTime":1576512000000,"sickCircleId":1757,"title":"郭昊坤"},{"amount":0,"detail":"不行了,快死了","releaseTime":1576512000000,"sickCircleId":1756,"title":"郭昊坤"},{"amount":0,"detail":"老了,不行了快死了","releaseTime":1576512000000,"sickCircleId":1755,"title":"郭昊坤"},{"amount":0,"detail":"年纪大了,不行了","releaseTime":1576512000000,"sickCircleId":1754,"title":"郭昊坤"},{"amount":50,"detail":"卧看满天云不动，不知云与我俱东","releaseTime":1571587200000,"sickCircleId":1485,"title":"江畔何人初见"},{"amount":0,"detail":"明","releaseTime":1569686400000,"sickCircleId":1457,"title":"第二"},{"amount":0,"detail":"焦焦焦焦焦","releaseTime":1569686400000,"sickCircleId":1444,"title":""},{"amount":0,"detail":"啥都不知道，呆呆傻傻","releaseTime":1569600000000,"sickCircleId":1422,"title":"痴呆"},{"amount":0,"detail":"时而欢喜，时而狂暴，时而魅惑，时而娇啼，时而狗血，时而撒野，时而撒娇，时而沙雕","releaseTime":1569513600000,"sickCircleId":1364,"title":"吴天宇狂犬病"}]
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
         * detail : 老了,不行了
         * releaseTime : 1576512000000
         * sickCircleId : 1758
         * title : 郭昊坤
         */

        private int amount;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

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
