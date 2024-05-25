package org.example;

/**
 * @author gzw
 * @description： 序列化
 * @since：2024/5/24 22:41
 */
public interface Encoder {

    /**
     * 将对象转二进制数组
     * @param obj 对象
     * @return 二进制数组
     */
    byte [] encode(Object obj);

}
