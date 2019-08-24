package com.lovefn.grace.common.service.template;

import com.lovefn.grace.common.service.callback.AbstractServiceCallback;
import com.lovefn.grace.common.service.exception.ServiceFailException;
import com.lovefn.grace.common.service.response.Response;
import com.lovefn.grace.common.service.response.ResponseBuilder;
import com.lovefn.grace.common.service.response.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Moffee on 2018/7/11.
 */
@Slf4j
public class ServiceTemplateImpl implements ServiceTemplate {

    @Override
    public Response execute(final AbstractServiceCallback action) {
        try {
            action.executeCheck();
            ResultData resultData = action.executeService();
            return ResponseBuilder.createSuccessRes(resultData);
        } catch (ServiceFailException e) {
            log.warn("业务失败：{}", e.toString(), e);
            return action.initFailResult(e);

        } catch (Exception e) {
            log.error("系统异常：{}", e.getMessage(), e);
            return action.initErrorResult(e);
        }
    }

}
