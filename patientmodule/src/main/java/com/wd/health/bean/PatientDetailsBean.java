package com.wd.health.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/14<p>
 * <p>更改时间：2019/12/14<p>
 */
public class PatientDetailsBean {
    /**
     * result : {"amount":0,"authorName":"杨立朝最帅","content":"笑着面对生活","departmentId":2,"departmentName":"骨科","detail":"尴尬","disease":"类风湿性关节炎","id":1658,"picture":"http://172.17.8.100/images/health/user/sickCircle/2019-12-08/mUXMdm20191208211952.jpg","title":"如果","treatmentEndTime":1575734400000,"treatmentHospital":"你奶奶","treatmentProcess":"不鸟你","treatmentStartTime":1575734400000,"userId":397,"whetherContent":1}
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
         * authorName : 杨立朝最帅
         * content : 笑着面对生活
         * departmentId : 2
         * departmentName : 骨科
         * detail : 尴尬
         * disease : 类风湿性关节炎
         * id : 1658
         * picture : http://172.17.8.100/images/health/user/sickCircle/2019-12-08/mUXMdm20191208211952.jpg
         * title : 如果
         * treatmentEndTime : 1575734400000
         * treatmentHospital : 你奶奶
         * treatmentProcess : 不鸟你
         * treatmentStartTime : 1575734400000
         * userId : 397
         * whetherContent : 1
         */

        private String adoptComment;
        private int adoptCommentId;
        private int adoptFlag;
        private String adoptHeadPic;
        private String adoptNickName;
        private int amount;
        private int authorUserId;
        private int collectionFlag;
        private int collectionNum;
        private int commentNum;
        private String department;
        private int departmentId;
        private String detail;
        private String disease;
        private String picture;
        private int sickCircleId;
        private String title;
        private long treatmentEndTime;
        private String treatmentHospital;
        private String treatmentProcess;
        private long treatmentStartTime;

        public String getAdoptComment() {
            return adoptComment;
        }

        public void setAdoptComment(String adoptComment) {
            this.adoptComment = adoptComment;
        }

        public int getAdoptCommentId() {
            return adoptCommentId;
        }

        public void setAdoptCommentId(int adoptCommentId) {
            this.adoptCommentId = adoptCommentId;
        }

        public int getAdoptFlag() {
            return adoptFlag;
        }

        public void setAdoptFlag(int adoptFlag) {
            this.adoptFlag = adoptFlag;
        }

        public String getAdoptHeadPic() {
            return adoptHeadPic;
        }

        public void setAdoptHeadPic(String adoptHeadPic) {
            this.adoptHeadPic = adoptHeadPic;
        }

        public String getAdoptNickName() {
            return adoptNickName;
        }

        public void setAdoptNickName(String adoptNickName) {
            this.adoptNickName = adoptNickName;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAuthorUserId() {
            return authorUserId;
        }

        public void setAuthorUserId(int authorUserId) {
            this.authorUserId = authorUserId;
        }

        public int getCollectionFlag() {
            return collectionFlag;
        }

        public void setCollectionFlag(int collectionFlag) {
            this.collectionFlag = collectionFlag;
        }

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

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
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
    }
}
