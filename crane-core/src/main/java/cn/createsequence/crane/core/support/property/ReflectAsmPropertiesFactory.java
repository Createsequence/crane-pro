package cn.createsequence.crane.core.support.property;

import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.createsequence.crane.core.support.invoker.ConversionMethodInvoker;
import cn.createsequence.crane.core.support.invoker.MethodInvoker;
import cn.createsequence.crane.core.support.invoker.ReflectAsmMethodInvoker;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>An implementation of {@link AbstractReflectionPropertiesFactory}
 * based on {@link com.esotericsoftware.reflectasm.MethodAccess}. <br />
 * Compared with reflection based implementation classes,
 * it will have better performance,
 * but the extra files generated may consume more memory.
 *
 * @author huangchengxing
 */
public class ReflectAsmPropertiesFactory extends AbstractReflectionPropertiesFactory {

    /**
     * {@link MethodAccess} caches
     */
    private static final Map<Class<?>, MethodAccess> METHOD_ACCESSES_CACHE= new ConcurrentHashMap<>(16);

     /**
     * Constructor
     *
     * @param typeConverter typeConverter
     */
    public ReflectAsmPropertiesFactory(TypeConverter typeConverter) {
        super(typeConverter);
    }

    /**
     * Create a {@link BeanProperty} instance for target field
     *
     * @param beanType bean's type
     * @param field field
     * @param propertyName propertyName
     * @return {@link BeanProperty} instance
     */
    @Override
    protected InvokerBeanProperty createBeanProperty(Class<?> beanType, Field field, String propertyName) {
        MethodAccess methodAccess = METHOD_ACCESSES_CACHE.computeIfAbsent(beanType, MethodAccess::get);
        MethodInvoker getter = createGetterInvoker(getGetterMethod(beanType, field), methodAccess);
        MethodInvoker setter = createSetterInvoker(getSetterMethod(beanType, field), methodAccess);
        return new InvokerBeanProperty(field.getType(), propertyName, getter, setter);
    }

    private MethodInvoker createGetterInvoker(Method getter, MethodAccess methodAccess) {
        int index = methodAccess.getIndex(getter.getName());
        MethodInvoker invoker = new ReflectAsmMethodInvoker(index, methodAccess);
        return new ConversionMethodInvoker(invoker, typeConverter, null, null);
    }

    private MethodInvoker createSetterInvoker(Method setter, MethodAccess methodAccess) {
        int index = methodAccess.getIndex(setter.getName());
        MethodInvoker invoker = new ReflectAsmMethodInvoker(index, methodAccess);
        return new ConversionMethodInvoker(invoker, typeConverter, null, setter.getParameterTypes());
    }

}
