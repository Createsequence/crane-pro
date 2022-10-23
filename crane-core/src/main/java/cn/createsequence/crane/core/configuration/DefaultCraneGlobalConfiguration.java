package cn.createsequence.crane.core.configuration;

import cn.createsequence.crane.core.executor.OperationExecutor;
import cn.createsequence.crane.core.executor.handler.AssembleHandler;
import cn.createsequence.crane.core.parser.operation.BeanOperation;
import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.createsequence.crane.core.support.expression.ExpressionExecutor;
import cn.createsequence.crane.core.support.property.BeanPropertiesFactory;
import cn.createsequence.crane.core.support.source.DataSource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * Global configuration
 *
 * @author huangchengxing
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultCraneGlobalConfiguration implements GlobalConfiguration {

    /**
     *  handlers
     */
    @Getter
    private final List<AssembleHandler> assembleHandlers = new ArrayList<>();

    /**
     * registered bean operations grouped by bean type
     */
    private final Map<Class<?>, BeanOperation> beanOperations = new HashMap<>(16);

    /**
     * bean properties factories grouped by implementation type
     */
    private final Map<Class<?>, BeanPropertiesFactory> beanPropertiesFactories = new HashMap<>(16);

    /**
     * data sources grouped by namespace
     */
    private final Map<String, DataSource<?>> dataSources = new HashMap<>(16);

    /**
     * operation executors grouped by implementation type
     */
    private final Map<Class<?>, OperationExecutor> operationExecutors = new HashMap<>(16);

    /**
     * type converter
     */
    @Setter
    @Getter
    private TypeConverter typeConverter;

    /**
     * expression executor
     */
    @Setter
    @Getter
    private ExpressionExecutor expressionExecutor;

    /**
     * Register handler
     *
     * @param handler handler
     */
    @Override
    public void registerAssembleHandler(AssembleHandler handler) {
        assembleHandlers.add(Objects.requireNonNull(handler));
    }

    /**
     * Register bean's operations
     *
     * @param beanOperation bean's operations
     */
    @Override
    public void registerBeanOperations(BeanOperation beanOperation) {
        Objects.requireNonNull(beanOperation);
        beanOperations.put(beanOperation.getTargetType(), beanOperation);
    }

    /**
     * Get bean's operations by type
     *
     * @param beanType bean's type
     */
    @Override
    public BeanOperation getBeanOperations(Class<?> beanType) {
        return beanOperations.get(beanType);
    }

    /**
     * Register data source
     *
     * @param dataSource data source
     */
    @Override
    public void registerDataSource(DataSource<?> dataSource) {
        Objects.requireNonNull(dataSource);
        dataSources.put(dataSource.getNamespace(), dataSource);
    }

    /**
     * Get data source by namespace
     *
     * @param namespace namespace of data source
     */
    @Override
    public DataSource<?> getDataSource(String namespace) {
        return dataSources.get(namespace);
    }

    /**
     * Register operation executor
     *
     * @param executor executor
     */
    @Override
    public void registerOperationExecutor(OperationExecutor executor) {
        Objects.requireNonNull(executor);
        operationExecutors.put(executor.getClass(), executor);
    }

    /**
     * Get operation executor
     *
     * @return operation executor
     */
    @Override
    public OperationExecutor getOperationExecutor(Class<?> implementationType) {
        return operationExecutors.get(implementationType);
    }

}
