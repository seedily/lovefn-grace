package com.lovefn.grace.common.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 14967333671L;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 页数据
     */
    private List pageData;


}
