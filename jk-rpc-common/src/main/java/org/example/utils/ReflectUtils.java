package org.example.utils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gzw
 * @description： 反射工具类
 * @since：2024/5/24 22:07
 */
public class ReflectUtils {

    /**
     * 根据class创建对象
     * @param tClass 待创建对象的类
     * @return 创建好的对象实例
     * @param <T> 对象类型
     */

    public static <T>  T newInstance(Class<T> tClass) {

        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取public 方法
     * @param clazz  clazz
     * @return 当前类的共有方法。
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] clazzDeclaredMethods = clazz.getDeclaredMethods();
        List<Method> arrayList = new ArrayList<>();
        for (Method m:clazzDeclaredMethods) {
            if(Modifier.isPublic(m.getModifiers())){
               arrayList.add(m);
            }
        }
        return arrayList.toArray(new Method[0]);
    }


    /**
     * 调用指定对象的某个方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 结果
     */
    public static Object invoke (Object obj, Method method,Object... args){
        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
