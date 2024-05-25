package org.example;

/**
 * @author gzw
 * @description： 反序列化
 * @since：2024/5/24 22:44
 */
public interface Decoder {


    /**
     *  将二进制数组转为对象
     * @param bytes 二进制数组
     * @param tClass 返回类型
     * @return 返回对象
     * @param <T> 泛型
     */
     <T> T decode(byte [] bytes,Class<T> tClass);
}
