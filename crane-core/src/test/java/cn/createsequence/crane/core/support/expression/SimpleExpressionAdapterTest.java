package cn.createsequence.crane.core.support.expression;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test for {@link SimpleExpressionAdapter}
 *
 * @author huangchengxing
 */
public class SimpleExpressionAdapterTest {

    @Test
    public void getExpression() {
        ExpressionAdapter expressionAdapter = ExpressionAdapter.of("123");
        Assert.assertEquals("123", expressionAdapter.getExpression());
    }

    @Test
    public void getResultType() {
        ExpressionAdapter expressionAdapter = ExpressionAdapter.of("123", Void.class);
        Assert.assertEquals(Void.class, expressionAdapter.getResultType());
    }
    
}