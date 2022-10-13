package cn.createsequence.crane.core.support.property;

import javax.annotation.Nullable;

/**
 * All operable properties in a bean，used to batch manage {@link BeanProperty}
 *
 * @author huangchengxing
 * @see BeanProperty
 * @see BeanPropertiesFactory
 */
public interface BeanProperties extends Iterable<BeanProperty> {

    /**
     * Get bean's type
     *
     * @return bean's type
     */
    Class<?> getBeanType();

    /**
     * Return true if instance contains the specified property
     *
     * @param propertyName property's name
     * @param propertyType property's type
     * @return true or false
     */
    boolean containsProperty(String propertyName, Class<?> propertyType);

    /**
     * Return true if instance contains the specified property,
     * use{@link #containsProperty(String, Class)} to check the type of property.
     *
     * @param propertyName property's name
     * @return true or false
     */
    boolean containsProperty(String propertyName);

    /**
     * Get {@link BeanProperty}
     *
     * @param propertyName property's name
     * @return {@link BeanProperty} instance if present, null otherwise
     */
    @Nullable
    BeanProperty getProperty(String propertyName);

    /**
     * Write a value to the specified attribute of the bean
     *
     * @param propertyName property's name
     * @param bean bean, must non-null
     * @param value value
     * @throws NullPointerException thrown when the bean is null
     */
    void writePropertyValue(String propertyName, Object bean, Object value);

    /**
     * Read the specified attribute value of the bean
     *
     * @param propertyName property's name
     * @param bean bean, must non-null
     * @return value, or null if property's is non-existent or property's value is null
     * @throws NullPointerException thrown when the bean is null
     */
    Object getPropertyValue(String propertyName, Object bean);
    
    /**
     * Get property's type if present
     *
     * @param propertyName property's name
     * @return property's type if property present, null otherwise
     */
    @Nullable
    Class<?> getPropertyType(String propertyName);

}
