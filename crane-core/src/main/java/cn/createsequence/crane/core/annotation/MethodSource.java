package cn.createsequence.crane.core.annotation;

import cn.createsequence.crane.core.support.source.MappingType;
import cn.createsequence.crane.core.support.source.adapter.AnnotatedMethodSourceAdaptProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark method as a data source.
 *
 * @author huangchengxing
 * @see AnnotatedMethodSourceAdaptProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodSource {

    /**
     * datasource namespace
     *
     * @return namespace
     */
    String namespace();

    /**
     * mapping type
     *
     * @return mapping type
     */
    MappingType mappingType() default MappingType.ONE_TO_ONE;

    /**
     * type of source object,
     * when {@link #mappingType()} is {@link MappingType#MAPPED}, it can be empty
     *
     * @return source object
     */
    Class<?> sourceType() default Object.class;

    /**
     * key property of source obj,
     * when {@link #mappingType()} is {@link MappingType#MAPPED}, it can be empty
     *
     * @return key property
     */
    String sourceKey() default "";

}
