package com.wd.health.bean;

import java.util.List;

public class RecordingBean {
    /**
     * result : [{"askTime":1577167507000,"content":"李海","direction":1,"doctorHeadPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","msgType":1,"userHeadPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg"},{"askTime":1577167345000,"content":"文本","direction":2,"doctorHeadPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","msgType":1,"userHeadPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg"},{"askTime":1577167308000,"content":"音视","direction":2,"doctorHeadPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","msgType":1,"userHeadPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg"},{"askTime":1577166959000,"content":"李海","direction":1,"doctorHeadPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","msgType":1,"userHeadPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg"},{"askTime":1577166951000,"content":"安排","direction":1,"doctorHeadPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","msgType":1,"userHeadPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg"},{"askTime":1577166826000,"content":"安排","direction":1,"doctorHeadPic":"http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg","msgType":1,"userHeadPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg"}]
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
         * askTime : 1577167507000
         * content : 李海
         * direction : 1
         * doctorHeadPic : http://172.17.8.100/images/health/doctor/image_pic/default/default_image_pic.jpg
         * msgType : 1
         * userHeadPic : http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg
         */

        private long askTime;
        private String content;
        private int direction;
        private String doctorHeadPic;
        private int msgType;
        private String userHeadPic;

        public long getAskTime() {
            return askTime;
        }

        public void setAskTime(long askTime) {
            this.askTime = askTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getDoctorHeadPic() {
            return doctorHeadPic;
        }

        public void setDoctorHeadPic(String doctorHeadPic) {
            this.doctorHeadPic = doctorHeadPic;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getUserHeadPic() {
            return userHeadPic;
        }

        public void setUserHeadPic(String userHeadPic) {
            this.userHeadPic = userHeadPic;
        }
    }
}
