package com.ruifeng.entity;
import java.io.Serializable;

/**
 * 描述:
 * @author Administrator
 * @create 2018-10-10 15:20
 */
public class Result<T> implements Serializable {
    //响应码 200为成功
    private int resultCode;
    //响应msg
    private String message;
    //返回数据
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
