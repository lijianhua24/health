package com.wd.health.bean.evebus;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/18 15:33
 */
public class SeekBarBean {
    String seekBar;
    String age;
    String weight;

    public SeekBarBean(String seekBar, String age, String weight) {
        this.seekBar = seekBar;
        this.age = age;
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(String seekBar) {
        this.seekBar = seekBar;
    }
}
