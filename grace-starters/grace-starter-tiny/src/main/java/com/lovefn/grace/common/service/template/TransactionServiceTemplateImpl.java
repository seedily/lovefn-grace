package com.lovefn.grace.common.service.template;

import com.lovefn.grace.common.service.callback.ServiceCallback;
import com.lovefn.grace.common.service.exception.ServiceFailException;
import com.lovefn.grace.common.service.entity.Response;
import com.lovefn.grace.common.service.entity.ResponseBuilder;
import com.lovefn.grace.common.service.entity.BaseResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Moffee on 2018/7/11.
 */
@Data
@Slf4j
public class TransactionServiceTemplateImpl implements TransactionServiceTemplate {

    private TransactionTemplate transactionTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public Response executeWithTransaction(final ServiceCallback action) {
        Response response = (Response) transactionTemplate.execute(new TransactionCallback() {
            /**
             * @see TransactionCallback#doInTransaction(TransactionStatus)
             */
            public Object doInTransaction(TransactionStatus status) {
                try {
                    action.executeCheck();
                    action.lock();  //加锁
                    BaseResult result = action.executeService();
                    return ResponseBuilder.createSuccessRes(result);
                } catch (ServiceFailException e) {
                    log.warn("业务失败：{}", e.toString(), e);
                    status.setRollbackOnly();  //事务回滚
                    return action.initFailResult(e);
                } catch (Exception e) {
                    log.error("系统异常：{}", e.getMessage(), e);
                    status.setRollbackOnly();  //事务回滚
                    return action.initErrorResult(e);
                } finally {
                    action.unlock(); //解锁
                }
            }
        });
        return response;
    }

}
