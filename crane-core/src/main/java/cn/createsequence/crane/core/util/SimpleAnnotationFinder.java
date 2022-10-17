package cn.createsequence.crane.core.util;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * A simple implementation for {@link AnnotationFinder}.
 *
 * @author huangchengxing
 */
public class SimpleAnnotationFinder implements AnnotationFinder {

    /**
     * Get annotation
     *
     * @param element        annotated element
     * @param annotationType annotation type
     * @return {@link Annotation}
     */
    @Override
    public <A extends Annotation> A findAnnotation(@Nonnull AnnotatedElement element, Class<A> annotationType) {
        return element.getAnnotation(annotationType);
    }

    /**
     * Get all annotations
     *
     * @param element        annotated element
     * @param annotationType annotation type
     * @return {@link Annotation}
     */
    @Override
    public <A extends Annotation> A[] findAllAnnotations(@Nonnull AnnotatedElement element, Class<A> annotationType) {
        return element.getAnnotationsByType(annotationType);
    }
}
