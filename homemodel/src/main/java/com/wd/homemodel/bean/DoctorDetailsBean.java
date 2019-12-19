package com.wd.homemodel.bean;

import java.util.List;

public class DoctorDetailsBean {
    /**
     * result : {"badNum":3,"commentList":[{"commentTime":1564798013000,"content":"啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg","nickName":"简简单单"},{"commentTime":1564797723000,"content":"啊实打实大师多","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg","nickName":"简简单单"},{"commentTime":1564795304000,"content":"大撒打算打算的","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg","nickName":"简简单单"}],"commentNum":11,"doctorId":11,"doctorName":"王硕","doctorReceiveGiftList":[{"giftName":"奖杯","giftPic":"http://172.17.8.100/images/health/gift/gift3.jpg","meaning":"奖杯是一种荣誉","receiveNum":7,"worth":100}],"followFlag":2,"goodField":"套你猴子的","imagePic":"http://172.17.8.100/images/health/doctor/system_image_pic/system_image5.jpg","inauguralHospital":"北京军区医院","jobTitle":"主任医师","personalProfile":"你妈炸了","praise":"50.00%","praiseNum":8,"serverNum":12,"servicePrice":500}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {
        /**
         * badNum : 3
         * commentList : [{"commentTime":1564798013000,"content":"啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg","nickName":"简简单单"},{"commentTime":1564797723000,"content":"啊实打实大师多","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg","nickName":"简简单单"},{"commentTime":1564795304000,"content":"大撒打算打算的","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg","nickName":"简简单单"}]
         * commentNum : 11
         * doctorId : 11
         * doctorName : 王硕
         * doctorReceiveGiftList : [{"giftName":"奖杯","giftPic":"http://172.17.8.100/images/health/gift/gift3.jpg","meaning":"奖杯是一种荣誉","receiveNum":7,"worth":100}]
         * followFlag : 2
         * goodField : 套你猴子的
         * imagePic : http://172.17.8.100/images/health/doctor/system_image_pic/system_image5.jpg
         * inauguralHospital : 北京军区医院
         * jobTitle : 主任医师
         * personalProfile : 你妈炸了
         * praise : 50.00%
         * praiseNum : 8
         * serverNum : 12
         * servicePrice : 500
         */

        private int badNum;
        private int commentNum;
        private int doctorId;
        private String doctorName;
        private int followFlag;
        private String goodField;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String personalProfile;
        private String praise;
        private int praiseNum;
        private int serverNum;
        private int servicePrice;
        private List<CommentListBean> commentList;
        private List<DoctorReceiveGiftListBean> doctorReceiveGiftList;

        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public int getFollowFlag() {
            return followFlag;
        }

        public void setFollowFlag(int followFlag) {
            this.followFlag = followFlag;
        }

        public String getGoodField() {
            return goodField;
        }

        public void setGoodField(String goodField) {
            this.goodField = goodField;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getInauguralHospital() {
            return inauguralHospital;
        }

        public void setInauguralHospital(String inauguralHospital) {
            this.inauguralHospital = inauguralHospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getPersonalProfile() {
            return personalProfile;
        }

        public void setPersonalProfile(String personalProfile) {
            this.personalProfile = personalProfile;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getServerNum() {
            return serverNum;
        }

        public void setServerNum(int serverNum) {
            this.serverNum = serverNum;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public List<DoctorReceiveGiftListBean> getDoctorReceiveGiftList() {
            return doctorReceiveGiftList;
        }

        public void setDoctorReceiveGiftList(List<DoctorReceiveGiftListBean> doctorReceiveGiftList) {
            this.doctorReceiveGiftList = doctorReceiveGiftList;
        }

        public static class CommentListBean {
            /**
             * commentTime : 1564798013000
             * content : 啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦
             * headPic : http://172.17.8.100/images/health/user/head_pic/2019-08-19/zgVhM220190819192416.jpeg
             * nickName : 简简单单
             */

            private long commentTime;
            private String content;
            private String headPic;
            private String nickName;

            public long getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(long commentTime) {
                this.commentTime = commentTime;
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
        }

        public static class DoctorReceiveGiftListBean {
            /**
             * giftName : 奖杯
             * giftPic : http://172.17.8.100/images/health/gift/gift3.jpg
             * meaning : 奖杯是一种荣誉
             * receiveNum : 7
             * worth : 100
             */

            private String giftName;
            private String giftPic;
            private String meaning;
            private int receiveNum;
            private int worth;

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public String getGiftPic() {
                return giftPic;
            }

            public void setGiftPic(String giftPic) {
                this.giftPic = giftPic;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public int getReceiveNum() {
                return receiveNum;
            }

            public void setReceiveNum(int receiveNum) {
                this.receiveNum = receiveNum;
            }

            public int getWorth() {
                return worth;
            }

            public void setWorth(int worth) {
                this.worth = worth;
            }
        }
    }
}
