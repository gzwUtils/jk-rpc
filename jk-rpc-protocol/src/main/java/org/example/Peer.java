package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gzw
 * @description： 网络传输的端点
 * @since：2024/5/24 21:56
 */

@AllArgsConstructor
@Data
public class Peer {

    private String host;

    private int port;

}
