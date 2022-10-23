package cn.createsequence.crane.core.parser.mapping;

/**
 * <p>A pair of property with a specified mapping relationship
 * between a source object and an object obtained from a data source.
 *
 * @author huangchengxing
 */
public interface PropertyMapping {

    /**
     * Get the name of the specified property in the data source object
     *
     * @return property's name
     */
    String getSource();

    /**
     * <p>Get the name of the specified property in the target object,
     * the target object will get the value of the property
     * in the data source object specified by {@link #getSource}.
     *
     * @return property's name
     */
    String getReference();

}
