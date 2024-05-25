package org.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * @author gzw
 * @description： 调用远程服务的代理类
 * @since：2024/5/25 01:40
 */

@Slf4j
public class RemoteInvoker implements InvocationHandler {


    private Encoder encoder;


    private Class clazz;

    private Decoder decoder;

    private TransportSelector transportSelector;


    public RemoteInvoker(Class clazz,Encoder encoder,Decoder decoder ,TransportSelector selector) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = selector;
        this.clazz =clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if(response == null || response.getCode() != -1){
            throw new IllegalStateException("fail to invoke remote "+response);
        }
        return response.getData();
    }


    private Response invokeRemote(Request request){
        TransportClient client = null ;
        Response response = null;
        try {
            client = transportSelector.selector();
            byte[] bytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(bytes));
            byte[] InBytes = IOUtils.readFully(receive, receive.available());
            response = decoder.decode(InBytes, Response.class);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            response.setCode(1);
            response.setMsg("rpcClient got error +"+e.getClass()+":"+e.getMessage());
        } finally {
            if(client!= null){
                transportSelector.release(client);
            }
        }
        return response;
    }
}
