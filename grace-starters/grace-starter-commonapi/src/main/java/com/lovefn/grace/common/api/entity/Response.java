package com.lovefn.grace.common.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Response<T> implements Serializable {

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
    private T data;


    /**
     * 序列化消息
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{\"resCode\":%s,\"resMsg\":\"%s\",\"data\":\"%s\"}", resCode, resMsg, data);
    }

}
