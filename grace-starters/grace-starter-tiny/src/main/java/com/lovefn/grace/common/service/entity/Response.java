package com.lovefn.grace.common.service.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 结果对象
 *
 * @param <T>
 */
@Data
public class Response<T extends BaseResult> implements Serializable {

    private static final long serialVersionUID = 123456789L;

    private boolean success;            // 是否成功：true-是，false-否
    private String resCode;             // 返回的结果码
    private String resMsg;              // 返回的结果信息（面向用户）
    private T data;                     // 返回的结数据果
    private Map<Object, Object> map;    // 返回的拓展信息
    private String errorMsg;            // 错误详情（执行失败时可添加，面向开发者）

    /**
     * Constructor for all param
     */
    protected Response(boolean success, ResultCode resultCode, T data, Map<Object, Object> map, String errorMsg) {
        this.success = success;
        this.resCode = resultCode.getCode();
        this.resMsg = resultCode.getMessage();
        this.data = data;
        this.map = map;
        this.errorMsg = errorMsg;
    }

    /**
     * Constructor.
     */
    protected Response(boolean success, ResultCode resultCode, T data) {
        this.success = success;
        this.resCode = resultCode.getCode();
        this.resMsg = resultCode.getMessage();
        this.data = data;
    }

    /**
     * Constructor.
     */
    protected Response(boolean success, ResultCode resultCode, T data, Map<Object, Object> map) {
        this.success = success;
        this.resCode = resultCode.getCode();
        this.resMsg = resultCode.getMessage();
        this.data = data;
        this.map = map;
    }

    /**
     * Constructor.
     */
    protected Response(boolean success, ResultCode resultCode, String errorMsg) {
        this.success = success;
        this.resCode = resultCode.getCode();
        this.resMsg = resultCode.getMessage();
        this.errorMsg = errorMsg;
    }

}
