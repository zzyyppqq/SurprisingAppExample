package com.zyp.bean;

import java.io.Serializable;

/**
 * Created by zhangyipeng on 2017/6/15.
 */

public class BaseEntry implements Serializable{
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseEntry{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
