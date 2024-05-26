package org.example;

/**
 * @author gzw
 * @description：
 * @since：${DATE} ${TIME}
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(Cal.class,new CalImpl());
        rpcServer.start();
    }
}