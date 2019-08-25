package com.lovefn.grace.common.service.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求体父类
 * 子类务必声明serialVersionUID
 */
@Data
public abstract class BaseRequest implements Serializable {

    private static final long serialVersionUID = 60947812345123459L;

}
