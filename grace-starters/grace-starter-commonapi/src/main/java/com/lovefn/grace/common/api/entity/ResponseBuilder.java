package com.lovefn.grace.common.api.entity;

import java.util.List;

public class ResponseBuilder {

    /**
     * 构造成功的vo
     */
    public static Response ok() {
        Response response = new Response();
        response.setResCode(ResultCode.SUCCESS.getCode());
        response.setResMsg(ResultCode.SUCCESS.getMsg());
        return response;
    }

    /**
     * 构造成功的vo
     */
    public static Response<Object> ok(Object obj) {
        Response<Object> response = new Response<>();
        response.setResCode(ResultCode.SUCCESS.getCode());
        response.setResMsg(ResultCode.SUCCESS.getMsg());
        response.setData(obj);
        return response;
    }

    /**
     * 构造成功的vo
     */
    public static Response<List> ok(List list) {
        Response<List> response = new Response<List>();
        response.setResCode(ResultCode.SUCCESS.getCode());
        response.setResMsg(ResultCode.SUCCESS.getMsg());
        response.setData(list);
        return response;
    }

    /**
     * 构造成功的vo
     */
    public static Response<Page> okPage(Page pageVo) {
        Response<Page> response = new Response<Page>();
        response.setResCode(ResultCode.SUCCESS.getCode());
        response.setResMsg(ResultCode.SUCCESS.getMsg());
        response.setData(pageVo);
        return response;
    }

    /**
     * 构造成功的vo
     */
    public static Response<Page> okPage(List pageList, int totalCount, int page, int pageSize) {
        Response<Page> response = new Response<Page>();
        response.setResCode(ResultCode.SUCCESS.getCode());
        response.setResMsg(ResultCode.SUCCESS.getMsg());
        Page pageVo = Page.builder()
                .page(page)
                .pageSize(pageSize)
                .pageData(pageList)
                .build();
        response.setData(pageVo);
        return response;
    }

    /**
     * 构造vo
     */
    public static Response fail(ResultCode resultCode) {
        Response response = new Response();
        response.setResCode(resultCode.getCode());
        response.setResMsg(resultCode.getMsg());
        return response;
    }

    /**
     * 构造vo
     */
    public static Response fail(ResultCode resultCode, String msg) {
        Response response = new Response();
        response.setResCode(resultCode.getCode());
        response.setResMsg(msg);
        return response;
    }

    /**
     * 构造vo
     */
    public static Response fail(String code, String msg) {
        Response response = new Response();
        response.setResCode(code);
        response.setResMsg(msg);
        return response;
    }

    /**
     * 构造vo
     */
    public static Response err(String msg) {
        Response response = new Response();
        response.setResCode(ResultCode.SYS_ERROR.getCode());
        response.setResMsg(msg);
        return response;
    }

}
