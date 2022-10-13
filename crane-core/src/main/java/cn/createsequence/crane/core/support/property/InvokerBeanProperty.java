package cn.createsequence.crane.core.support.property;

import cn.createsequence.crane.core.support.invoker.MethodInvoker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * An implementation of {@link BeanProperty} what based on {@link MethodInvoker}
 *
 * @author huangchengxing
 */
@RequiredArgsConstructor
public class InvokerBeanProperty implements BeanProperty {

    /**
     * property's type
     */
    @Getter
    private final Class<?> type;

    /**
     * property's name
     */
    @Getter
    private final String name;

    /**
     * getter method
     */
    private final MethodInvoker getter;

    /**
     * setter method
     */
    private final MethodInvoker setter;

    /**
     * Write a value to the specified attribute of the bean
     *
     * @param bean  bean, must non-null
     * @param value value
     * @throws NullPointerException thrown when the bean is null
     */
    @Override
    public void writeValue(Object bean, Object value) {
        setter.invoke(bean, value);
    }

    /**
     * Read the specified attribute value of the bean
     *
     * @param bean bean, must non-null
     * @return value, or null if property's is non-existent or property's value is null
     * @throws NullPointerException thrown when the bean is null
     */
    @Override
    public Object getValue(Object bean) {
        return getter.invoke(bean);
    }

}
