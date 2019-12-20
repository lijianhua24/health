package com.wd.health.bean;

import java.util.List;

public class DrugBean {
    /**
     * result : [{"drugsCategoryId":1,"id":1,"name":" [同仁堂]牛黄解毒片(薄膜衣片) ","picture":"https://imgq.ddky.com/c/product/328654/big/z_1.jpg?t=9898&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":2,"name":" [以岭]连花清瘟胶囊 ","picture":"https://imgq.ddky.com/c/product/500186/big/z_1.jpg?t=1534842302335&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":3,"name":" [仁和]抗病毒口服液 ","picture":"https://imgq.ddky.com/c/product/509512/big/z_1.jpg?t=1489473551400&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":4,"name":" [仁和可立克]风热感冒颗粒 ","picture":"https://imgq.ddky.com/c/product/128150/big/z_1.jpg?t=1489743264412&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":5,"name":" [济川]蒲地蓝消炎口服液 ","picture":"https://imgq.ddky.com/c/product/500578/big/z_1.jpg?t=9898&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":6,"name":" [三精]双黄连口服液 ","picture":"https://imgq.ddky.com/c/product/105703/big/z_1.jpg?t=1515723013282&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":7,"name":" [吉春]炎可宁片 ","picture":"https://imgq.ddky.com/c/product/131078/big/z_1.jpg?t=1490843321491&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":8,"name":" [仁和]穿心莲胶囊 ","picture":"https://imgq.ddky.com/c/product/129741/big/z_1.jpg?t=1540435145351&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":9,"name":" [同仁堂]牛黄上清丸 ","picture":"https://imgq.ddky.com/c/product/233340/big/z_1.jpg?t=1489457500505&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"},{"drugsCategoryId":1,"id":10,"name":" [仁和]清开灵颗粒 ","picture":"https://imgq.ddky.com/c/product/535232/big/z_1.jpg?t=1525854698935&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100"}]
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
         * drugsCategoryId : 1
         * id : 1
         * name :  [同仁堂]牛黄解毒片(薄膜衣片)
         * picture : https://imgq.ddky.com/c/product/328654/big/z_1.jpg?t=9898&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100
         */

        private int drugsCategoryId;
        private int id;
        private String name;
        private String picture;

        public int getDrugsCategoryId() {
            return drugsCategoryId;
        }

        public void setDrugsCategoryId(int drugsCategoryId) {
            this.drugsCategoryId = drugsCategoryId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
