package cn.createsequence.crane.core.support.converter;

import java.util.Objects;

/**
 * <p>A implementation of {@link TypeConverter},
 * only when value's type is assignable from result's type,
 * return this value, defaultValue otherwise.
 *
 * @author huangchengxing
 */
public class ClassCastConverter implements TypeConverter {

    /**
     * <p>Convert the value to the specified type,
     * and return the default value if the conversion fails.
     *
     * @param value        value
     * @param resultType   resultType
     * @param defaultValue defaultValue
     * @return input value, or default value if value's type isn't assignable from result's type
     */
    @SuppressWarnings("unchkeced")
    @Override
    public <T, R> R convert(T value, Class<R> resultType, R defaultValue) {
        if (Objects.isNull(value)) {
            return null;
        }
        R result;
        try {
            result = resultType.cast(value);
        } catch (ClassCastException e) {
            result = defaultValue;
        }
        return result;
    }

}
