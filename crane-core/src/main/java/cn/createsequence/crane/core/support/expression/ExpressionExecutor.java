package cn.createsequence.crane.core.support.expression;

/**
 * <p>An executor for executing expressions.
 *
 * @author huangchengxing
 */
@FunctionalInterface
public interface ExpressionExecutor {

    /**
     * Execute the expressionAdapter according to the context and return the execution result
     *
     * @param expressionAdapter expressionAdapter
     * @param context context
     * @return execution result, or null if non-result for expressionAdapter
     */
    Object execute(ExpressionAdapter expressionAdapter, ExpressionContextAdapter context);

}
