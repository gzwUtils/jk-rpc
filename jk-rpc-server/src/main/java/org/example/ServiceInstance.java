package org.example;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gzw
 * @description： 具体服务
 * @since：2024/5/24 23:51
 */
@AllArgsConstructor
@Data
public class ServiceInstance {

    private Object  target;

    private Method method;

}
