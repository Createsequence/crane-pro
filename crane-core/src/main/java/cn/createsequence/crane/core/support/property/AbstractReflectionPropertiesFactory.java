package cn.createsequence.crane.core.support.property;

import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.createsequence.crane.core.util.ExceptionUtils;
import cn.createsequence.crane.core.util.ReflectUtils;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.MapMaker;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>An basic implementation of {@link BeanPropertiesFactory}
 * based on {@link java.lang.reflect.Method}
 *
 * @author huangchengxing
 * @see MethodPropertiesFactory
 * @see ReflectAsmPropertiesFactory
 * @see FieldPropertiesFactory
 */
@RequiredArgsConstructor
public abstract class AbstractReflectionPropertiesFactory implements BeanPropertiesFactory {

    /**
     * type converter
     */
    protected final TypeConverter typeConverter;

    /**
     * caches of {@link BeanProperties}
     */
    private final ConcurrentMap<Class<?>, BeanProperties> beanPropertiesCache = new MapMaker().weakKeys().makeMap();

    /**
     * <p>Create a {@link BeanProperties} instance,
     * if it exists in the cache,
     * obtain the singleton object directly from the cache.
     *
     * @param beanType bean's type
     * @return {@link BeanProperties} instance
     */
    @Override
    public BeanProperties getBeanProperties(Class<?> beanType) {
        return beanPropertiesCache.computeIfAbsent(beanType, this::createBeanProperties);
    }

    /**
     * Create a {@link SimpleBeanProperties} instance
     */
    private SimpleBeanProperties createBeanProperties(Class<?> beanType) {
        Map<String, BeanProperty> propertyMap = new HashMap<>(8);
        for (Field field : ReflectUtil.getFields(beanType)) {
            propertyMap.computeIfAbsent(field.getName(), propertyName ->
                createBeanProperty(beanType, field, propertyName)
            );
        }
        return new SimpleBeanProperties(beanType, propertyMap);
    }

    /**
     * Create a {@link BeanProperty} instance for target field
     *
     * @param beanType bean's type
     * @param field field
     * @param propertyName propertyName
     * @return {@link BeanProperty} instance
     */
    protected abstract BeanProperty createBeanProperty(Class<?> beanType, Field field, String propertyName);

    /**
     * Get setter Method
     *
     * @param beanType bean's type
     * @param field field
     * @return java.lang.reflect.Method
     */
    protected Method getSetterMethod(Class<?> beanType, Field field) {
        return ReflectUtils.findSetterMethod(beanType, field)
            .orElseThrow(
                () -> ExceptionUtils.create(IllegalArgumentException::new, "no such setter method for [{}]", field)
            );
    }

    /**
     * Get getter Method
     *
     * @param beanType bean's type
     * @param field field
     * @return java.lang.reflect.Method
     */
    protected Method getGetterMethod(Class<?> beanType, Field field) {
        return ReflectUtils.findGetterMethod(beanType, field)
            .orElseThrow(
                () -> ExceptionUtils.create(IllegalArgumentException::new, "no such getter method for [{}]", field)
            );
    }

}
