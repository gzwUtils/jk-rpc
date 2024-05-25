package org.example;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.ReflectUtils;

/**
 * @author gzw
 * @description： 管理rpc暴露的服务
 * @since：2024/5/24 23:53
 */
@Slf4j
public class ServiceManager {

    private Map<ServiceDescriptor,ServiceInstance> service;


    public ServiceManager() {
        this.service = new ConcurrentHashMap<>();
    }

    public  <T> void  register(Class<T> tClass,T bean){
        Method[] methods = ReflectUtils.getPublicMethods(tClass);
        for(Method method : methods) {
            ServiceInstance instance = new ServiceInstance(bean, method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(tClass, method);
            service.put(serviceDescriptor,instance);
            log.warn("register service: {} {}",serviceDescriptor.getClazz(),serviceDescriptor.getMethod());
        }
    }


    public ServiceInstance lookup(Request request) {
        ServiceDescriptor descriptor = request.getServiceDescriptor();
        ServiceInstance instance = service.get(descriptor);
        return instance;
    }
}
