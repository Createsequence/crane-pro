package cn.createsequence.crane.core.support.converter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * test for {@link SimpleTypeConvert}
 *
 * @author huangchengxing
 */
public class SimpleTypeConvertTest {

    @Test
    public void convert() {
        TypeConverter converter = new SimpleTypeConvert();
        Assert.assertEquals((Integer)2, converter.convert("2", Integer.class, 1));
        Assert.assertEquals((Integer)2, converter.convert("2", Integer.class));
        Assert.assertEquals("1", converter.convert(1, String.class, "2"));
        Assert.assertEquals("1", converter.convert(1, String.class));
        Assert.assertNull(converter.convert(null, Integer.class));
        Assert.assertEquals(Collections.emptyList(), converter.convert(Collections.emptyList(), Collection.class));
    }

}