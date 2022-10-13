package cn.createsequence.crane.core.support.converter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * test for {@link ClassCastConverter}
 *
 * @author huangchengxing
 */
public class ClassCastConverterTest {

    @Test
    public void convert() {
        TypeConverter converter = new ClassCastConverter();
        Assert.assertEquals((Integer)1, converter.convert("2", Integer.class, 1));
        Assert.assertNull(converter.convert("2", Integer.class));
        Assert.assertEquals("2", converter.convert(1, String.class, "2"));
        Assert.assertNull(converter.convert(1, String.class));
        Assert.assertNull(converter.convert(null, Integer.class));
        Assert.assertEquals(Collections.emptyList(), converter.convert(Collections.emptyList(), Collection.class));
    }

}