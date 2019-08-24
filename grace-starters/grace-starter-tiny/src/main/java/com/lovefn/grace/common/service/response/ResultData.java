package com.lovefn.grace.common.service.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果对象父类
 * 子类务必声明serialVersionUID
 */
@Data
public abstract class ResultData implements Serializable {

    private static final long serialVersionUID = 123456789L;

}
