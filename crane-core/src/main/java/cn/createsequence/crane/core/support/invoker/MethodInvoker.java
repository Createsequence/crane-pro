package cn.createsequence.crane.core.support.invoker;

import javax.annotation.Nullable;

/**
 * An invokable object，such link {@link java.lang.reflect.Method}
 *
 * @author huangchengxing
 * @see ConversionMethodInvoker
 * @see ReflectionMethodInvoker
 * @see ReflectAsmMethodInvoker
 */
public interface MethodInvoker {

    /**
     * do invoker
     *
     * @param target target
     * @param args parameter or null
     * @return value or null
     */
    Object invoke(@Nullable Object target, @Nullable Object... args);

}
