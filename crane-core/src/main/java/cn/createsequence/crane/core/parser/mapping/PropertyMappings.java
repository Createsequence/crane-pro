package cn.createsequence.crane.core.parser.mapping;

import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * <p>A set of {@link PropertyMapping} aggregations with an associated relationship,
 * It is used to describe how to map the property value of
 * the object obtained from the {@link cn.createsequence.crane.core.support.source.DataSource}
 * to the property of the target object.
 *
 * @author huangchengxing
 * @see PropertyMapping
 */
public interface PropertyMappings extends Iterable<PropertyMapping> {

    /**
     * <p>Get the data source property associated with
     * the property of the specified target object.
     *
     * @param reference property of target object
     * @return properties of data source object
     */
    Collection<PropertyMapping> getMappedSourcesByReference(String reference);

}
