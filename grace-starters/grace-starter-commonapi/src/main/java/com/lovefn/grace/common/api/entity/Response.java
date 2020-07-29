package com.lovefn.grace.common.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Response<D> implements Serializable {

    private static final long serialVersionUID = 77843211333671L;

    /**
     * 返回码
     */
    private String resCode;

    /**
     * 返回说明
     */
    private String resMsg;

    /**
     * 结果数据 : obj/list/page
     */
    private D data;


    /**
     * data赋值
     */
    public Response withData(D obj) {
        setData(obj);
        return this;
    }

    /**
     * 序列化消息
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{\"resCode\":%s,\"resMsg\":\"%s\",\"data\":\"%s\"}", resCode, resMsg, data);
    }



    /************** build集装箱方法 *****************************/


    /**
     * 构造成功的vo
     */
    public static Response buildOk() {
        Response response = new Response();
        response.setResCode(ResultCode.SUCCESS.getCode());
        response.setResMsg(ResultCode.SUCCESS.getMsg());
        return response;
    }
    

    /**
     * 构造Response
     */
    public static Response buildFail(ResultCode resultCode) {
        Response response = new Response();
        response.setResCode(resultCode.getCode());
        response.setResMsg(resultCode.getMsg());
        return response;
    }

    /**
     * 构造Response
     */
    public static Response buildFail(ResultCode resultCode, String msg) {
        Response response = new Response();
        response.setResCode(resultCode.getCode());
        response.setResMsg(msg);
        return response;
    }

    /**
     * 构造Response
     */
    public static Response buildFail(String code, String msg) {
        Response response = new Response();
        response.setResCode(code);
        response.setResMsg(msg);
        return response;
    }

    /**
     * 构造Response
     */
    public static Response buildError(String msg) {
        Response response = new Response();
        response.setResCode(ResultCode.SYS_ERROR.getCode());
        response.setResMsg(msg);
        return response;
    }

}
