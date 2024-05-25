package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

/**
 * @author gzw
 * @description：
 * @since：2024/5/24 23:16
 */
public class HttpTransportClient implements TransportClient{

    private String url;


    @Override
    public void connect(Peer peer) {
        this.url = "http://"+peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.connect();
            IOUtils.copy(data,connection.getOutputStream());
            int code = connection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                return connection.getInputStream();
            } else {
                return connection.getErrorStream();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}
