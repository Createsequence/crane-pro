package cn.createsequence.crane.core.support.source;

import java.util.Map;

/**
 * The correspondence between the single key value
 * obtained from the target object and the data source object.
 *
 * @author huangchengxing
 */
public enum MappingType {

    /**
     * <p>The obtained data source has been mapped
     * according to the key value obtained from the target object.
     * In this case, the return value provided by the data source method
     * must be a {@link Map} set with the key value as the key
     */
    MAPPED,

    /**
     * One target object corresponds to multiple data source objects
     */
    ONE_TO_MANY,

    /**
     * One target object corresponds to one data source object
     */
    ONE_TO_ONE;

}
