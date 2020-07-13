package com.lovefn.grace.common.code.template;

import com.lovefn.grace.common.api.entity.Response;
import com.lovefn.grace.common.code.callback.ServiceCallback;
import com.lovefn.grace.common.code.exception.ServiceFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Moffee on 2018/7/11.
 */
@Slf4j
public class TransactionServiceTemplate {

    private static TransactionTemplate transactionTemplate;

    @SuppressWarnings("unchecked")
    public static Response executeWithTransaction(final ServiceCallback action) {
        Response response = (Response) transactionTemplate.execute(new TransactionCallback() {
            /**
             * @see TransactionCallback#doInTransaction(TransactionStatus)
             */
            public Object doInTransaction(TransactionStatus status) {
                try {
                    action.lock();  //加锁
                    return action.executeService();
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

    public static void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        TransactionServiceTemplate.transactionTemplate = transactionTemplate;
    }
}
