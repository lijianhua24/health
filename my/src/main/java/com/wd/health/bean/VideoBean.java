package com.wd.health.bean;

import java.util.List;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 19:59
 */
public class VideoBean {

    /**
     * result : [{"createTime":1576476200000,"duration":26,"id":552,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek1.mp4","title":"南方健康","videoId":1}]
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
         * createTime : 1576476200000
         * duration : 26
         * id : 552
         * originalUrl : http://172.17.8.100/video/health/original/ek/ek1.mp4
         * title : 南方健康
         * videoId : 1
         */

        private long createTime;
        private int duration;
        private int id;
        private String originalUrl;
        private String title;
        private int videoId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }
    }
}
