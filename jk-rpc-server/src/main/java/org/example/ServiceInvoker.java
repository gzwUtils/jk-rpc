package org.example;

import org.example.utils.ReflectUtils;

/**
 * @author gzw
 * @description：
 * @since：2024/5/25 00:29
 */
public class ServiceInvoker {


    public Object invoke (ServiceInstance instance,Request request){

        return ReflectUtils.invoke(instance.getTarget(),instance.getMethod(),request.getParameters());

    }
}
