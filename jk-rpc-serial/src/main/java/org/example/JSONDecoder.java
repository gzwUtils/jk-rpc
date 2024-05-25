package org.example;

import com.alibaba.fastjson2.JSON;

/**
 * @author gzw
 * @description： 基于json的反序列化实现
 * @since：2024/5/24 22:50
 */

@SuppressWarnings("all")
public class JSONDecoder implements Decoder{

    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes,tClass);
    }
}
