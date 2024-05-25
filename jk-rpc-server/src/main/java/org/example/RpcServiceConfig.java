package org.example;

import lombok.Data;

/**
 * @author gzw
 * @description： 配置
 * @since：2024/5/24 23:41
 */

@Data
public class RpcServiceConfig {

    //网络协议服务端

    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    //序列化

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    // 端口

   private  int port = 3000;

}
