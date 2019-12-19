package com.wd.doctor.view;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class az {
    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("email","xcx485131108@163.com");
        params.put("code","057221");
        params.put("name","呆志3");
        params.put("inauguralHospital","清华大学附属医院");
        params.put("departmentName","小儿科");
        params.put("jobTitle","主任");
        params.put("personalProfile","在世华佗");
        params.put("goodField","小儿科soeasy");
        Gson gson = new Gson();
        String s = gson.toJson(params);//map转json字符串
        System.out.println(s);//{"a":"11111","b":"22222"}

    }
}
