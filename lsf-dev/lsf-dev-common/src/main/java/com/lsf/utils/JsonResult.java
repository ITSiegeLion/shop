package com.lsf.utils;

/**
 * 通用响应对象
 */
public class JsonResult {
    private int status;
    private String msg;
    private Object data;

    public JsonResult() {}

    public JsonResult(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult getSuccessResult(){
        return new JsonResult(200, "success", null);
    }

    public static JsonResult getSuccessResult(Object data){
        return new JsonResult(200, "success", data);
    }

    public static JsonResult getErrorResult(){
        return new JsonResult(400, "error", null);
    }

    public static JsonResult getErrorResult(int status, String msg){
        return new JsonResult(status, msg, null);
    }

    public static JsonResult getErrorResult(String msg){
        return new JsonResult(400, msg, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
