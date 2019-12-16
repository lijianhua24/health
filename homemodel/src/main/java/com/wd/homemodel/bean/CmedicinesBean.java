package com.wd.homemodel.bean;

public class CmedicinesBean {
    /**
     * result : {"benefitTaboo":" 1．患者应卧床静养，饮食清淡温和，要大量饮水。 2．患者的年龄很小，或是身体极为衰弱，就必须住院，以静脉注射输入药物及液体的方式接受治疗。 3．治疗必须彻底，疗程一般不应少于1周，切勿稍有好转就停药，以免转为慢性肾盂肾炎。","chineseMedicineTreatment":" 1．治疗肾盂肾炎尿频、尿急、排尿痛、口渴等。泽泻、茯苓、滑石、猪芩各9克，或以此四味再加四物汤煎服更有疗效。水煎服，每日一剂。 2．肾盂肾炎属于亚急性，而有不断发热、口渴、自汗、脐上动悸亢进、严重尿混浊等现象时，取白术9克，地黄、知母、当归、芍药、天门冬、麦门冬、黄柏、陈皮各7.5克，甘草、大枣各4.5克。水煎取汁，分两次服。 3．治肾区有疼痛、压痛、肿胀感，且有便秘等。大黄6~12克，牡丹皮、桃仁、芒硝各12克，冬瓜子18克。煎取汁，分二次服用。 4．治疗肾盂肾炎，心脏有压痛，寒热往来，恶心呕吐等。黄芩6克，人参6克，半夏12克，芍药6克，柴胡15克，桂枝7.5克，生姜、大枣各6克，甘草4.5克。水煎取汁，分二次温服。 5．肾盂肾炎有往来寒热、舌白苔、恶心或呕吐、食欲不振等现象时，取人参12克，半夏9克，黄芩9克，柴胡12克，炙甘草6克，生姜4片，大枣6枚。水煎取汁，分二次温服。 6．症状类似前方，且有腹痛、便秘、舌黄苔等现象者，取黄芩9克，枳实6克，芍药9克，柴胡12克，半夏9克，大黄6克，生姜4片，大枣4枚。水煎取汁，分二次服用。","createTime":1547105863000,"diseaseCategoryId":191,"id":191,"pathology":"急性肾盂肾炎是突然发生的肾脏感染和炎症。当血流将身体另一部分的感染原带到肾脏时，就会发生这种疾病。在大多数情况下，感染性细菌来自尿道四周的皮肤，当细菌经尿道由膀胱扩散，就会经过输尿管进入肾脏。如果尿流受阻，也会发生此症。妇女患此病的特别多。妊娠、肾结石、膀胱肿瘤、前列腺肥大等，都容易使人患急性肾盂肾炎。","symptom":"背后腰上面的 方突然发生剧痛，通常身体一侧较中一侧疼得更厉害，并向下扩散到腹股沟部。体温急剧上升，常会达到40℃。并有寒颤、发抖、恶心及呕吐症状。还可能发生排尿困难或排尿痛的现象。尿液浑浊，如果有血渗入，还会呈现浅红色。","westernMedicineTreatment":" 1．控制感染：常用药物有呋喃咀啶、复方新诺明；氨苄青霉素加入液体中静滴。通常用药至症状消失、尿检阴性后继续用药3~5天。停药后坚持复检和随访，切忌过早停药或停药后不随访，以防感染复发或迁延不愈。如药效不理想，可考虑两药联用或改用在尿液中浓度高的抗菌药物，如杏体酸（或马尿酸）乌洛托品或长效磺胺制剂加甲氢苄氨嘧啶。 2．对症治疗：高热、头痛、腰痛明显者给退热镇痛剂。膀胱刺激症状严重者，口服碳酸氢钠。"}
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
         * benefitTaboo :  1．患者应卧床静养，饮食清淡温和，要大量饮水。 2．患者的年龄很小，或是身体极为衰弱，就必须住院，以静脉注射输入药物及液体的方式接受治疗。 3．治疗必须彻底，疗程一般不应少于1周，切勿稍有好转就停药，以免转为慢性肾盂肾炎。
         * chineseMedicineTreatment :  1．治疗肾盂肾炎尿频、尿急、排尿痛、口渴等。泽泻、茯苓、滑石、猪芩各9克，或以此四味再加四物汤煎服更有疗效。水煎服，每日一剂。 2．肾盂肾炎属于亚急性，而有不断发热、口渴、自汗、脐上动悸亢进、严重尿混浊等现象时，取白术9克，地黄、知母、当归、芍药、天门冬、麦门冬、黄柏、陈皮各7.5克，甘草、大枣各4.5克。水煎取汁，分两次服。 3．治肾区有疼痛、压痛、肿胀感，且有便秘等。大黄6~12克，牡丹皮、桃仁、芒硝各12克，冬瓜子18克。煎取汁，分二次服用。 4．治疗肾盂肾炎，心脏有压痛，寒热往来，恶心呕吐等。黄芩6克，人参6克，半夏12克，芍药6克，柴胡15克，桂枝7.5克，生姜、大枣各6克，甘草4.5克。水煎取汁，分二次温服。 5．肾盂肾炎有往来寒热、舌白苔、恶心或呕吐、食欲不振等现象时，取人参12克，半夏9克，黄芩9克，柴胡12克，炙甘草6克，生姜4片，大枣6枚。水煎取汁，分二次温服。 6．症状类似前方，且有腹痛、便秘、舌黄苔等现象者，取黄芩9克，枳实6克，芍药9克，柴胡12克，半夏9克，大黄6克，生姜4片，大枣4枚。水煎取汁，分二次服用。
         * createTime : 1547105863000
         * diseaseCategoryId : 191
         * id : 191
         * pathology : 急性肾盂肾炎是突然发生的肾脏感染和炎症。当血流将身体另一部分的感染原带到肾脏时，就会发生这种疾病。在大多数情况下，感染性细菌来自尿道四周的皮肤，当细菌经尿道由膀胱扩散，就会经过输尿管进入肾脏。如果尿流受阻，也会发生此症。妇女患此病的特别多。妊娠、肾结石、膀胱肿瘤、前列腺肥大等，都容易使人患急性肾盂肾炎。
         * symptom : 背后腰上面的 方突然发生剧痛，通常身体一侧较中一侧疼得更厉害，并向下扩散到腹股沟部。体温急剧上升，常会达到40℃。并有寒颤、发抖、恶心及呕吐症状。还可能发生排尿困难或排尿痛的现象。尿液浑浊，如果有血渗入，还会呈现浅红色。
         * westernMedicineTreatment :  1．控制感染：常用药物有呋喃咀啶、复方新诺明；氨苄青霉素加入液体中静滴。通常用药至症状消失、尿检阴性后继续用药3~5天。停药后坚持复检和随访，切忌过早停药或停药后不随访，以防感染复发或迁延不愈。如药效不理想，可考虑两药联用或改用在尿液中浓度高的抗菌药物，如杏体酸（或马尿酸）乌洛托品或长效磺胺制剂加甲氢苄氨嘧啶。 2．对症治疗：高热、头痛、腰痛明显者给退热镇痛剂。膀胱刺激症状严重者，口服碳酸氢钠。
         */

        private String benefitTaboo;
        private String chineseMedicineTreatment;
        private long createTime;
        private int diseaseCategoryId;
        private int id;
        private String pathology;
        private String symptom;
        private String westernMedicineTreatment;

        public String getBenefitTaboo() {
            return benefitTaboo;
        }

        public void setBenefitTaboo(String benefitTaboo) {
            this.benefitTaboo = benefitTaboo;
        }

        public String getChineseMedicineTreatment() {
            return chineseMedicineTreatment;
        }

        public void setChineseMedicineTreatment(String chineseMedicineTreatment) {
            this.chineseMedicineTreatment = chineseMedicineTreatment;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDiseaseCategoryId() {
            return diseaseCategoryId;
        }

        public void setDiseaseCategoryId(int diseaseCategoryId) {
            this.diseaseCategoryId = diseaseCategoryId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPathology() {
            return pathology;
        }

        public void setPathology(String pathology) {
            this.pathology = pathology;
        }

        public String getSymptom() {
            return symptom;
        }

        public void setSymptom(String symptom) {
            this.symptom = symptom;
        }

        public String getWesternMedicineTreatment() {
            return westernMedicineTreatment;
        }

        public void setWesternMedicineTreatment(String westernMedicineTreatment) {
            this.westernMedicineTreatment = westernMedicineTreatment;
        }
    }
}
