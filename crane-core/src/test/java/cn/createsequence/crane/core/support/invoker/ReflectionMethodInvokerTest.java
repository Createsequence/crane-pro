package cn.createsequence.crane.core.support.invoker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * test for {@link ReflectionMethodInvoker}
 *
 * @author huangchengxing
 */
public class ReflectionMethodInvokerTest {

    @SneakyThrows
    @Test
    public void invoke() {
        Method method = Foo.class.getDeclaredMethod("getTarget");
        MethodInvoker invoker = new ReflectionMethodInvoker(method);
        Foo foo = new Foo("foo");
        Assert.assertEquals("foo", invoker.invoke(foo));
    }

    @Getter
    @RequiredArgsConstructor
    private static class Foo {
        private final Object target;
    }

}