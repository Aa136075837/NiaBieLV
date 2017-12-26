package com.example.bo.niabielv.bean;

import java.util.List;

/**
 * Created by bo on 2017/12/24.
 */
public class AccountBean {

    /**
     * allMoney : 325
     * list : [{"actor":"1，2，3，4","average":"0.0","date_time":"2017-12-23","money":"100.0","money_person":"驴"},{"actor":"1，2，3，4","average":"100.0","date_time":"2017-12-24","money":"100.0","money_person":"驴"},{"actor":"张小龙,曹仪佳,杨月月,MacYang,nia驴","average":"25.0","content":"买菜，吃饭","date_time":"2017-12-26","money":"125.0","money_person":"MacYang"}]
     */

    private double allMoney;
    private List<ListBean> list;

    public double getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(int allMoney) {
        this.allMoney = allMoney;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * actor : 1，2，3，4
         * average : 0.0
         * date_time : 2017-12-23
         * money : 100.0
         * money_person : 驴
         * content : 买菜，吃饭
         */

        private String actor;
        private String average;
        private String date_time;
        private String money;
        private String money_person;
        private String content;

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoney_person() {
            return money_person;
        }

        public void setMoney_person(String money_person) {
            this.money_person = money_person;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
