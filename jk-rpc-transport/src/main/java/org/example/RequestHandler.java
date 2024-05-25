package org.example;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author gzw
 * @description： 处理网络请求的handler
 * @since：2024/5/24 23:12
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream out);


}
