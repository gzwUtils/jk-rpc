package org.example;

import lombok.Data;

/**
 * @author gzw
 * @description： 请求
 * @since：2024/5/24 22:03
 */

@Data
public class Request {


    private ServiceDescriptor serviceDescriptor;


    private Object [] parameters;

}
