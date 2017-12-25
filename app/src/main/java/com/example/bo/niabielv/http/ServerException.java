package com.example.bo.niabielv.http;

/**
 * @author zsp
 * @date 2016/8/10 0010
 * @description
 */
public class ServerException extends IllegalAccessException {

    private int code;
    private String message;

    public ServerException(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
