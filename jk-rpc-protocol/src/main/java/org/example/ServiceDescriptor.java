package org.example;

import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author gzw
 * @description： 一个服务
 * @since：2024/5/24 22:00
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceDescriptor {

    private String clazz;

    private String  method;


    private String returnType;

    private String [] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor descriptor = new ServiceDescriptor();
        descriptor.setClazz(clazz.getName()).setMethod(method.getName()).setReturnType(method.getReturnType().getName());
        Class<?>[] types = method.getParameterTypes();
        String[] strings = new String[types.length];
        for (int i = 0 ; i< types.length ;i++) {
            strings[i] = types[i].getName();
        }
        descriptor.setParameterTypes(strings);

        return descriptor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }


    @Override
    public String toString() {
        return "clazz="+clazz +",method="+ method + ",returnType="+ returnType +",parameterTypes="+ Arrays.toString(parameterTypes);
    }
}
