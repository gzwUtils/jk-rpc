package org.example;

import com.alibaba.fastjson2.JSON;

/**
 * @author gzw
 * @description： 基于json的序列化实现
 * @since：2024/5/24 22:48
 */
@SuppressWarnings("all")
public class JSONEncoder implements Encoder {

    @Override
    public byte[] encode(Object obj) {

        return JSON.toJSONBytes(obj);
    }


}
