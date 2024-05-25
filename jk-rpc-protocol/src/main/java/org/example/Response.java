package org.example;

import lombok.Data;

/**
 * @author gzw
 * @description： 响应
 * @since：2024/5/24 22:05
 */

@Data
public class Response<T> {


    private String msg = "ok";


    private int code = -1;


    private T data;

}
