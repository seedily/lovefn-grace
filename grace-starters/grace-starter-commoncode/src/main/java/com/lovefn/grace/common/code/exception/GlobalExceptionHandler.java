package com.lovefn.grace.common.code.exception;


import com.lovefn.grace.common.api.entity.Response;
import com.lovefn.grace.common.api.entity.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

/**
 * 系统的controller异常拦截器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handlerException(Exception e) {
        Response res = null;
        if (e instanceof ServiceFailException) {
            log.warn("catch ServiceFailException {}.", e.getMessage());
            res = handlerServiceFailException((ServiceFailException) e);
        } else if (e instanceof ServiceErrorException) {
            log.warn("catch ServiceErrorException {}.", e.getMessage(), e);
            res = handlerServiceErrorException((ServiceErrorException) e);
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("catch MethodArgumentNotValidException {}.", e.getMessage(), e);
            res = handlerMethodArgumentNotValidException((MethodArgumentNotValidException) e);
        } else if (e instanceof ConstraintViolationException) {
            log.error("catch ConstraintViolationException {}.", e.getMessage(), e);
            res = handlerConstraintViolationException((ConstraintViolationException) e);
        } else if (e instanceof BindException) {
            log.error("catch BindException {}.", e.getMessage(), e);
            res = handlerBindException((BindException) e);
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("catch HttpMessageNotReadableException {}.", e.getMessage(), e);
            res = handlerHttpMessageNotReadableException((HttpMessageNotReadableException) e);
        } else {
            log.error("unknown Exception {}.", e.getMessage(), e);
            res = new Response();
            res.setResCode(ResultCode.SYS_ERROR.getCode());
            res.setResMsg(ResultCode.SYS_ERROR.getMsg());
        }
        return res;
    }

    private Response handlerServiceFailException(ServiceFailException e) {
        Response res = new Response();
        res.setResCode(e.getCode());
        res.setResMsg(e.getMsg());
        return res;
    }

    private Response handlerServiceErrorException(ServiceErrorException e) {
        Response res = new Response();
        res.setResCode(e.getResultCode().getCode());
        res.setResMsg(e.getErrorMsg());
        return res;
    }

    private Response handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Response res = new Response();
        res.setResCode(ResultCode.ILLEGAL_PARAM.getCode());
        res.setResMsg(e.getBindingResult().getFieldError().getDefaultMessage());
        return res;
    }

    private Response handlerConstraintViolationException(ConstraintViolationException e) {
        Response res = new Response();
        res.setResCode(ResultCode.ILLEGAL_PARAM.getCode());
        Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator();
        if (it.hasNext()) {
            ConstraintViolation<?> cvl = it.next();
            res.setResMsg(cvl.getMessageTemplate());
        }
        return res;
    }

    private Response handlerBindException(BindException e) {
        Response res = new Response();
        res.setResCode(ResultCode.ILLEGAL_PARAM.getCode());
        res.setResMsg(e.getFieldError().getDefaultMessage());
        return res;
    }

    private Response handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Response res = new Response();
        log.error("Parse params failure, params [{}]", e.getHttpInputMessage().toString());
        res.setResCode(ResultCode.ILLEGAL_PARAM.getCode());
        res.setResMsg(ResultCode.ILLEGAL_PARAM.getMsg());
        return res;
    }

}
