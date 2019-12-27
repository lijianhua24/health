package com.wd.health.bean;

import java.util.List;

public class QvideoListBean {

    /**
     * result : [{"content":"很好啊","id":989},{"content":"很好啊","id":1024},{"content":"很好啊","id":1048},{"content":"很好啊","id":1055},{"content":"很好啊","id":1067},{"content":"很好啊","id":1088},{"content":"很好啊","id":1097},{"content":"很好啊","id":1110},{"content":"很好啊","id":1120},{"content":"很好啊","id":1133},{"content":"很好啊","id":1168},{"content":"很好啊","id":1175},{"content":"很好啊","id":1185},{"content":"很好啊","id":1194},{"content":"很好啊","id":1206},{"content":"很好啊","id":1216},{"content":"很好啊","id":1221},{"content":"很好啊","id":1224},{"content":"很好啊","id":1235},{"content":"很好啊","id":1240},{"content":"很好啊","id":1245},{"content":"很好啊","id":1248},{"content":"很好啊","id":1253},{"content":"很好啊","id":1269},{"content":"很好啊","id":1284},{"content":"很好啊","id":1299},{"content":"很好啊","id":1325},{"content":"很好啊","id":1337},{"content":"很好啊","id":1363},{"content":"很好啊","id":1384},{"content":"很好啊","id":1389},{"content":"很好啊","id":1390},{"content":"很好啊","id":1404},{"content":"很好啊","id":1444},{"content":"很好啊","id":1445},{"content":"很好啊","id":1464},{"content":"很好啊","id":1476},{"content":"很好啊","id":1480},{"content":"很好啊","id":1553},{"content":"很好啊","id":1554},{"content":"很好啊","id":1563},{"content":"很好啊","id":1567},{"content":"很好啊","id":1574},{"content":"很好啊","id":1575},{"content":"很好啊","id":1693},{"content":"很好啊","id":1696},{"content":"很好啊","id":1723},{"content":"很好啊","id":1731},{"content":"很好啊","id":1794},{"content":"很好啊","id":1835},{"content":"很好啊","id":1840},{"content":"很好啊","id":1853},{"content":"很好啊","id":1863},{"content":"很好啊","id":1866}]
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
         * content : 很好啊
         * id : 989
         */

        private String content;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
