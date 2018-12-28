package com.spring.ghost.bean;

public class MakerResp {

    //操作成功
    public static final int SUCCESS = 200;
    //操作失败
    public static final int FAILED = 500;

    private int code;
    private String msg;
    private Object data;


    public static MakerResp success() {
        return success(null);
    }

    public static MakerResp success(Object obj) {
        MakerResp makerResp = new MakerResp();
        makerResp.data = obj;
        makerResp.msg = "请求成功";
        makerResp.code = SUCCESS;
        return makerResp;
    }


    public static MakerResp failed() {
        return failed(FAILED, "请求失败");
    }

    public static MakerResp failed(String msg) {
        return failed(FAILED, msg);
    }


    public static MakerResp failed(int code, String msg) {
        MakerResp makerResp = new MakerResp();
        makerResp.code = code;
        makerResp.msg = msg;
        makerResp.data = null;
        return makerResp;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
