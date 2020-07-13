package com.lovefn.grace.common.api.entity;

import lombok.Getter;

/**
 * 基础结果码（后缀）
 * 对于一组业务结果码，应该遵循如下格式: [4位系统码][3位业务码][基础结果码]
 */
@SuppressWarnings({"JavadocVariable"}) // for checkstyle#JavadocVariable
@Getter
public class ResultCode {

    public static final ResultCode SUCCESS = new ResultCode("200", "成功");
    public static final ResultCode ILLEGAL_PARAM = new ResultCode("400", "请求参数非法");
    public static final ResultCode ILLEGAL_AUTH = new ResultCode("401", "权限非法");
    public static final ResultCode FORBIDDEN = new ResultCode("403", "数据不允许访问");
    public static final ResultCode NOT_FOUND = new ResultCode("404", "查询为空");
    public static final ResultCode RUN_TIMEOUT = new ResultCode("408", "执行超时");
    public static final ResultCode DATA_EXIST = new ResultCode("409", "数据已存在");
    public static final ResultCode DATA_READONLY = new ResultCode("409", "数据不允许修改");
    public static final ResultCode DATA_UNSUPPORTED = new ResultCode("415", "数据不支持");
    public static final ResultCode SYS_ERROR = new ResultCode("500", "系统异常");
    public static final ResultCode OUT_OF_RANGE = new ResultCode("503", "数据超出限制");
    public static final ResultCode GATEWAY_FAIL = new ResultCode("504", "网络异常");
    public static final ResultCode VERSION_ERROR = new ResultCode("505", "版本非法");

    /**
     * Constructor.
     */
    public ResultCode(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    private String code;
    private String msg;

}
