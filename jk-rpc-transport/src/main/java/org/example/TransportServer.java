package org.example;

/**
 * @author gzw
 * @description： 1、 启动 监听   2、接收请求 3 、关闭
 * @since：2024/5/24 23:10
 */
public interface TransportServer {

    void  init(int port ,RequestHandler handler);

    void start();


    void stop();

}
