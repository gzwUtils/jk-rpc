package org.example;

import java.lang.reflect.Proxy;
import org.example.utils.ReflectUtils;

/**
 * @author gzw
 * @description：
 * @since：2024/5/25 01:31
 */
public class RpcClient {

    private RpcClientConfig rpcClientConfig;


    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    public RpcClient(RpcClientConfig rpcClientConfig) {
        this.rpcClientConfig = rpcClientConfig;

        this.encoder = ReflectUtils.newInstance(this.rpcClientConfig.getEncoderClass());

        this.decoder = ReflectUtils.newInstance(this.rpcClientConfig.getDecoderClass());

        this.transportSelector =  ReflectUtils.newInstance(this.rpcClientConfig.getAClass());

        this.transportSelector.init(this.rpcClientConfig.getServers(),this.rpcClientConfig.getDefaultPeerConnectCount(),this.rpcClientConfig.getTransportClients());

    }


    public RpcClient() {
        this(new RpcClientConfig());
    }


    public <T> T getProxy(Class<T> tClass){
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{tClass},new RemoteInvoker(tClass,encoder,decoder,transportSelector));
    }
}
