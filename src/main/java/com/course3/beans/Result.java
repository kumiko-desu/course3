package com.course3.beans;

public class Result {
    /** 是否成功 */
    private boolean success;
    /** 返回码 */
    private Integer code;
    /** 返回信息 */
    private String msg;
    /** 返回数据 */
    private Object data;

    public Result() {
        this.success = false;
        this.code = -1;
        this.msg = "Error";
        this.data = null;
    }

    public Result(boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(boolean success, Integer code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

}
