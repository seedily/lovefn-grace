package com.lovefn.grace.tiny.exception;


import com.lovefn.grace.common.api.entity.ResultCode;

/**
 * 严重业务失败，建议打error日志
 */
public class ServiceErrorException extends ServiceFailException {

    public ServiceErrorException(ResultCode resultCode) {
        super(resultCode);
    }

    public ServiceErrorException(String code, String errorMsg) {
        super(code, errorMsg);
    }
}
