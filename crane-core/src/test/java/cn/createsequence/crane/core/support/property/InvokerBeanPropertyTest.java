package cn.createsequence.crane.core.support.property;

import cn.hutool.core.util.ArrayUtil;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * test for {@link InvokerBeanProperty}
 *
 * @author huangchengxing
 */
public class InvokerBeanPropertyTest {

    @Test
    public void writeValue() {
        Foo foo = new Foo();
        BeanProperty property = createNameProperty();
        property.writeValue(foo, "foo");
        Assert.assertEquals("foo", foo.getName());
    }

    @Test
    public void getValue() {
        Foo foo = new Foo();
        foo.setName("foo");

        BeanProperty property = createNameProperty();
        Assert.assertEquals("foo", property.getValue(foo));
    }

    @Test
    public void getType() {
        BeanProperty property = createNameProperty();
        Assert.assertEquals(String.class, property.getType());
    }

    @Test
    public void getName() {
        BeanProperty property = createNameProperty();
        Assert.assertEquals("name", property.getName());
    }

    private static InvokerBeanProperty createNameProperty() {
        return new InvokerBeanProperty(
            String.class, "name",
            (target, args) -> Objects.isNull(target) ? null : ((Foo)target).getName(),
            (target, args) -> {
                if(Objects.nonNull(target)) {
                    ((Foo)target).setName(ArrayUtil.get(args, 0));
                }
                return null;
            }
        );
    }

    @Setter
    @Getter
    private static class Foo {
        private String name;
    }

}