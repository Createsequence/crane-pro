package cn.createsequence.crane.core.support.source;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>An implementation of {@link DataSource} based on {@link Map},
 * register the key value pair, and then obtain the value through the key.
 *
 * @author huangchengxing
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyValueDataSource implements DataSource<Object> {

    /**
     * Create a key value pair data source container based on {@link Map}.
     *
     * @param namespace namespace
     * @param dataContainer container map for caching datas, {@link HashMap} if null
     * @return {@link KeyValueDataSource} instance
     */
    public KeyValueDataSource create(String namespace, @Nullable Map<Object, Object> dataContainer) {
        return new KeyValueDataSource(
            Objects.requireNonNull(namespace),
            Objects.isNull(dataContainer) ? new HashMap<>(16) : dataContainer
        );
    }

    /**
     * namespace
     */
    @Getter
    private final String namespace;

    /**
     * container for caching data
     */
    private final Map<Object, Object> dataMap;

    /**
     * Get the corresponding data from a specific data source according to the specified key set
     *
     * @param keys keys
     * @return data grouped by key
     */
    @Override
    public Map<Object, ?> getData(Collection<Object> keys) {
        return dataMap;
    }

}
