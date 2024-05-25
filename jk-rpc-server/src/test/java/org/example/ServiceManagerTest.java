package org.example;

import java.lang.reflect.Method;
import org.example.utils.ReflectUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author gzw
 * @description：
 * @since：2024/5/25 00:16
 */
public class ServiceManagerTest {

    ServiceManager serviceManager;


    @Before
    public void init(){
        serviceManager = new ServiceManager();
    }

    @Test
    public void register() {
        TestInstance aClass = new TestClass();
        serviceManager.register(TestInstance.class,aClass);
    }

    @Test
    public void lookup() {
        Method[] publicMethods = ReflectUtils.getPublicMethods(TestInstance.class);
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInstance.class, publicMethods[0]);
        Request request = new Request();
        request.setServiceDescriptor(serviceDescriptor);
        ServiceInstance serviceInstance = serviceManager.lookup(request);

        Assert.assertNotNull(serviceInstance);
    }
}