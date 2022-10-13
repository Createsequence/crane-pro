package cn.createsequence.crane.core.support.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * An adapter of expression
 *
 * @author huangchengxing
 */
@Getter
@RequiredArgsConstructor
public class SimpleExpressionAdapter implements ExpressionAdapter {

    /**
     * expression
     */
    private final String expression;

    /**
     * result type
     */
    private final Class<?> resultType;

}
