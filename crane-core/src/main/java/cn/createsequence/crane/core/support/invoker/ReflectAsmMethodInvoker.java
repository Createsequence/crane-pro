package cn.createsequence.crane.core.support.invoker;

import com.esotericsoftware.reflectasm.MethodAccess;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * An implementation of {@link MethodInvoker}
 * based on {@link com.esotericsoftware.reflectasm.MethodAccess}
 *
 * @author huangchengxing
 */
public class ReflectAsmMethodInvoker implements MethodInvoker {

    private final int methodIndex;
    private final MethodAccess methodAccess;

    /**
     * constructor
     *
     * @param methodIndex method index
     * @param methodAccess methodAccess
     */
    public ReflectAsmMethodInvoker(int methodIndex, MethodAccess methodAccess) {
        this.methodIndex = methodIndex;
        this.methodAccess = Objects.requireNonNull(methodAccess);
    }

    /**
     * do invoke
     *
     * @param target target
     * @param args parameter or null
     * @return value or null
     */
    @Override
    public Object invoke(@Nullable Object target, @Nullable Object... args) {
        return methodAccess.invoke(target, methodIndex, args);
    }

}
