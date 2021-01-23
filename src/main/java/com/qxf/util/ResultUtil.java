package com.qxf.util;

/**
 * @ClassName ResultUtil
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/20 22:59
 **/
public class ResultUtil {
    private Integer code;
    private String msg;
    private Object data;

    public ResultUtil(Integer code, String msg) {
        this(code,msg,null);
    }

    public ResultUtil(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultUtil ok(String msg){
        return new ResultUtil(200,msg);
    }

    public static ResultUtil ok(Object data){
        return new ResultUtil(200,"成功",data);
    }

    public static ResultUtil ok(String msg,Object data){
        return new ResultUtil(200,msg,data);
    }

    public static ResultUtil ok(Integer code, String msg){
        return new ResultUtil(code,msg,null);
    }

    public static ResultUtil ok(Integer code, String msg,Object data){
        return new ResultUtil(code,msg,data);
    }

    public static ResultUtil error(String msg){
        return new ResultUtil(500,msg);
    }

    public static ResultUtil error(Integer code, String msg){
        return new ResultUtil(code,msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
