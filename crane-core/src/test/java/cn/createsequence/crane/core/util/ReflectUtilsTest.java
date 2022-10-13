package cn.createsequence.crane.core.util;

import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * test for {@link ReflectUtils}
 *
 * @author huangchengxing
 */
public class ReflectUtilsTest {

    @Test
    public void testFindGetterMethodByName() {
        Assert.assertTrue(
            ReflectUtils.findGetterMethod(Foo.class, "standard").isPresent()
        );
        Assert.assertTrue(
            ReflectUtils.findGetterMethod(Foo.class, "fluent").isPresent()
        );
        Assert.assertTrue(
            ReflectUtils.findGetterMethod(Foo.class, "flag").isPresent()
        );
    }

    @SneakyThrows
    @Test
    public void testFindGetterMethodByField() {
        Field field = Foo.class.getDeclaredField("standard");
        Assert.assertTrue(
            ReflectUtils.findGetterMethod(Foo.class, field).isPresent()
        );

        field = Foo.class.getDeclaredField("fluent");
        Assert.assertTrue(
            ReflectUtils.findGetterMethod(Foo.class, field).isPresent()
        );

        field = Foo.class.getDeclaredField("flag");
        Assert.assertTrue(
            ReflectUtils.findGetterMethod(Foo.class, field).isPresent()
        );
    }

    @SneakyThrows
    @Test
    public void testFindSetterMethod() {
        Field field = Foo.class.getDeclaredField("standard");
        Assert.assertTrue(
            ReflectUtils.findSetterMethod(Foo.class, field).isPresent()
        );

        field = Foo.class.getDeclaredField("fluent");
        Assert.assertTrue(
            ReflectUtils.findSetterMethod(Foo.class, field).isPresent()
        );

        field = Foo.class.getDeclaredField("flag");
        Assert.assertTrue(
            ReflectUtils.findSetterMethod(Foo.class, field).isPresent()
        );
    }

    private static class Foo {

        private Integer standard;

        private Integer fluent;

        private boolean flag;

        public Integer getStandard() {
            return standard;
        }
        public void setStandard(Integer standard) {

        }

        public Integer fluent() {
            return fluent;
        }
        public void fluent(Integer id) {

        }

        public boolean isFlag() {
            return flag;
        }
        public void isFlag(boolean flag) {
        }

    }

}