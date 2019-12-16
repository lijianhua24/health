package com.wd.homemodel.bean;

public class FindBean {
    /**
     * result : {"approvalNumber":" 国药准字Z20026198 ","component":" 鱼腥草、黄芩、板蓝根、连翘、金银花。辅料为蔗糖、蜂蜜、苯甲酸钠、羟苯乙酯。 ","createTime":1547709515000,"drugsCategoryId":1,"effect":" 清热解毒。用于外感风热引起的咽喉疼痛；急性咽炎、扁桃腺炎有风热证候者。 ","id":21,"mindMatter":" 1. 忌烟酒、辛辣、鱼腥食物。 2. 不宜在服药期间同时服用温补性中药。 3. 孕妇慎用。儿童应在医师指导下服用。 4. 脾虚大便溏者慎用。 5. 属风寒感冒咽痛者，症见恶寒发热、无汗、鼻流清涕者慎用。 6. 扁桃体有化脓及全身高热者应去医院就诊。 7. 服药3天症状无缓解，应去医院就诊。 8. 对本品过敏者禁用，过敏体质者慎用。 9. 本品性状发生改变时禁止使用。 10. 儿童必须在成人监护下使用。 11. 请将本品放在儿童不能接触的地方。 12. 如正在使用其他药品，使用本品前请咨询医师或药师。 13. 本品贮存期间允许有少量摇之易散的沉淀。 ","name":" [惠松]复方鱼腥草合剂 ","packing":" 10mlx10支 ","picture":"https://imgq.ddky.com/c/product/130819/big/z_1.jpg?t=1544179386983&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100","shape":" 本品为黄棕色至棕色的液体；味甜、微苦涩。 ","sideEffects":" 尚不明确。 ","storage":" 密封，置阴凉处(不超过20℃)。 ","taboo":" 糖尿病患者禁服。 ","usage":" 口服，一次20～30毫升，一日3次。 "}
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
         * approvalNumber :  国药准字Z20026198
         * component :  鱼腥草、黄芩、板蓝根、连翘、金银花。辅料为蔗糖、蜂蜜、苯甲酸钠、羟苯乙酯。
         * createTime : 1547709515000
         * drugsCategoryId : 1
         * effect :  清热解毒。用于外感风热引起的咽喉疼痛；急性咽炎、扁桃腺炎有风热证候者。
         * id : 21
         * mindMatter :  1. 忌烟酒、辛辣、鱼腥食物。 2. 不宜在服药期间同时服用温补性中药。 3. 孕妇慎用。儿童应在医师指导下服用。 4. 脾虚大便溏者慎用。 5. 属风寒感冒咽痛者，症见恶寒发热、无汗、鼻流清涕者慎用。 6. 扁桃体有化脓及全身高热者应去医院就诊。 7. 服药3天症状无缓解，应去医院就诊。 8. 对本品过敏者禁用，过敏体质者慎用。 9. 本品性状发生改变时禁止使用。 10. 儿童必须在成人监护下使用。 11. 请将本品放在儿童不能接触的地方。 12. 如正在使用其他药品，使用本品前请咨询医师或药师。 13. 本品贮存期间允许有少量摇之易散的沉淀。
         * name :  [惠松]复方鱼腥草合剂
         * packing :  10mlx10支
         * picture : https://imgq.ddky.com/c/product/130819/big/z_1.jpg?t=1544179386983&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100
         * shape :  本品为黄棕色至棕色的液体；味甜、微苦涩。
         * sideEffects :  尚不明确。
         * storage :  密封，置阴凉处(不超过20℃)。
         * taboo :  糖尿病患者禁服。
         * usage :  口服，一次20～30毫升，一日3次。
         */

        private String approvalNumber;
        private String component;
        private long createTime;
        private int drugsCategoryId;
        private String effect;
        private int id;
        private String mindMatter;
        private String name;
        private String packing;
        private String picture;
        private String shape;
        private String sideEffects;
        private String storage;
        private String taboo;
        private String usage;

        public String getApprovalNumber() {
            return approvalNumber;
        }

        public void setApprovalNumber(String approvalNumber) {
            this.approvalNumber = approvalNumber;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDrugsCategoryId() {
            return drugsCategoryId;
        }

        public void setDrugsCategoryId(int drugsCategoryId) {
            this.drugsCategoryId = drugsCategoryId;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMindMatter() {
            return mindMatter;
        }

        public void setMindMatter(String mindMatter) {
            this.mindMatter = mindMatter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPacking() {
            return packing;
        }

        public void setPacking(String packing) {
            this.packing = packing;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getSideEffects() {
            return sideEffects;
        }

        public void setSideEffects(String sideEffects) {
            this.sideEffects = sideEffects;
        }

        public String getStorage() {
            return storage;
        }

        public void setStorage(String storage) {
            this.storage = storage;
        }

        public String getTaboo() {
            return taboo;
        }

        public void setTaboo(String taboo) {
            this.taboo = taboo;
        }

        public String getUsage() {
            return usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }
    }
}
