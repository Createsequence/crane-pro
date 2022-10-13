package cn.createsequence.crane.core.support.property;

import cn.createsequence.crane.core.support.converter.ClassCastConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test for {@link FieldPropertiesFactory}
 *
 * @author huangchengxing
 */
public class FieldPropertiesFactoryTest {

    @Test
    public void createBeanProperty() {
        BeanPropertiesFactory factory = new FieldPropertiesFactory(new ClassCastConverter());
        BeanProperties properties = factory.getBeanProperties(Foo.class);

        Assert.assertNotNull(properties);
        Assert.assertSame(properties, factory.getBeanProperties(Foo.class));

        Assert.assertNotNull(properties.getProperty("id"));
        Assert.assertNull(properties.getProperty("none"));
        Assert.assertNotNull(factory.getProperty(Foo.class, "id"));
        Assert.assertNull(factory.getProperty(Foo.class, "none"));

        Assert.assertTrue(properties.containsProperty("id"));
        Assert.assertFalse(properties.containsProperty("none"));
        Assert.assertTrue(properties.containsProperty("id", Integer.class));
        Assert.assertFalse(properties.containsProperty("id", String.class));

        Assert.assertEquals(Integer.class, properties.getPropertyType("id"));
        Assert.assertEquals(String.class, properties.getPropertyType("name"));

        Foo foo = new Foo(1, "foo");
        Assert.assertEquals(foo.id, properties.getPropertyValue("id", foo));
        Assert.assertEquals(foo.name, properties.getPropertyValue("name", foo));

        properties.writePropertyValue("id", foo, 42);
        Assert.assertEquals((Integer)42, foo.id);
        properties.writePropertyValue("name", foo, "name");
        Assert.assertEquals("name", foo.name);

    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class Foo {
        private Integer id;
        private String name;
    }

}