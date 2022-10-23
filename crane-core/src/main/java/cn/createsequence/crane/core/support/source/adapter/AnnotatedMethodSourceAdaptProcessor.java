package cn.createsequence.crane.core.support.source.adapter;

import cn.createsequence.crane.core.annotation.MethodSource;
import cn.createsequence.crane.core.support.invoker.MethodInvoker;
import cn.createsequence.crane.core.support.property.BeanPropertiesFactory;
import cn.createsequence.crane.core.support.property.BeanProperty;
import cn.createsequence.crane.core.support.source.DataSource;
import cn.createsequence.crane.core.support.source.InvokerDataSource;
import cn.createsequence.crane.core.support.source.MappingType;
import cn.createsequence.crane.core.util.AnnotationFinder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>A processor used to adapt the method annotated by {@link MethodSource} to {@link DataSource}
 *
 * @author huangchengxing
 * @see MethodSource
 */
@Slf4j
@RequiredArgsConstructor
public class AnnotatedMethodSourceAdaptProcessor {

    private final AnnotationFinder annotationFinder;
    private final BeanPropertiesFactory beanPropertiesFactory;

    /**
     * Try to parse the {@link Method} object and generate {@link DataSource}
     *
     * @param source source of method, when {@code method} it is a static method, it is allowed to be null
     * @param method Instance method or static method to be adapted
     * @return {@link DataSource} instance, or null when cannot resolve method object
     */
    public <K> Optional<DataSource<K>> tryAdapt(Object source, @NonNull Method method) {
        Objects.requireNonNull(method);
        // data source method must have return
        if (Objects.equals(Void.class, method.getReturnType())) {
            return Optional.empty();
        }
        // check method has been annotated
        MethodSource annotation = annotationFinder.findAnnotation(method, MethodSource.class);
        if (Objects.isNull(annotation)) {
            return Optional.empty();
        }
        Object sourceOfMethod = Modifier.isStatic(method.getModifiers()) ? null : source;
        return adapt(sourceOfMethod, method, annotation);
    }

    private <K> Optional<DataSource<K>> adapt(@Nullable Object sourceOfMethod, Method method, MethodSource annotation) {
        MappingType mappingType = annotation.mappingType();
        String namespace = annotation.namespace();

        // not need mapping
        if (MappingType.MAPPED == mappingType) {
            if (!Objects.equals(method.getReturnType(), Map.class)) {
                return Optional.empty();
            }
            DataSource<K> dataSource = new InvokerDataSource<>(
                namespace, new MethodSourceProvider<>(sourceOfMethod, method),
                null, mappingType
            );
            return Optional.of(dataSource);
        }

        // need mapping
        return Optional.ofNullable(getIdentifier(annotation))
            .map(keyProperty -> new InvokerDataSource<>(
                namespace, new MethodSourceProvider<>(sourceOfMethod, method),
                keyProperty, mappingType
            ));
    }

    private MethodInvoker getIdentifier(MethodSource annotation) {
        BeanProperty keyProperty = beanPropertiesFactory.getProperty(annotation.sourceType(), annotation.sourceKey());
        return Objects.isNull(keyProperty) ?
            null : (target, args) -> keyProperty.getValue(target);
    }

}
