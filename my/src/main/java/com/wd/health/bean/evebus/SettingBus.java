package com.wd.health.bean.evebus;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/18 21:32
 */
public class SettingBus {
    String name;
    String image;

    public SettingBus(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
