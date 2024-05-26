package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.example.utils.ReflectUtils;

/**
 * @author gzw
 * @description：
 * @since：2024/5/25 00:32
 */
@Slf4j
public class RpcServer {

    private RpcServiceConfig rpcServiceConfig;

    private TransportServer net;

    private Encoder encoder;

    private Decoder decoder ;

    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;


    public RpcServer() {
        this(new RpcServiceConfig());
    }

    public RpcServer(RpcServiceConfig rpcServiceConfig) {
        this.rpcServiceConfig = rpcServiceConfig;

        this.net = ReflectUtils.newInstance(rpcServiceConfig.getTransportClass());

        this.net.init(rpcServiceConfig.getPort(),this.requestHandler);

        this.encoder = ReflectUtils.newInstance(rpcServiceConfig.getEncoderClass());

        this.decoder = ReflectUtils.newInstance(rpcServiceConfig.getDecoderClass());

        this.serviceManager = new ServiceManager();

        this.serviceInvoker = new ServiceInvoker();
    }


    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    public  <T> void  register(Class<T> tClass,T bean){
        serviceManager.register(tClass,bean);
    }

    private RequestHandler requestHandler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream out) {
            Response<Object> response = new Response<>();
            try {
                byte[] bytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(bytes, Request.class);
                log.warn("get request: {} ",request);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object rep = serviceInvoker.invoke(serviceInstance, request);
                response.setData(rep);
            } catch (IOException e) {
                log.error(e.getMessage(),e);
                response.setCode(1);
                response.setMsg("rpcServer error:"+e.getClass().getName() + ":"+e.getMessage());
                throw new RuntimeException(e);
            } finally {
                byte[] encode = encoder.encode(response);
                try {
                    out.write(encode);
                    log.warn("response client");
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
    };
}
