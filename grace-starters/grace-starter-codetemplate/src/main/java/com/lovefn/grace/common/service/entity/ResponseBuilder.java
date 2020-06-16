package com.lovefn.grace.common.service.entity;


import com.lovefn.grace.common.service.exception.ServiceFailException;

import java.io.Serializable;

/**
 * 结果对象
 */
public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    /**
     * To create success res with the object extends BaseResult
     */
    public static <T extends IBaseResultVo> Response<T> createSuccessRes(T data) {
        return new Response<T>(true, ResultCode.SUCCESS, data);
    }


    /**
     * To create fail entity with ServiceFailException
     */
    public static <T extends IBaseResultVo> Response<T> createFailRes(ServiceFailException e) {
        return new Response<T>(false, e.getResultCode(), e.getErrorMsg());
    }


    /**
     * To create error entity with Exception
     */
    public static <T extends IBaseResultVo> Response<T> createErrorRes(Exception e) {
        return new Response<T>(false, ResultCode.SYS_ERROR, e.getMessage());
    }

}
