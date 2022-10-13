package cn.createsequence.crane.core.support.expression;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * <p>An expressions that can be executed.
 *
 * @author huangchengxing
 * @see SimpleExpressionAdapter
 */
public interface ExpressionAdapter {

    /**
     * Create a {@link ExpressionAdapter} instance with a return value
     *
     * @param expression expression
     * @param resultType resultType
     * @return {@link ExpressionAdapter} instance
     */
    static ExpressionAdapter of(String expression, @Nullable Class<?> resultType) {
        return new SimpleExpressionAdapter(expression, resultType);
    }

    /**
     * Create a {@link ExpressionAdapter} instance with a non-return value
     *
     * @param expression expression
     * @return {@link ExpressionAdapter} instance
     */
    static ExpressionAdapter of(String expression) {
        return of(expression, null);
    }

    /**
     * Get expression
     *
     * @return expression
     */
    String getExpression();

    /**
     * Get expression execution result type
     *
     * @return result type, or null if non-result for expression
     */
    Class<?> getResultType();

}
