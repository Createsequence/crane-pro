package cn.createsequence.crane.core.support.converter;

/**
 * type converter
 *
 * @author huangchengxing
 */
@FunctionalInterface
public interface TypeConverter {

    /**
     * <p>Convert the value to the specified type,
     * and return the default value if the conversion fails.
     *
     * @param value value
     * @param resultType resultType
     * @param defaultValue defaultValue
     * @return converted value, or default value if the conversion fails
     */
    <T, R> R convert(T value, Class<R> resultType, R defaultValue);

    /**
     * <p>Convert the value to the specified type,
     * and return null if the conversion fails.
     *
     * @param value target
     * @param resultType resultType
     * @return converted value, or null if the conversion fails
     */
    default <T, R> R convert(T value, Class<R> resultType) {
        return convert(value, resultType, null);
    }

}
