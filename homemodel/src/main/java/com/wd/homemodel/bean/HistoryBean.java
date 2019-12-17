package com.wd.homemodel.bean;

public class HistoryBean {
    String name;

    public HistoryBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HistoryBean() {
    }

    @Override
    public String toString() {
        return "HistoryBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
