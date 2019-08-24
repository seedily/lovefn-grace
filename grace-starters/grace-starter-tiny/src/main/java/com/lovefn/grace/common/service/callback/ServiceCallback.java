package com.lovefn.grace.common.service.callback;

import com.lovefn.grace.common.service.exception.ServiceFailException;
import com.lovefn.grace.common.service.response.Response;
import com.lovefn.grace.common.service.response.ResultData;

/**
 * 代理模式：实现ServiceCallback（目标对象）
 *
 * @param <T>
 */
public interface ServiceCallback<T extends ResultData> {

    /**
     * 参数校验
     */
    void executeCheck() throws ServiceFailException;

    /**
     * 加锁
     */
    void lock();

    /**
     * 执行
     *
     * @throws ServiceFailException
     */
    ResultData executeService() throws ServiceFailException;

    /**
     * 封装成功结果
     */
    Response<T> initSuccessResult(T resultData);

    /**
     * 解锁
     */
    void unlock();

    /**
     * 封装失败结果 - 针对执行失败的情况
     */
    Response<T> initFailResult(ServiceFailException e);

    /**
     * 封装失败结果 - 针对执行时异常的情况
     */
    Response<T> initErrorResult(Exception e);

}
