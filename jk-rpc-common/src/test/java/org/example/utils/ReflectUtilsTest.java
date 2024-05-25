package org.example.utils;

import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author gzw
 * @description：
 * @since：2024/5/24 22:31
 */
public class ReflectUtilsTest {

    @Test
    public void newInstance() {
        TestCase testCase = ReflectUtils.newInstance(TestCase.class);
        Assert.assertNotNull(testCase);
    }

    @Test
    public void getPublicMethods() {
        Method[] publicMethods = ReflectUtils.getPublicMethods(TestCase.class);
        Assert.assertEquals(1, publicMethods.length);
        String name = publicMethods[0].getName();
        Assert.assertEquals("b", name);
    }

    @Test
    public void invoke() {

        Method[] publicMethods = ReflectUtils.getPublicMethods(TestCase.class);
        Method method = publicMethods[0];
        TestCase aCase = new TestCase();
        Object invoke = ReflectUtils.invoke(aCase, method);
        Assert.assertEquals("b", invoke);
    }
}