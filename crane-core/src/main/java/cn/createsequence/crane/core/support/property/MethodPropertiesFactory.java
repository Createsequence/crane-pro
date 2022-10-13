package cn.createsequence.crane.core.support.property;

import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.createsequence.crane.core.support.invoker.ConversionMethodInvoker;
import cn.createsequence.crane.core.support.invoker.MethodInvoker;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p>An implementation of {@link AbstractReflectionPropertiesFactory}
 * based on {@link java.lang.reflect.Method}
 *
 * @author huangchengxing
 */
public class MethodPropertiesFactory extends AbstractReflectionPropertiesFactory {

    /**
     * Constructor
     *
     * @param typeConverter typeConverter
     */
    public MethodPropertiesFactory(TypeConverter typeConverter) {
        super(typeConverter);
    }

    /**
     * Create a {@link BeanProperty} instance for target field
     *
     * @param beanType     bean's type
     * @param field        field
     * @param propertyName propertyName
     * @return {@link BeanProperty} instance
     */
    @Override
    protected BeanProperty createBeanProperty(Class<?> beanType, Field field, String propertyName) {
        MethodInvoker getter = createGetterInvoker(getGetterMethod(beanType, field));
        MethodInvoker setter = createSetterInvoker(getSetterMethod(beanType, field));
        return new InvokerBeanProperty(field.getType(), propertyName, getter, setter);
    }

    private MethodInvoker createGetterInvoker(Method getter) {
        return (target, args) -> ReflectUtil.invoke(target, getter, args);
    }

    private MethodInvoker createSetterInvoker(Method setter) {
        MethodInvoker invoker = (target, args) -> ReflectUtil.invoke(target, setter, args);
        return new ConversionMethodInvoker(invoker, typeConverter, null, setter.getParameterTypes());
    }

}
