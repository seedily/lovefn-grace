package com.lovefn.grace.tiny.callback;

import com.lovefn.grace.common.api.entity.Response;
import com.lovefn.grace.tiny.exception.ServiceFailException;

/**
 * 代理模式：实现ServiceCallback（目标对象）
 *
 * @param <T>
 */
public interface ServiceCallback<T> {

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
    @SuppressWarnings("unchecked")
    T executeService() throws ServiceFailException;


    /**
     * 针对执行成功的情况
     */
    default Response initSuccessResult(Object resultVo) {
        return Response.buildOk().withData(resultVo);
    }

    /**
     * 针对执行失败的情况
     */
    default Response initFailResult(ServiceFailException e) {
        return Response.buildFail(e.getCode(), e.getMsg());
    }

    /**
     * 针对执行时异常的情况
     */
    default Response initErrorResult(Exception e) {
        return Response.buildError("系统繁忙");
    }

}
