package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.ReflectUtils;

/**
 * @author gzw
 * @description： random
 * @since：2024/5/25 01:07
 */
@Slf4j
public class RandomTransPortSelector implements TransportSelector{


    public RandomTransPortSelector() {
        this.transportClients = new ArrayList<>();
    }

    /**
     * 已经连接好的client
     */
    private List<TransportClient> transportClients;

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> client) {
        count = Math.max(count,1);

        for(Peer peer : peers){
            for (int i=0;i<count;i++){
                TransportClient transportClient = ReflectUtils.newInstance(client);
                transportClient.connect(peer);
                transportClients.add(transportClient);
            }
            log.warn("connection server : {}",peer);
        }

    }

    @Override
    public synchronized TransportClient selector() {
        int nextInt = new Random().nextInt(transportClients.size());
        return transportClients.remove(nextInt);
    }

    @Override
    public synchronized void release(TransportClient client) {
        transportClients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : transportClients){
            client.close();
        }
        transportClients.clear();
    }
}
