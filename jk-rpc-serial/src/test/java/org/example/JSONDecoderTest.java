package org.example;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author gzw
 * @description：
 * @since：2024/5/24 22:59
 */
public class JSONDecoderTest {

    @Test
    public void decode() {

        JSONEncoder jsonEncoder = new JSONEncoder();

        TestBean testBean = new TestBean();

        testBean.setAge("11");
        testBean.setName("kd");

        byte[] bytes = jsonEncoder.encode(testBean);

        Assert.assertNotNull(bytes);

        JSONDecoder jsonDecoder = new JSONDecoder();

        TestBean decode = jsonDecoder.decode(bytes, TestBean.class);

        Assert.assertEquals(testBean.getName(),decode.getName());
    }
}