package cn.createsequence.crane.core.support.invoker;

import cn.hutool.core.util.ReflectUtil;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * An implementation of {@link MethodInvoker}
 * based on {@link java.lang.reflect.Method}
 *
 * @author huangchengxing
 */
public class ReflectionMethodInvoker implements MethodInvoker {

    /**
     * method
     */
    private final Method method;

    public ReflectionMethodInvoker(Method method) {
        this.method = Objects.requireNonNull(method);
    }

    /**
     * do invoke
     *
     * @param target target
     * @param args   parameter or null
     * @return value or null
     */
    @Override
    public Object invoke(@Nullable Object target, @Nullable Object... args) {
        return ReflectUtil.invoke(target, method, args);
    }

}
