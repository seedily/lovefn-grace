package com.lovefn.grace.tiny.autoconfigure.web.exceptionhandler;

import com.lovefn.grace.common.api.entity.Response;
import com.lovefn.grace.common.api.entity.ResultCode;
import com.lovefn.grace.tiny.exception.ServiceErrorException;
import com.lovefn.grace.tiny.exception.ServiceFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


/**
 * [Configuration] controller异常拦截器
 */
@Configuration
@ConditionalOnProperty(name = "grace.application.web.exceptionhandler", matchIfMissing = true, havingValue = "ON")
@ConditionalOnClass({org.springframework.web.method.HandlerMethod.class})
public class GlobalExceptionHandlerConfiguration {

    private static String ERROR_MSG = "{}:{}";
    private static String ERROR_MSG_SUMMARY = "%s:%s";
    private static String ERROR_MSG_TYPE_SUMMARY = "参数类型错误";

    /**
     * controller异常拦截器
     */
    @RestControllerAdvice
    private class GlobalExceptionHandler {

        private Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(Exception.class)
        @ResponseBody
        public Response handlerException(Exception e) {
            if (e instanceof ServiceFailException) {
                return handlerServiceFailException((ServiceFailException) e);

            } else if (e instanceof ServiceErrorException) {
                return handlerServiceErrorException((ServiceErrorException) e);

            }  else if (e instanceof MethodArgumentNotValidException) {
                return handlerMethodArgumentNotValidException((MethodArgumentNotValidException) e);

            } else if (e instanceof ConstraintViolationException) {
                return handlerConstraintViolationException((ConstraintViolationException) e);

            } else if (e instanceof BindException) {
                return handlerBindException((BindException) e);

            } else if (e instanceof HttpMessageNotReadableException) {
                return handlerHttpMessageNotReadableException((HttpMessageNotReadableException) e);

            } else if (e instanceof MethodArgumentTypeMismatchException) {
                return handlerMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) e);

            } else if (e instanceof SQLException) {
                LOG.error("Please follow : SQLException should not appear in controller");
                return handlerSQLException((SQLException) e);

            } else {
                LOG.error("Please follow : unknown exception:{}", e.getMessage(), e);
                return Response.buildFail(ResultCode.ILLEGAL_PARAM, ERROR_MSG_TYPE_SUMMARY);
            }
        }


        private Response handlerServiceFailException(ServiceFailException exception) {
            String errorMsg = exception.getMessage();
            String summaryMsg = String.format(ERROR_MSG_SUMMARY, exception.getClass().getSimpleName(), errorMsg);
            LOG.warn(summaryMsg); // 不打印堆栈
            return Response.buildFail(exception.getCode(), exception.getMsg());
        }

        private Response handlerServiceErrorException(ServiceErrorException exception) {
            String errorMsg = exception.getMessage();
            String summaryMsg = String.format(ERROR_MSG_SUMMARY, exception.getClass().getSimpleName(), errorMsg);
            LOG.error(summaryMsg, exception);
            return Response.buildFail(exception.getCode(), exception.getMsg());
        }

        private Response handlerConstraintViolationException(ConstraintViolationException exception) {
            Response responseEntity = Response.buildFail(ResultCode.ILLEGAL_PARAM, null);
            Iterator<ConstraintViolation<?>> it = exception.getConstraintViolations().iterator();
            if (it.hasNext()) {
                ConstraintViolation<?> cvl = it.next();
                responseEntity.setResMsg(cvl.getMessageTemplate());
            }
            LOG.warn(ERROR_MSG, exception.getClass().getSimpleName(), exception.getMessage(), exception);
            return responseEntity;
        }




        private Response handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
            BindingResult result = exception.getBindingResult();
            final List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors) {
                String errorMsg = error.getField() + error.getDefaultMessage();
                LOG.warn(ERROR_MSG, exception.getClass().getSimpleName(), errorMsg);
                return Response.buildFail(ResultCode.ILLEGAL_PARAM, errorMsg);
            }
            return Response.buildFail(ResultCode.ILLEGAL_PARAM, null);
        }

        private Response handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
            LOG.error("Parse params failure, params [{}]", e.getHttpInputMessage().toString());
            return Response.buildFail(ResultCode.ILLEGAL_PARAM, ERROR_MSG_TYPE_SUMMARY);
        }

        private Response handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
            LOG.warn(ERROR_MSG, ex.getClass().getSimpleName(), ERROR_MSG_TYPE_SUMMARY);
            return Response.buildFail(ResultCode.ILLEGAL_PARAM, ERROR_MSG_TYPE_SUMMARY);
        }


        private Response handlerBindException(BindException exception) {
            String errorMsg = exception.getFieldError().getDefaultMessage();
            String summaryMsg = String.format(ERROR_MSG_SUMMARY, exception.getClass().getSimpleName(), errorMsg);
            LOG.error(summaryMsg, exception);
            return Response.buildFail(ResultCode.ILLEGAL_PARAM, summaryMsg);
        }

        private Response handlerSQLException(SQLException exception) {
            String errorMsg = exception.getMessage();
            String summaryMsg = String.format(ERROR_MSG_SUMMARY, exception.getClass().getSimpleName(), errorMsg);
            LOG.error(summaryMsg, exception);
            return Response.buildFail(ResultCode.SYS_ERROR, "数据操作异常");
        }
    }

}
