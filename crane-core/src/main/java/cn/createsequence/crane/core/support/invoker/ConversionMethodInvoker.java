package cn.createsequence.crane.core.support.invoker;

import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.hutool.core.util.ArrayUtil;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * A wrapper of {@link MethodInvoker} what
 * support automatic conversion of input parameter and return value types.
 *
 * @author huangchengxing
 */
public class ConversionMethodInvoker implements MethodInvoker {

    private static final Class<?>[] EMPTY_PARAMETER_TYPES = new Class[0];

    private final MethodInvoker methodInvoker;
    private final TypeConverter typeConverter;
    private final Class<?> returnType;
    private final Class<?>[] parameterTypes;

    public ConversionMethodInvoker(
        MethodInvoker methodInvoker, TypeConverter typeConverter,
        @Nullable Class<?> returnType, @Nullable Class<?>[] parameterTypes) {
        this.methodInvoker = Objects.requireNonNull(methodInvoker);
        this.typeConverter = Objects.requireNonNull(typeConverter);
        this.returnType = returnType;
        this.parameterTypes = ArrayUtil.defaultIfEmpty(parameterTypes, EMPTY_PARAMETER_TYPES);
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
        Object result = methodInvoker.invoke(target, convertParameters(args));
        return Objects.nonNull(result) && Objects.nonNull(returnType) ?
            convertType(result, returnType) : result;
    }

    private Object[] convertParameters(Object... parameters) {
        if (ArrayUtil.isEmpty(parameters) || EMPTY_PARAMETER_TYPES == parameterTypes) {
            return parameters;
        }
        int parametersLength = parameters.length;
        Object[] converted = new Object[parametersLength];
        for (int i = 0; i < parameters.length; i++) {
            Object val = parameters[i];
            if (i < parameterTypes.length) {
                val = convertType(val, parameterTypes[i]);
            }
            converted[i] = val;
        }
        return converted;
    }

    private Object convertType(Object value, Class<?> type) {
        return Objects.isNull(type) ? value : typeConverter.convert(value, type);
    }

}
