package org.example;

/**
 * @author gzw
 * @description：
 * @since：2024/5/27 00:19
 */
public class CalImpl implements Cal{

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}
