package com.lovefn.grace.common.service.response;


import com.lovefn.grace.common.service.exception.ServiceFailException;

/**
 * 结果对象
 */
public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    /**
     * To create success res with the object extends ResultData
     */
    public static <T extends ResultData> Response<T> createSuccessRes(T data) {
        return new Response<T>(true, ResultCode.SUCCESS, data);
    }


    /**
     * To create fail response with ServiceFailException
     */
    public static <T extends ResultData> Response<T> createFailRes(ServiceFailException e) {
        return new Response<T>(false, e.getResultCode(), e.getErrorMsg());
    }


    /**
     * To create error response with Exception
     */
    public static <T extends ResultData> Response<T> createErrorRes(Exception e) {
        return new Response<T>(false, ResultCode.SYS_ERROR, e.getMessage());
    }

}
