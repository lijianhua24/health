package com.wd.doctor.bean;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/26<p>
 * <p>更改时间：2019/12/26<p>
 */
public class QueryIdBean {

    /**
     * result : {"idNumber":"k6QbQP48DLZalMzLRpZr5Zyl3saqZHCYhzsZK45sNDH4zZSjzeQ7AC7kuZEcmTzwAvfAeZcrD0aY3us/xDamHj6b0WsYbmasB7bQ3AJvcezf6c6NHcyzQi9k4cdbV7+Ycr6OFlINnCeniCa3311X29/s++GxAjDetCTmrLmavuo=","name":"hu3y5rayjU8HVNweFljNiGM3Nj7lM0wA9otRLtKW5/3+RVeNglBu9n+m8zgAVevhvXccFdLG6Il+jpYDHY5YHQ2Tt0Qy1+06FA6nQew0SBFp9xQ7rOsXzDYTgB83g4UrmkCFc0I989aarunQfbbROGb745NtjmpEBmdmlzwTKBw=","nation":"hnujmt+e/cKXjnQY35qYeGTpbKByW9W8GvooFEm9ljYZfsoBWmGIvoR+fQ+MOEezvr9wWGNiecf/dYwSykQhZIn6jddGbOBp6PPkF56QYYRSot/O6sMMDFKWp2t1BI34uFLPJJow65l3fzuX5LzJDjGVnuQdieznoxLC0pKx0Ig=","sex":"lihIj522nrHyuweP57iS3iAX7YOJNtvR2McfuNf+qZv0LYzgYsbn0jWYE9ogyilujwFMmSIt6LJk+9dGPLTQ/0l7siRaVAN33fPNRdDrGI+LHyCGAVylBNtQcEWXFiFbYW6OBQOrsOUBJvJkMvdDzMdHWG5f3cT5yUDnecLNRig="}
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
         * idNumber : k6QbQP48DLZalMzLRpZr5Zyl3saqZHCYhzsZK45sNDH4zZSjzeQ7AC7kuZEcmTzwAvfAeZcrD0aY3us/xDamHj6b0WsYbmasB7bQ3AJvcezf6c6NHcyzQi9k4cdbV7+Ycr6OFlINnCeniCa3311X29/s++GxAjDetCTmrLmavuo=
         * name : hu3y5rayjU8HVNweFljNiGM3Nj7lM0wA9otRLtKW5/3+RVeNglBu9n+m8zgAVevhvXccFdLG6Il+jpYDHY5YHQ2Tt0Qy1+06FA6nQew0SBFp9xQ7rOsXzDYTgB83g4UrmkCFc0I989aarunQfbbROGb745NtjmpEBmdmlzwTKBw=
         * nation : hnujmt+e/cKXjnQY35qYeGTpbKByW9W8GvooFEm9ljYZfsoBWmGIvoR+fQ+MOEezvr9wWGNiecf/dYwSykQhZIn6jddGbOBp6PPkF56QYYRSot/O6sMMDFKWp2t1BI34uFLPJJow65l3fzuX5LzJDjGVnuQdieznoxLC0pKx0Ig=
         * sex : lihIj522nrHyuweP57iS3iAX7YOJNtvR2McfuNf+qZv0LYzgYsbn0jWYE9ogyilujwFMmSIt6LJk+9dGPLTQ/0l7siRaVAN33fPNRdDrGI+LHyCGAVylBNtQcEWXFiFbYW6OBQOrsOUBJvJkMvdDzMdHWG5f3cT5yUDnecLNRig=
         */

        private String idNumber;
        private String name;
        private String nation;
        private String sex;

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
