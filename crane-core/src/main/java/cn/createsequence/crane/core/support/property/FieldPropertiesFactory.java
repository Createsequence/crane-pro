package cn.createsequence.crane.core.support.property;

import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * An implementation of {@link BeanPropertiesFactory} based on {@link Field}
 *
 * @author huangchengxing
 */
public class FieldPropertiesFactory extends AbstractReflectionPropertiesFactory {

    /**
     * Constructor
     *
     * @param typeConverter typeConverter
     */
    public FieldPropertiesFactory(TypeConverter typeConverter) {
        super(typeConverter);
    }

    /**
     * Create a {@link BeanProperty} instance for target field
     *
     * @param beanType     bean's type
     * @param field        field
     * @param propertyName propertyName
     * @return {@link BeanProperty} instance
     */
    @Override
    protected BeanProperty createBeanProperty(Class<?> beanType, Field field, String propertyName) {
        return new InvokerBeanProperty(
            field.getType(), field.getName(),
            (target, args) -> ReflectUtil.getFieldValue(target, field),
            (target, args) -> {
                Object value = ArrayUtil.get(args, 0);
                ReflectUtil.setFieldValue(target, field, typeConverter.convert(value, field.getType()));
                return null;
            }
        );
    }

}
