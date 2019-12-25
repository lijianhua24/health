package com.wd.health.bean;

import java.util.List;

public class VideoSortBean {

    /**
     * result : [{"abstracts":"开灯睡觉对宝宝生长发育有影响吗？","buyNum":12,"categoryId":1,"duration":61,"id":7,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek7.mp4","price":55,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek7.mp4","title":"儿科医生雨滴","whetherBuy":2,"whetherCollection":2},{"abstracts":"宝宝一哭就抱，会被惯坏吗？","buyNum":8,"categoryId":1,"duration":53,"id":3,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek3.mp4","price":300,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek3.mp4","title":"儿科医生雨滴","whetherBuy":2,"whetherCollection":2},{"abstracts":"宝宝发烧这些错误的物理降温方法不要用！","buyNum":8,"categoryId":1,"duration":43,"id":8,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek8.mp4","price":200,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek8.mp4","title":"儿科医生雨滴","whetherBuy":2,"whetherCollection":2}]
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
         * abstracts : 开灯睡觉对宝宝生长发育有影响吗？
         * buyNum : 12
         * categoryId : 1
         * duration : 61
         * id : 7
         * originalUrl : http://172.17.8.100/video/health/original/ek/ek7.mp4
         * price : 55
         * shearUrl : http://172.17.8.100/video/health/cut/ek/ek7.mp4
         * title : 儿科医生雨滴
         * whetherBuy : 2
         * whetherCollection : 2
         */

        private String abstracts;
        private int buyNum;
        private int categoryId;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int whetherBuy;
        private int whetherCollection;

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getShearUrl() {
            return shearUrl;
        }

        public void setShearUrl(String shearUrl) {
            this.shearUrl = shearUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }
    }
}
