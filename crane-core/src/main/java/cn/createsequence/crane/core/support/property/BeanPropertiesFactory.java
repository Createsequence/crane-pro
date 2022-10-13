package cn.createsequence.crane.core.support.property;

import javax.annotation.Nullable;

/**
 * A factory of {@link BeanProperties}
 *
 * @author huangchengxing
 * @see FieldPropertiesFactory
 * @see ReflectAsmPropertiesFactory
 */
@FunctionalInterface
public interface BeanPropertiesFactory {

    /**
     * <p>Create a {@link BeanProperties} instance,
     * it may be a single instance obtained from the cache
     * if the factory supports caching instances.
     *
     * @param beanType bean's type
     * @return {@link BeanProperties} instance
     */
    BeanProperties getBeanProperties(Class<?> beanType);

    /**
     * Get {@link BeanProperty} if it's present in specified {@link BeanProperties}
     *
     * @param beanType bean's type
     * @param propertyName property's type
     * @return {@link BeanProperty} if it's present in {@link BeanProperties} of this type, null otherwise
     */
    @Nullable
    default BeanProperty getProperty(Class<?> beanType, String propertyName) {
        return getBeanProperties(beanType).getProperty(propertyName);
    }

}
