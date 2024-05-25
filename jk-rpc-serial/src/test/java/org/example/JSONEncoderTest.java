package org.example;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author gzw
 * @description：
 * @since：2024/5/24 22:54
 */
public class JSONEncoderTest {

    @Test
    public void encode() {

        JSONEncoder jsonEncoder = new JSONEncoder();

        TestBean testBean = new TestBean();

        testBean.setAge("11");
        testBean.setName("kd");

        byte[] bytes = jsonEncoder.encode(testBean);

        Assert.assertNotNull(bytes);
    }
}