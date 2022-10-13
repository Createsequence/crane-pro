package cn.createsequence.crane.core.support.property;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * test for {@link SimpleBeanProperties}
 *
 * @author huangchengxing
 */
public class SimpleBeanPropertiesTest {

    @Test
    public void getBeanType() {
        BeanProperties properties = createBeanProperties();
        Assert.assertEquals(Foo.class, properties.getBeanType());
    }

    @Test
    public void containsProperty() {
        BeanProperties properties = createBeanProperties();
        Assert.assertTrue(properties.containsProperty("name"));
        Assert.assertFalse(properties.containsProperty("none"));
        Assert.assertFalse(properties.containsProperty("name", Integer.class));
        Assert.assertTrue(properties.containsProperty("name", String.class));
    }

    @Test
    public void getProperty() {
        BeanProperties properties = createBeanProperties();
        Assert.assertNotNull(properties.getProperty("name"));
        Assert.assertNull(properties.getProperty("id"));
    }

    @Test
    public void writePropertyValue() {
        BeanProperties properties = createBeanProperties();
        Foo foo = new Foo();
        properties.writePropertyValue("name", foo, "name");
        Assert.assertEquals("name", foo.name);
    }

    @Test
    public void getPropertyValue() {
        BeanProperties properties = createBeanProperties();
        Foo foo = new Foo();
        foo.setName("foo");
        Assert.assertNull(properties.getPropertyValue("id", foo));
        Assert.assertEquals(foo.name, properties.getPropertyValue("name", foo));
    }

    @Test
    public void getPropertyType() {
        BeanProperties properties = createBeanProperties();
        Assert.assertNull(properties.getPropertyType("id"));
        Assert.assertEquals(String.class, properties.getPropertyType("name"));
    }

    @Test
    public void iterator() {
        Iterator<BeanProperty> iterable = createBeanProperties().iterator();
        Assert.assertTrue(iterable.hasNext());
        Assert.assertNotNull(iterable.next());
        Assert.assertFalse(iterable.hasNext());
    }

    private static BeanProperties createBeanProperties() {
        BeanProperty property = new InvokerBeanProperty(
            String.class, "name",
            (target, args) -> Objects.isNull(target) ? null : ((Foo)target).getName(),
            (target, args) -> {
                if(Objects.nonNull(target)) {
                    ((Foo)target).setName(ArrayUtil.get(args, 0));
                }
                return null;
            }
        );
        Map<String, BeanProperty> propertyMap = new HashMap<>(1);
        propertyMap.put(property.getName(), property);
        return new SimpleBeanProperties(Foo.class, propertyMap);
    }

    @Setter
    @Getter
    private static class Foo {
        private String name;
    }

}