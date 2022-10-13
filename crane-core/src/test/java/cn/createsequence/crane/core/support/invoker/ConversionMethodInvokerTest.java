package cn.createsequence.crane.core.support.invoker;

import cn.createsequence.crane.core.support.converter.SimpleTypeConvert;
import cn.createsequence.crane.core.support.converter.TypeConverter;
import lombok.*;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * test for {@link ConversionMethodInvoker}
 *
 * @author huangchengxing
 */
public class ConversionMethodInvokerTest {

    @Test
    public void invoke() {
        MethodInvoker invoker = getInvoker();
        TypeConverter typeConverter = new SimpleTypeConvert();

        Foo foo = new Foo();

        // convert params， no convert result
        MethodInvoker conversionInvoker = new ConversionMethodInvoker(
            invoker, typeConverter, null, new Class[]{ Integer.class }
        );
        conversionInvoker.invoke(foo, "42");
        Assert.assertEquals((Integer)42, foo.getId());

        // no params， no convert result
        conversionInvoker = new ConversionMethodInvoker(
            invoker, typeConverter, String.class, null
        );
        Assert.assertEquals(
            foo.toString(),
            conversionInvoker.invoke(foo, "24")
        );

    }

    @SneakyThrows
    private static MethodInvoker getInvoker() {
        Method method = Foo.class.getDeclaredMethod("setId", Integer.class);
        return new ReflectionMethodInvoker(method);
    }

    @Accessors(chain = true)
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Foo {
        private Integer id;
    }

}