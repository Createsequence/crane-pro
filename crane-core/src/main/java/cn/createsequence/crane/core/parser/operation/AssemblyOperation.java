package cn.createsequence.crane.core.parser.operation;

import cn.createsequence.crane.core.parser.mapping.PropertyMappings;
import cn.createsequence.crane.core.support.property.BeanPropertiesFactory;
import cn.createsequence.crane.core.support.source.DataSource;

import java.util.Set;

/**
 * A performer used to perform data assembly operations.
 *
 * @author huangchengxing
 */
public interface AssemblyOperation extends KeyPropertyOperation {

    /**
     * Get data source
     *
     * @return data source
     */
    DataSource<?> getDataSource();

    /**
     * Get properties to be mapped between source object and target object
     *
     * @return properties
     */
    PropertyMappings getPropertyMappings();

    /**
     * Get bean properties factory
     *
     * @return bean properties factory
     */
    BeanPropertiesFactory getBeanPropertiesFactory();

    /**
     * Get assembler's group
     *
     * @return assembler's group
     */
    Set<String> getGroups();

}
