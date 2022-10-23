package cn.createsequence.crane.core.support.source;

import cn.createsequence.crane.core.support.invoker.MethodInvoker;
import cn.createsequence.crane.core.util.CollectionUtils;
import cn.hutool.core.collection.CollUtil;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>An implementation of {@link DataSource} based on specific {@link MethodInvoker}.
 *
 * @author huangchengxing
 */
public class InvokerDataSource<K> implements DataSource<K> {

    /**
     * namespace
     */
    @Getter
    protected final String namespace;

    /**
     * method invoker for obtaining the corresponding data source set according to the key
     */
    protected final SourceProvider<K> sourceProvider;

    /**
     * the identification property corresponding to the key on the source object
     */
    protected final MethodInvoker identifier;

    /**
     * mapping type
     */
    protected final MappingType mappingType;

    /**
     * constructor
     *
     * @param namespace namespace
     * @param sourceProvider source invoker
     * @param identifier sourceObjectIdentifier
     * @param mappingType mappingType
     */
    public InvokerDataSource(
        String namespace,
        SourceProvider<K> sourceProvider,
        MethodInvoker identifier, MappingType mappingType) {
        this.namespace = Objects.requireNonNull(namespace);
        this.sourceProvider = Objects.requireNonNull(sourceProvider);
        this.identifier = identifier;
        this.mappingType = Objects.requireNonNull(mappingType);
    }

    /**
     * Get the corresponding data from a specific data source according to the specified key set.
     *
     * @param keys keys
     * @return data grouped by key
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<K, ?> getData(Collection<K> keys) {
        Object sourceObj = sourceProvider.getByKeys(keys);
        if (MappingType.MAPPED == mappingType) {
            return (Map<K, ?>)sourceObj;
        }
        Collection<?> sources = CollectionUtils.adaptObjectToCollection(sourceObj);
        if (CollUtil.isEmpty(sources)) {
            return Collections.emptyMap();
        }
        // one key to one source object
        if (MappingType.ONE_TO_ONE == mappingType) {
            return sources.stream().collect(Collectors.toMap(
                t -> (K)identifier.invoke(t), Function.identity()
            ));
        }
        // one key to many source objects
        return new HashMap<>(
            sources.stream().collect(Collectors.groupingBy(t -> (K)identifier.invoke(t)))
        );
    }

}
