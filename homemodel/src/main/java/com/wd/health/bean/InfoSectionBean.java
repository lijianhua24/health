package com.wd.health.bean;

import java.util.List;

public class InfoSectionBean {

    /**
     * result : [{"id":21,"plateId":2,"releaseTime":1569464574000,"source":"@weidu","thumbnail":"https://jkcdn.pajk.com.cn/image/T1zabSBmEv1RCvBVdK","title":"胖子每天去操场跑10圈不吃零食，3个月后身体有何变化？"},{"id":22,"plateId":2,"releaseTime":1569464574000,"source":"@weidu","thumbnail":"https://jkcdn.pajk.com.cn/image/T1hxxOBbKv1RCvBVdK","title":"想要科学的瘦身？你需选择正确的烹饪方式，来看看选对了没？"},{"id":23,"plateId":2,"releaseTime":1569464574000,"source":"@weidu","thumbnail":"https://jkcdn.pajk.com.cn/image/T1cBJABKJv1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1ZuLABmKg1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1ZMLOBg_T1RCvBVdK","title":"我的产后瘦身血泪史，分享最有效的减肥菜单"},{"id":24,"plateId":2,"releaseTime":1569464574000,"source":"@weidu","thumbnail":"https://jkcdn.pajk.com.cn/image/T1YiZABCD_1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1vVKkBQhv1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1fwYABTJv1RCvBVdK","title":"小心不要踩雷，吃这3大类早餐越吃越胖，不健康！"},{"id":25,"plateId":2,"releaseTime":1569464574000,"source":"@weidu","thumbnail":"https://jkcdn.pajk.com.cn/image/T1GfxOBQKv1RCvBVdK","title":"锻炼腹肌的一些好办法，掌握这些，事半功倍"}]
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
         * id : 21
         * plateId : 2
         * releaseTime : 1569464574000
         * source : @weidu
         * thumbnail : https://jkcdn.pajk.com.cn/image/T1zabSBmEv1RCvBVdK
         * title : 胖子每天去操场跑10圈不吃零食，3个月后身体有何变化？
         */

        private int id;
        private int plateId;
        private long releaseTime;
        private String source;
        private String thumbnail;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPlateId() {
            return plateId;
        }

        public void setPlateId(int plateId) {
            this.plateId = plateId;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
