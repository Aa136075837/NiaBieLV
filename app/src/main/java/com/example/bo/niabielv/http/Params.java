package com.example.bo.niabielv.http;

import java.util.LinkedHashMap;

import okhttp3.RequestBody;

/**
 * @author zsp
 * @date 2016/12/13 0013
 * @description 参数封装类
 */
public class Params {
    private LinkedHashMap<String, Object> hashMap;
    private String method;
    private boolean isAddId = true;

    public Params() {
    }

    public Params(String method) {
        this.method = method;
    }

    public Params(Enum anEnum) {
        method = anEnum.toString();
    }

    public Params(String method, LinkedHashMap<String, Object> map) {
        this.method = method;
        this.hashMap = map;
    }

    public Params(String method, boolean isAddId) {
        this.method = method;
        this.isAddId = isAddId;
    }

    public Params addParams(String key, Object value) {
        if (hashMap == null) {
            hashMap = new LinkedHashMap<>();
        }
        hashMap.put(key, value);
        return this;
    }

    public LinkedHashMap<String, Object> getHashMap() {
        return hashMap;
    }

    public void setHashMap(LinkedHashMap<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isAddId() {
        return isAddId;
    }

    /**
     * 是否自动添加loginId
     *
     * @param addId  normal is <code>true</code>
     */
    public Params setAddId(boolean addId) {
        isAddId = addId;
        return this;
    }

    public RequestBody create() {
        return RequestUtils.getParams(hashMap);
    }
}
