package com.lovefn.grace.common.service.exception;

import com.lovefn.grace.common.service.response.ResultCode;

/**
 * 业务异常
 */
public class ServiceFailException extends RuntimeException {

    private ResultCode resultCode;
    private String errorMsg;

    /**
     * Constructor.
     *
     * @param resultCode
     * @param errorMsg
     */
    public ServiceFailException(ResultCode resultCode, String errorMsg) {
        super(errorMsg);
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
