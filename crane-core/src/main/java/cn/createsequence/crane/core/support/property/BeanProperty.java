package cn.createsequence.crane.core.support.property;

/**
 * <p>An operable attribute of a bean,
 * used to read or write the specified property value from the bean instance.
 * generally, it corresponds to {@link java.lang.reflect.Field} in {@link Class}.
 *
 * @author huangchengxing
 * @see BeanProperties
 */
public interface BeanProperty {

    /**
     * Get property's type
     *
     * @return property's type
     */
    Class<?> getType();

    /**
     * Get property's name
     *
     * @return property's name
     */
    String getName();

    /**
     * Write a value to the specified attribute of the bean
     *
     * @param bean bean, must non-null
     * @param value value
     * @throws NullPointerException thrown when the bean is null
     */
    void writeValue(Object bean, Object value);

    /**
     * Read the specified attribute value of the bean
     *
     * @param bean bean, must non-null
     * @return value, or null if property's is non-existent or property's value is null
     * @throws NullPointerException thrown when the bean is null
     */
    Object getValue(Object bean);

}
