package org.example;

import java.io.InputStream;

/**
 * @author gzw
 * @description： 1、创建连接  2、 发送数据 3、关闭
 * @since：2024/5/24 23:07
 */
public interface TransportClient {


    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
