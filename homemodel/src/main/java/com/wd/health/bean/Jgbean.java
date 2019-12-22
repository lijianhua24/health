package com.wd.health.bean;

public class Jgbean {
    /**
     * text : 1234
     * extras : {}
     */

    private String text;
    private ExtrasBean extras;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ExtrasBean getExtras() {
        return extras;
    }

    public void setExtras(ExtrasBean extras) {
        this.extras = extras;
    }

    public static class ExtrasBean {
    }
}
