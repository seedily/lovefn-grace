package com.lovefn.grace.common.service.callback;

import com.lovefn.grace.common.service.exception.ServiceFailException;
import com.lovefn.grace.common.service.response.Response;
import com.lovefn.grace.common.service.response.ResponseBuilder;
import com.lovefn.grace.common.service.response.ResultData;

/**
 * 代理模式：实现ServiceCallback（目标对象）
 * Created by Moffee on 2018/7/11.
 */
public abstract class AbstractServiceCallback implements ServiceCallback {

    @Override
    public void lock() {
        // Please override if necessary
    }


    @Override
    public void unlock() {
        // Please override if necessary
    }


    /**
     * 针对执行失败的情况
     */
    @Override
    public Response initSuccessResult(ResultData resultData) {
        return ResponseBuilder.createSuccessRes(resultData);
    }

    /**
     * 针对执行失败的情况
     */
    @Override
    public Response initFailResult(ServiceFailException e) {
        return ResponseBuilder.createFailRes(e);
    }

    /**
     * 针对执行时异常的情况
     */
    @Override
    public Response initErrorResult(Exception e) {
        return ResponseBuilder.createErrorRes(e);
    }

}
