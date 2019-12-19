package com.wd.doctor.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SuffererOutBean {

    /**
     * result : {"amount":0,"authorName":"IF_LGOGZ","departmentId":11,"departmentName":"精神病科","detail":"老了,不行了","disease":"老年痴呆症","id":1758,"picture":"http://172.17.8.100/images/health/user/sickCircle/2019-12-17/2odZTN20191217171131.png","title":"郭昊坤","treatmentEndTime":1576598400000,"treatmentHospital":"中国人民解放军","treatmentProcess":"不好","treatmentStartTime":1576512000000,"userId":440,"whetherContent":2}
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
         * amount : 0
         * authorName : IF_LGOGZ
         * departmentId : 11
         * departmentName : 精神病科
         * detail : 老了,不行了
         * disease : 老年痴呆症
         * id : 1758
         * picture : http://172.17.8.100/images/health/user/sickCircle/2019-12-17/2odZTN20191217171131.png
         * title : 郭昊坤
         * treatmentEndTime : 1576598400000
         * treatmentHospital : 中国人民解放军
         * treatmentProcess : 不好
         * treatmentStartTime : 1576512000000
         * userId : 440
         * whetherContent : 2
         */

        private int amount;
        private String authorName;
        private int departmentId;
        private String departmentName;
        private String detail;
        private String disease;
        private int id;
        private String picture;
        private String title;
        private long treatmentEndTime;
        private String treatmentHospital;
        private String treatmentProcess;
        private long treatmentStartTime;
        private int userId;
        private int whetherContent;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String content;
        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getTreatmentEndTime() {
            return treatmentEndTime;
        }

        public void setTreatmentEndTime(long treatmentEndTime) {
            this.treatmentEndTime = treatmentEndTime;
        }

        public String getTreatmentHospital() {
            return treatmentHospital;
        }

        public void setTreatmentHospital(String treatmentHospital) {
            this.treatmentHospital = treatmentHospital;
        }

        public String getTreatmentProcess() {
            return treatmentProcess;
        }

        public void setTreatmentProcess(String treatmentProcess) {
            this.treatmentProcess = treatmentProcess;
        }

        public long getTreatmentStartTime() {
            return treatmentStartTime;
        }

        public void setTreatmentStartTime(long treatmentStartTime) {
            this.treatmentStartTime = treatmentStartTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherContent() {
            return whetherContent;
        }

        public void setWhetherContent(int whetherContent) {
            this.whetherContent = whetherContent;
        }
    }
}
