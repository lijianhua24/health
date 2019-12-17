package com.wd.health.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class QueryCommentBean {

    /**
     * result : [{"commentId":7692,"commentTime":1576460073000,"commentUserId":129,"content":"2","headPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"像风一样","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1},{"commentId":7673,"commentTime":1576408428000,"commentUserId":1,"content":"**医生非常棒","headPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"张医生","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1},{"commentId":7413,"commentTime":1576223588000,"commentUserId":90,"content":"八维傻逼","headPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"张伟成","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1}]
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
         * commentId : 7692
         * commentTime : 1576460073000
         * commentUserId : 129
         * content : 2
         * headPic : http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg
         * nickName : 像风一样
         * opinion : 0
         * opposeNum : 0
         * supportNum : 0
         * whetherDoctor : 1
         */

        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String content;
        private String headPic;
        private String nickName;
        private int opinion;
        private int opposeNum;
        private int supportNum;
        private int whetherDoctor;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getOpinion() {
            return opinion;
        }

        public void setOpinion(int opinion) {
            this.opinion = opinion;
        }

        public int getOpposeNum() {
            return opposeNum;
        }

        public void setOpposeNum(int opposeNum) {
            this.opposeNum = opposeNum;
        }

        public int getSupportNum() {
            return supportNum;
        }

        public void setSupportNum(int supportNum) {
            this.supportNum = supportNum;
        }

        public int getWhetherDoctor() {
            return whetherDoctor;
        }

        public void setWhetherDoctor(int whetherDoctor) {
            this.whetherDoctor = whetherDoctor;
        }
    }
}
