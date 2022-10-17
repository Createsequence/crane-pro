package cn.createsequence.crane.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * test for {@link AnnotationFinder}
 *
 * @author huangchengxing
 */
public class SimpleAnnotationFinderTest {

    @Test
    public void findAnnotation() {
        AnnotationFinder finder = new SimpleAnnotationFinder();
        Assert.assertEquals(
            Foo.class.getAnnotation(Annotation1.class),
            finder.findAnnotation(Foo.class, Annotation1.class)
        );
    }

    @Test
    public void findAllAnnotations() {
        AnnotationFinder finder = new SimpleAnnotationFinder();
        Assert.assertArrayEquals(
            Foo.class.getAnnotationsByType(Annotation1.class),
            finder.findAllAnnotations(Foo.class, Annotation1.class)
        );
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    private @interface Annotation1 {}

    @Annotation1
    private static class Foo{};

}
