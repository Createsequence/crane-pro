package cn.createsequence.crane.core.support.source;

import java.util.Collection;

/**
 * Methods of providing data sources
 *
 * @author huangchengxing
 * @param <K> key's type
 */
@FunctionalInterface
public interface SourceProvider<K> {

    /**
     * Accept a set of key values and obtain the corresponding data source object.
     *
     * @param keys keys
     * @param <R> result type
     * @return data source object
     */
    <R> R getByKeys(Collection<K> keys);

}
