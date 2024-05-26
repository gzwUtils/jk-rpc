package org.example;

/**
 * @author gzw
 * @description：
 * @since：2024/5/27 00:18
 */
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        Cal proxy = client.getProxy(Cal.class);
        int add = proxy.add(1, 2);
        System.out.println(add);
    }

}
