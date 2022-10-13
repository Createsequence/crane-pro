package cn.createsequence.crane.core.support.property;

import cn.hutool.core.util.ClassUtil;

import javax.annotation.Nullable;
import java.util.*;

/**
 * A simple implementation of {@link BeanProperties}
 *
 * @author huangchengxing
 */
public class SimpleBeanProperties implements BeanProperties {

    /**
     * bean's type
     */
    private final Class<?> beanType;

    /**
     * bean's properties
     */
    private final Map<String, BeanProperty> properties;

    /**
     * Constructor
     *
     * @param beanType bean's type
     * @param properties bean's properties, grouped by property name
     */
    public SimpleBeanProperties(Class<?> beanType, Map<String, BeanProperty> properties) {
        this.beanType = Objects.requireNonNull(beanType);
        this.properties = Collections.unmodifiableMap(properties);
    }

    /**
     * Get bean's type
     *
     * @return bean's type
     */
    @Override
    public Class<?> getBeanType() {
        return beanType;
    }

    /**
     * Return true if instance contains the specified property
     *
     * @param propertyName property's name
     * @param propertyType property's type
     * @return true or false
     */
    @Override
    public boolean containsProperty(String propertyName, Class<?> propertyType) {
        return Optional.ofNullable(propertyName)
            .map(properties::get)
            .filter(p -> ClassUtil.isAssignable(propertyType, p.getType()))
            .isPresent();
    }

    /**
     * Return true if instance contains the specified property,
     * use{@link #containsProperty(String, Class)} to check the type of property.
     *
     * @param propertyName property's name
     * @return true or false
     */
    @Override
    public boolean containsProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    /**
     * Get {@link BeanProperty}
     *
     * @param propertyName property's name
     * @return {@link BeanProperty} instance if present, null otherwise
     */
    @Nullable
    @Override
    public BeanProperty getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    /**
     * Write a value to the specified attribute of the bean
     *
     * @param propertyName property's name
     * @param bean         bean, must non-null
     * @param value        value
     * @throws NullPointerException thrown when the bean is null
     */
    @Override
    public void writePropertyValue(String propertyName, Object bean, Object value) {
        Objects.requireNonNull(bean);
        Optional.ofNullable(propertyName)
            .map(properties::get)
            .ifPresent(p -> p.writeValue(bean, value));
    }

    /**
     * Read the specified attribute value of the bean
     *
     * @param propertyName property's name
     * @param bean         bean, must non-null
     * @return value, or null if property's is non-existent or property's value is null
     * @throws NullPointerException thrown when the bean is null
     */
    @Override
    public Object getPropertyValue(String propertyName, Object bean) {
        Objects.requireNonNull(bean);
        return Optional.ofNullable(propertyName)
            .map(properties::get)
            .map(p -> p.getValue(bean))
            .orElse(null);
    }

    /**
     * Get property's type if present
     *
     * @param propertyName property's name
     * @return property's type if property present, null otherwise
     */
    @Nullable
    @Override
    public Class<?> getPropertyType(String propertyName) {
        return Optional.ofNullable(propertyName)
            .map(properties::get)
            .map(BeanProperty::getType)
            .orElse(null);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<BeanProperty> iterator() {
        return properties.values().iterator();
    }

}
