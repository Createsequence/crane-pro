package cn.createsequence.crane.core.util;

import cn.hutool.core.util.ArrayUtil;

import java.util.*;

/**
 * CollectionUtils
 *
 * @author huangchengxing
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * Adapting an array to a collection.
     *
     * @param args args
     * @return collection
     */
    public static Collection<?> adaptArrayToCollection(Object... args) {
        int len = ArrayUtil.length(args);
        if (len == 0) {
            return Collections.emptyList();
        }
        if (len == 1) {
            return Objects.isNull(args[0]) ? Collections.emptyList() : (Collection<?>)args[0];
        }
        return Arrays.asList(args);
    }

    /**
     * Adapting an object to a collection.
     *
     * @param obj obj
     * @return collection
     */
    public static Collection<?> adaptObjectToCollection(Object obj) {
        if (Objects.isNull(obj)) {
            return Collections.emptyList();
        }
        if (obj.getClass().isArray()) {
            return Arrays.asList((Object[])obj);
        }
        if (obj instanceof Collection) {
            return (Collection<?>)obj;
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>)obj).entrySet();
        }
        return Collections.singletonList(obj);
    }

}
