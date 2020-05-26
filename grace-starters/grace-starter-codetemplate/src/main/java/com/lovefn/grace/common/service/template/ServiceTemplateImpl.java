package com.lovefn.grace.common.service.template;

import com.lovefn.grace.common.service.callback.ServiceCallback;
import com.lovefn.grace.common.service.entity.BaseResult;
import com.lovefn.grace.common.service.entity.Response;
import com.lovefn.grace.common.service.exception.ServiceFailException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Moffee on 2018/7/11.
 */
@Slf4j
public class ServiceTemplateImpl implements ServiceTemplate {

    @Override
    public Response execute(final ServiceCallback action) {
        try {
            action.lock();  //加锁
            BaseResult result = action.executeService();
            return action.initSuccessResult(result);
        } catch (ServiceFailException e) {
            log.warn("业务失败：{}", e.toString(), e);
            return action.initFailResult(e);

        } catch (Exception e) {
            log.error("系统异常：{}", e.getMessage(), e);
            return action.initErrorResult(e);
        } finally {
            action.unlock(); //解锁
        }
    }

}
