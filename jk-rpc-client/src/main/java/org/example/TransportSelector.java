package org.example;

import java.util.List;

/**
 * @author gzw
 * @description： 表示选择哪个server去连接
 * @since：2024/5/25 00:53
 */
public interface TransportSelector {


    /**
     * 初始化selector
     * @param peers 可以连接的端点信息
     * @param count 建立连接的个数
     * @param client client的实现。
     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> client);


    /**
     * 选择一个transport 与server 做交互
     * @return 网络客户端
     */
    TransportClient selector();


    /**
     * 释放用完的客户端
     * @param client client
     */
    void release(TransportClient client);



    void close();

}
