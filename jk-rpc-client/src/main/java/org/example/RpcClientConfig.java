package org.example;

import java.util.Arrays;
import java.util.List;
import lombok.Data;

/**
 * @author gzw
 * @description： 客户端配置
 * @since：2024/5/25 01:19
 */
@Data
public class RpcClientConfig {

    //网络协议客户端

    private Class<? extends TransportClient> transportClients = HttpTransportClient.class;

    //序列化

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;


    private Class<? extends TransportSelector> aClass = RandomTransPortSelector.class;


    private int defaultPeerConnectCount = 1;


    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));




}
