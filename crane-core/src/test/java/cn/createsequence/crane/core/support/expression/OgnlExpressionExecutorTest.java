package cn.createsequence.crane.core.support.expression;

import cn.createsequence.crane.core.support.converter.SimpleTypeConvert;
import ognl.OgnlException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test for {@link OgnlExpressionExecutor}
 *
 * @author huangchengxing
 */
public class OgnlExpressionExecutorTest {

    @Test
    public void execute() {
        ExpressionContextAdapter contextAdapter = new SimpleExpressionContextAdapter();
        contextAdapter.registerVariables("a", 1);
        contextAdapter.registerVariables("b", 2);

        ExpressionExecutor executor = new OgnlExpressionExecutor(new SimpleTypeConvert());
        ExpressionAdapter expressionAdapter = ExpressionAdapter.of("#a + #b", Integer.class);
        Assert.assertEquals(3, executor.execute(expressionAdapter, contextAdapter));

        Assert.assertThrows(
            OgnlException.class,
            ()-> executor.execute(ExpressionAdapter.of(""), contextAdapter)
        );
    }

}