package com.lovefn.grace.common.service.response;

/**
 * 结果码
 */
@SuppressWarnings({"JavadocVariable"}) // for checkstyle#JavadocVariable
public class ResultCode {

    public static final ResultCode SUCCESS = new ResultCode("000000", "成功");
    public static final ResultCode MISS_PARAM = new ResultCode("000400", "请求参数非法");
    public static final ResultCode NOT_FOUND = new ResultCode("000404", "查询为空");
    public static final ResultCode ILLEGAL_AUTH = new ResultCode("000403", "权限非法");
    public static final ResultCode RUN_TIMEOUT = new ResultCode("000408", "执行超时");
    public static final ResultCode SYS_ERROR = new ResultCode("000500", "系统异常");
    public static final ResultCode NETWORK_ERROR = new ResultCode("000504", "网络异常");
    public static final ResultCode VERSION_ERROR = new ResultCode("000505", "版本非法");

    /**
     * Constructor.
     */
    public ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
