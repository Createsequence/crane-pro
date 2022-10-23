package cn.createsequence.crane.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>An enum dict，used to adapt enum to dict items,
 * provides the function of obtaining
 * and accessing properties based on types and names.
 *
 * @author huangchengxing
 */
@Slf4j
public class EnumDict {

    private final Map<String, EnumDictType<?>> nameCache = new HashMap<>();
    private final Map<Class<?>, EnumDictType<?>> classCache = new HashMap<>();

    private static final EnumDict INSTANCE = new EnumDict();
    
    /**
     * get the default singleton object
     *
     * @return {@link EnumDict} singleton object
     */
    public static EnumDict instance() {
        return INSTANCE;
    }

    // ================================ register ================================

    /**
     * unregister
     *
     * @param targetType registered enum's type
     */
    public <T extends Enum<?>> void unregister(Class<T> targetType) {
        Optional.ofNullable(classCache.remove(targetType))
            .ifPresent(t -> nameCache.remove(t.getName()));
    }

    /**
     * unregister
     *
     * @param targetTypeName registered item's type name
     */
    public void unregister(String targetTypeName) {
        Optional.ofNullable(nameCache.remove(targetTypeName))
            .ifPresent(t -> classCache.remove(t.getType()));
    }

    /**
     * <p>register enum, use class name as type's name, and use {@link Enum#name()} as item's name,
     * if the {@link Item} annotation exists on the class,
     * it will be registered according to the annotation configuration first.
     *
     * @param targetType enum's type
     * @see Item
     */
    public <T extends Enum<?>> void register(Class<T> targetType) {
        register(targetType, null, null);
    }

    /**
     * register enum
     *
     * @param targetType target enum's type
     * @param typeName item type's name, or name of {@code targetType} if null
     * @param itemNameGetter getter method of item name from the enumeration instance, or {@link Enum#name()} if null
     */
    public <T extends Enum<?>> void register(
        Class<T> targetType, String typeName, Function<T, String> itemNameGetter) {
        // get class name and item's name getter
        Item annotation = targetType.getAnnotation(Item.class);
        if (Objects.nonNull(annotation)) {
            typeName = annotation.typeName();
            itemNameGetter = t -> ReflectUtils.findGetterMethod(targetType, annotation.itemNameProperty())
                .map(m -> ReflectUtil.invoke(t, m))
                .map(String::valueOf)
                .orElseThrow(() -> new IllegalArgumentException(CharSequenceUtil.format(
                    "can not get item's name from enum [{}]", targetType
                )));
        }
        typeName = ObjectUtil.defaultIfNull(typeName, targetType.getSimpleName());
        itemNameGetter = ObjectUtil.defaultIfNull(itemNameGetter, T::name);

        // generate enum's item
        EnumDictType<T> type = new EnumDictType<>(targetType, typeName);
        List<EnumDictItem<T>> targets = new ArrayList<>();
        for (T item : targetType.getEnumConstants()) {
            targets.add(new EnumDictItem<>(type, item, itemNameGetter.apply(item)));
        }
        targets.forEach(type::addItem);
        log.info(
            "register enum [{}]({})：[{}]",
            typeName, targetType,
            targets.stream()
                .map(t -> CharSequenceUtil.format("{}->{}", t.getName(), t.getTarget().name()))
                .collect(Collectors.joining(", "))
        );

        nameCache.put(typeName, type);
        classCache.put(targetType, type);
    }

    // ================================ get ================================

    /**
     * get the specified {@link EnumDictType} instance
     *
     * @param typeName typeName
     * @return {@link EnumDictType} instance if present, null otherwise
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<?>> EnumDictType<T> getType(String typeName) {
        return (EnumDictType<T>) nameCache.get(typeName);
    }

    /**
     * get the specified {@link EnumDictType} instance
     *
     * @param enumClass enumClass
     * @return {@link EnumDictType} instance if present, null otherwise
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<?>> EnumDictType<T> getType(Class<T> enumClass) {
        return (EnumDictType<T>) classCache.get(enumClass);
    }

    /**
     * get the specified {@link EnumDictItem} instance
     *
     * @param typeName typeName
     * @param itemName itemName
     * @return {@link EnumDictItem} instance if present, null otherwise
     */
    public <T extends Enum<?>> EnumDictItem<T> getItem(String typeName, String itemName) {
        EnumDictType<T> type = getType(typeName);
        return type == null ?
            null : type.get(itemName);
    }

    /**
     * get the specified {@link EnumDictItem} instance
     *
     * @param enumClass enumClass
     * @param itemName itemName
     * @return {@link EnumDictItem} instance if present, null otherwise
     */
    public <T extends Enum<?>> EnumDictItem<T> getItem(Class<T> enumClass, String itemName) {
        EnumDictType<T> type = getType(enumClass);
        return type == null ?
            null : type.get(itemName);
    }

    /**
     * get the specified enum instance
     *
     * @param enumClass enumClass
     * @param itemName itemName
     * @return {@link Enum} instance if present, null otherwise
     */
    public <T extends Enum<?>> T getEnum(Class<T> enumClass, String itemName) {
        return Optional.ofNullable(getType(enumClass))
            .map(t -> t.get(itemName))
            .map(EnumDictItem::getTarget)
            .orElse(null);
    }

    /**
     * get the specified enum instance
     *
     * @param typeName typeName
     * @param itemName itemName
     * @return {@link Enum} instance if present, null otherwise
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<?>> T getEnum(String typeName, String itemName) {
        return (T) Optional.ofNullable(getType(typeName))
            .map(t -> t.get(itemName))
            .map(EnumDictItem::getTarget)
            .orElse(null);
    }

    // ================================ model ================================

    /**
     * All dict items under the same enum class
     */
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Getter
    public static class EnumDictType<T extends Enum<?>> {

        /**
         * enum's type
         */
        @EqualsAndHashCode.Include
        private final Class<T> type;

        /**
         * enum's name
         */
        @EqualsAndHashCode.Include
        private final String name;

        /**
         * items cache grouped by item's name
         */
        private final Map<String, EnumDictItem<T>> nameCache;

        /**
         * items cache grouped by enum instance
         */
        private final Map<Enum<?>, EnumDictItem<T>> enumCache;

        /**
         * constructor
         *
         * @param type enum's type
         * @param name item's name
         */
        protected EnumDictType(Class<T> type, String name) {
            this.type = type;
            this.name = name;
            int len = type.getEnumConstants().length;
            nameCache = new HashMap<>(len);
            enumCache = new HashMap<>(len);
        }

        /**
         * get by item's name instances
         *
         * @param itemName 字典项名称
         * @return {@link EnumDictItem} instance if present, null otherwise
         */
        public EnumDictItem<T> get(String itemName) {
            return nameCache.get(itemName);
        }

        /**
         * get by enum instances
         *
         * @param target 字典实例
         * @return {@link EnumDictItem} instance if present, null otherwise
         */
        public EnumDictItem<T> get(Enum<?> target) {
            return enumCache.get(target);
        }

        /**
         * add item
         *
         * @param item item
         */
        public void addItem(EnumDictItem<T> item) {
            nameCache.put(item.getName(), item);
            enumCache.put(item.getTarget(), item);
        }
    }
    
    /**
     * Dict item
     */
    @EqualsAndHashCode(callSuper = false)
    @Getter
    public static class EnumDictItem<T extends Enum<?>> extends HashMap<String, Object> {
        private final transient EnumDictType<T> type;
        private final T target;
        private final String name;

        @SuppressWarnings("unchecked")
        protected EnumDictItem(EnumDictType<T> type, T target, String name) {
            this.type = type;
            this.name = name;
            this.target = target;

            Map<String, Object> properties = new HashMap<>(BeanUtil.beanToMap(target));
            putAll(properties);
        }

    }
    
    /**
     * Annotate an enumeration class and declare it as a registrable dictionary item
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Item {

        /**
         * 类型名，指定从枚举中哪个属性获得，默认为{@link Enum#name()}
         */
        String itemNameProperty();

        /**
         * 枚举类在字典中的名称，默认为类名
         */
        String typeName();

    }

}
