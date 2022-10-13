package cn.createsequence.crane.core.support.converter;

import cn.hutool.core.convert.Convert;

/**
 * A simple implementation of {@link TypeConverter} what
 * based on {@link cn.hutool.core.convert.Convert}
 *
 * @author huangchengxing
 */
public class SimpleTypeConvert implements TypeConverter {

    /**
     * <p>Convert the value to the specified type,
     * and return the default value if the conversion fails.
     *
     * @param value        value
     * @param resultType   resultType
     * @param defaultValue defaultValue
     * @return converted value, or default value if the conversion fails
     */
    @Override
    public <T, R> R convert(T value, Class<R> resultType, R defaultValue) {
        return Convert.convert(resultType, value, defaultValue);
    }

}
