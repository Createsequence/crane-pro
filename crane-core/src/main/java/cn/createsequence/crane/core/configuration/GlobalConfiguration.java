package cn.createsequence.crane.core.configuration;

import cn.createsequence.crane.core.executor.OperationExecutor;
import cn.createsequence.crane.core.executor.handler.AssembleHandler;
import cn.createsequence.crane.core.parser.operation.BeanOperation;
import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.createsequence.crane.core.support.expression.ExpressionExecutor;
import cn.createsequence.crane.core.support.source.DataSource;

import java.util.Collection;

/**
 * Global configuration
 *
 * @author huangchengxing
 */
public interface GlobalConfiguration {

    /**
     * Register handler
     *
     * @param handler handler
     */
    void registerAssembleHandler(AssembleHandler handler);

    /**
     * Get all registered handler
     *
     * @return handler
     */
    Collection<AssembleHandler> getAssembleHandlers();

    /**
     * Register bean's operations
     *
     * @param beanOperation bean's operations
     */
    void registerBeanOperations(BeanOperation beanOperation);

    /**
     * Get bean's operations by type
     *
     * @param beanType bean's type
     */
    BeanOperation getBeanOperations(Class<?> beanType);

    /**
     * Register data source
     *
     * @param dataSource data source
     */
    void registerDataSource(DataSource<?> dataSource);

    /**
     * Get data source by namespace
     *
     * @param namespace namespace of data source
     */
    DataSource<?> getDataSource(String namespace);

    /**
     * Register operation executor
     *
     * @param executor executor
     */
    void registerOperationExecutor(OperationExecutor executor);

    /**
     * Get operation executor
     *
     * @return operation executor
     */
    OperationExecutor getOperationExecutor(Class<?> implementationType);

    /**
     * Set type converter
     *
     * @param typeConverter typeConverter
     */
    void setTypeConverter(TypeConverter typeConverter);

    /**
     * Get type converter
     *
     * @return  typeConverter
     */
    TypeConverter getTypeConverter();

    /**
     * Set expression executor
     *
     * @param expressionExecutor expressionExecutor
     */
    void setExpressionExecutor(ExpressionExecutor expressionExecutor);

    /**
     * Get expression executor
     *
     * @return expression executor
     */
    ExpressionExecutor getExpressionExecutor();

}
