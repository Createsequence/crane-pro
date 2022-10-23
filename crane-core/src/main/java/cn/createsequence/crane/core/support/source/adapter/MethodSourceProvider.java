package cn.createsequence.crane.core.support.source.adapter;

import cn.createsequence.crane.core.support.source.SourceProvider;
import cn.hutool.core.util.ReflectUtil;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;

/**
 * A simple implementation of {@link SourceProvider}
 *
 * @param <K> key type
 */
@RequiredArgsConstructor
class MethodSourceProvider<K> implements SourceProvider<K> {

    /**
     * source of method
     */
    @Nullable
    private final Object source;

    /**
     * target method
     */
    @Nonnull
    private final Method method;

    /**
     * Accept a set of key values and obtain the corresponding data source object.
     *
     * @param keys keys
     * @return data source object
     */
    @Override
    public <R> R getByKeys(Collection<K> keys) {
        return Objects.isNull(source) ? ReflectUtil.invokeStatic(method, keys) : ReflectUtil.invoke(source, method, keys);
    }

}
