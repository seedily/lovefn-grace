package com.lovefn.grace.common.service.callback;

import com.lovefn.grace.common.service.entity.BaseResult;
import com.lovefn.grace.common.service.entity.Response;
import com.lovefn.grace.common.service.entity.ResponseBuilder;
import com.lovefn.grace.common.service.exception.ServiceFailException;

/**
 * 代理模式：实现ServiceCallback（目标对象）
 *
 * @param <T>
 */
public interface ServiceCallback<T extends BaseResult> {

    /**
     * 在executeService前加锁（需要Override）
     */
    default void lock() {
    }

    /**
     * 在executeService后解锁（需要Override）
     */
    default void unlock() {
    }

    /**
     * 执行体（建议先执行checker）
     *
     * @throws ServiceFailException
     */
    T executeService() throws ServiceFailException;


    /**
     * 针对执行失败的情况
     */
    default Response initSuccessResult(BaseResult baseResult) {
        return ResponseBuilder.createSuccessRes(baseResult);
    }

    /**
     * 针对执行失败的情况
     */
    default Response initFailResult(ServiceFailException e) {
        return ResponseBuilder.createFailRes(e);
    }

    /**
     * 针对执行时异常的情况
     */
    default Response initErrorResult(Exception e) {
        return ResponseBuilder.createErrorRes(e);
    }

}
