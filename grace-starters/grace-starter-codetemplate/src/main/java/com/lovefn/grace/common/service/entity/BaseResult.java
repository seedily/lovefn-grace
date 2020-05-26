package com.lovefn.grace.common.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 结果体父类
 * 子类务必声明serialVersionUID
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BaseResult implements Serializable {

    private static final long serialVersionUID = 239877812349145L;

}
