package com.wd.health.bean;

import java.util.List;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/15 20:39
 */
public class UserTaskListBean {

    /**
     * result : [{"id":1001,"reward":1,"taskName":"签到","taskType":1,"whetherFinish":2,"whetherReceive":3},{"id":1002,"reward":10,"taskName":"每日病友圈首评","taskType":1,"whetherFinish":2,"whetherReceive":3},{"id":1003,"reward":10,"taskName":"每日首发病友圈","taskType":1,"whetherFinish":2,"whetherReceive":3},{"id":1004,"reward":30,"taskName":"完善档案","taskType":2,"whetherFinish":2,"whetherReceive":3},{"id":1005,"reward":10,"taskName":"参与健康测评","taskType":2,"whetherFinish":2,"whetherReceive":3},{"id":1006,"reward":20,"taskName":"绑定身份证","taskType":2,"whetherFinish":2,"whetherReceive":3},{"id":1007,"reward":20,"taskName":"绑定银行卡","taskType":2,"whetherFinish":2,"whetherReceive":3}]
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
         * id : 1001
         * reward : 1
         * taskName : 签到
         * taskType : 1
         * whetherFinish : 2
         * whetherReceive : 3
         */

        private int id;
        private int reward;
        private String taskName;
        private int taskType;
        private int whetherFinish;
        private int whetherReceive;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }

        public int getWhetherFinish() {
            return whetherFinish;
        }

        public void setWhetherFinish(int whetherFinish) {
            this.whetherFinish = whetherFinish;
        }

        public int getWhetherReceive() {
            return whetherReceive;
        }

        public void setWhetherReceive(int whetherReceive) {
            this.whetherReceive = whetherReceive;
        }
    }
}
