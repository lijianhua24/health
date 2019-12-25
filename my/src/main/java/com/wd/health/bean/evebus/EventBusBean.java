package com.wd.health.bean.evebus;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/18 14:01
 */
public class EventBusBean {
    String name;
    String sex;

    public EventBusBean(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
