package com.lovefn.grace.common.service.template;

import com.lovefn.grace.common.service.callback.ServiceCallback;
import com.lovefn.grace.common.service.entity.Response;

/**
 * 代理模式：代理对象（注入ServiceCallback并调用其check&execute）
 * Created by Moffee on 2018/7/11.
 */
public interface ServiceTemplate {

    /**
     * 代理执行
     *
     * @param action
     * @return Response
     */
    @SuppressWarnings("unchecked")
    Response execute(ServiceCallback action);

}
