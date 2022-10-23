package cn.createsequence.crane.core.support.source;

import java.util.Collection;
import java.util.Map;

/**
 * <p>A data source used to provide the required data for assembly operations,
 * different data sources are distinguished by namespaces,
 * each namespace has and only corresponds to one data source.
 *
 * @author huangchengxing
 * @see EnumDictDataSource
 * @see KeyValueDataSource
 * @see InvokerDataSource
 */
public interface DataSource<K> {

    /**
     * Get the namespace corresponding to the data source
     *
     * @return namespace
     */
    String getNamespace();

    /**
     * Get the corresponding data from a specific data source according to the specified key set
     *
     * @param keys keys
     * @return data grouped by key
     */
    Map<K, ?> getData(Collection<K> keys);
    
}
