package cn.createsequence.crane.core.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * ReflectUtils
 *
 * @author huangchengxing
 */
public class ReflectUtils {

    /**
     * method name prefix such like "setXXX"
     */
    private static final String SET_PREFIX = "set";

    /**
     * method name prefix such like "isXXX"
     */
    private static final String IS_PREFIX = "is";

    /**
     * method name prefix such like "getXXX"
     */
    private static final String GET_PREFIX = "get";

    private ReflectUtils() {
    }

    /**
     * find getter method
     *
     * @param beanType bean's type
     * @param field field
     * @return java.util.Optional<java.lang.reflect.Method>
     */
    public static Optional<Method> findGetterMethod(Class<?> beanType, Field field) {
        // find isXXX method
        Class<?> fieldType = field.getType();
        if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) {
            String booleanGetterName = CharSequenceUtil.upperFirstAndAddPre(field.getName(), IS_PREFIX);
            return Optional.ofNullable(ReflectUtil.getMethod(beanType, booleanGetterName));
        }

        // find getXXX method
        String getterName = CharSequenceUtil.upperFirstAndAddPre(field.getName(), GET_PREFIX);
        Method method = ReflectUtil.getMethod(beanType, getterName);
        if (Objects.nonNull(method)) {
            return Optional.of(method);
        }

        // find fluent method
        getterName = field.getName();
        return Optional.ofNullable(ReflectUtil.getMethod(beanType, getterName));
    }

    /**
     * find getter method
     *
     * @param beanType bean's type
     * @param fieldName field's name
     * @return java.util.Optional<java.lang.reflect.Method>
     */
    public static Optional<Method> findGetterMethod(Class<?> beanType, String fieldName) {
        // find getXXX method
        String getterName = CharSequenceUtil.upperFirstAndAddPre(fieldName, GET_PREFIX);
        Method method = ReflectUtil.getMethod(beanType, getterName);
        if (Objects.nonNull(method)) {
            return Optional.of(method);
        }

        // find fluent method
        Optional<Method> fluentGetter = Optional.ofNullable(ReflectUtil.getMethod(beanType, fieldName));
        if (fluentGetter.isPresent()) {
            return fluentGetter;
        }

        // find isXXX method
        String booleanGetterName = CharSequenceUtil.upperFirstAndAddPre(fieldName, IS_PREFIX);
        return Optional.ofNullable(ReflectUtil.getMethod(beanType, booleanGetterName));
    }

    /**
     * find setter method
     *
     * @param beanType bean's type
     * @param field field
     * @return java.util.Optional<java.lang.reflect.Method>
     */
    public static Optional<Method> findSetterMethod(Class<?> beanType, Field field) {
        // find isXXX method
        Class<?> fieldType = field.getType();
        if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) {
            String booleanSetterName = CharSequenceUtil.upperFirstAndAddPre(field.getName(), IS_PREFIX);
            return Optional.ofNullable(ReflectUtil.getMethod(beanType, booleanSetterName, fieldType));
        }

        // find getXXX method
        String setterName = CharSequenceUtil.upperFirstAndAddPre(field.getName(), SET_PREFIX);
        Method method = ReflectUtil.getMethod(beanType, setterName, fieldType);
        if (Objects.nonNull(method)) {
            return Optional.of(method);
        }

        // find fluent method
        setterName = field.getName();
        return Optional.ofNullable(ReflectUtil.getMethod(beanType, setterName, fieldType));
    }

}
