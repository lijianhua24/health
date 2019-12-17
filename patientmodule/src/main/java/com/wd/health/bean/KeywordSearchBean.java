package com.wd.health.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class KeywordSearchBean {

    /**
     * result : [{"amount":0,"detail":"可怜人真可怜","releaseTime":1569168000000,"sickCircleId":1306,"title":"张梦龙头疼"},{"amount":0,"detail":"想不到就到家","releaseTime":1568908800000,"sickCircleId":1304,"title":"头疼"},{"amount":0,"detail":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈","releaseTime":1568908800000,"sickCircleId":1303,"title":"李根头疼"},{"amount":0,"detail":"非凡哥回家看看吧不好看好尬复习","releaseTime":1564675200000,"sickCircleId":924,"title":"头疼头晕"},{"amount":10,"detail":"疼起来像针扎一样","releaseTime":1564070400000,"sickCircleId":594,"title":"头疼的厉害"},{"amount":0,"detail":"敲代码","releaseTime":1564070400000,"sickCircleId":583,"title":"头疼"}]
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
         * detail : 可怜人真可怜
         * releaseTime : 1569168000000
         * sickCircleId : 1306
         * title : 张梦龙头疼
         */

        private int amount;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;
        private int collectionNum;
        private int commentNum;

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
