package cn.createsequence.crane.core.support.source;

import cn.createsequence.crane.core.util.EnumDict;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p>An implementation of {@link DataSource} based on {@link EnumDict},
 * register the enum to {@link EnumDict} held by the current instance,
 * then the corresponding {@link EnumDict.EnumDictItem} instance can be obtained through the specified typeName.
 *
 * @author huangchengxing
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumDictDataSource implements DataSource<String> {

    /**
     * <p>Re-register the enum type with the {@code enumDict},
     * and generate the data source corresponding to
     * this type of enum according to the registered information.
     *
     * @param namespace namespace
     * @param enumDict enumDict
     * @param enumType enumType
     * @return {@link EnumDictDataSource} instance
     * @see EnumDict#register(Class)
     */
    public static EnumDictDataSource forEnumType(
        String namespace, EnumDict enumDict, Class<? extends Enum<?>> enumType) {
        Objects.requireNonNull(enumDict);
        Objects.requireNonNull(enumType);
        enumDict.unregister(enumType);
        enumDict.register(enumType);
        return new EnumDictDataSource(namespace, enumDict.getType(enumType));
    }

    /**
     * namespace
     */
    @Getter
    private final String namespace;

    /**
     * enum cache
     */
    private final EnumDict.EnumDictType<?> dictType;

    /**
     * Get the corresponding data from a specific data source according to the specified key set
     *
     * @param keys keys
     * @return data grouped by key
     */
    @Override
    public Map<String, ? extends EnumDict.EnumDictItem<?>> getData(@Nonnull Collection<String> keys) {
        return dictType.getNameCache();
    }

}
