package cn.createsequence.crane.core.support.invoker;

import com.esotericsoftware.reflectasm.MethodAccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test for {@link ReflectAsmMethodInvoker}
 *
 * @author huangchengxing
 */
public class ReflectAsmMethodInvokerTest {

    @Test
    public void invoke() {
        MethodAccess methodAccess = MethodAccess.get(Foo.class);
        int index = methodAccess.getIndex("getTarget");
        MethodInvoker invoker = new ReflectAsmMethodInvoker(index, methodAccess);
        Foo foo = new Foo("foo");
        Assert.assertEquals("foo", invoker.invoke(foo));
    }

    @Getter
    @RequiredArgsConstructor
    private static class Foo {
        private final Object target;
    }
}