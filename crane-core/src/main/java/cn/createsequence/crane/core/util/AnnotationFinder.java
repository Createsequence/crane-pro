package cn.createsequence.crane.core.util;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * <p>Annotation finder, used to find the specified annotation from {@link AnnotatedElement}.
 *
 * @author huangchengxing
 */
public interface AnnotationFinder {

    /**
     * Get annotation
     *
     * @param element annotated element
     * @param annotationType annotation type
     * @param <A> annotation type
     * @return {@link Annotation}
     */
    <A extends Annotation> A findAnnotation(@Nonnull AnnotatedElement element, Class<A> annotationType);

    /**
     * Get all annotations
     *
     * @param element annotated element
     * @param annotationType annotation type
     * @param <A> annotation type
     * @return {@link Annotation}
     */
    <A extends Annotation> A[] findAllAnnotations(@Nonnull AnnotatedElement element, Class<A> annotationType);

}
