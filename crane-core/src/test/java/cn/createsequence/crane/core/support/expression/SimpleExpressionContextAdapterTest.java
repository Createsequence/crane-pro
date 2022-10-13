package cn.createsequence.crane.core.support.expression;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test for {@link SimpleExpressionContextAdapter}
 *
 * @author huangchengxing
 */
public class SimpleExpressionContextAdapterTest {

    @Test
    public void registerVariables() {
        ExpressionContextAdapter contextAdapter = new SimpleExpressionContextAdapter();
        contextAdapter.registerVariables("id", 1);
        Assert.assertEquals(1, contextAdapter.getVariables().get("id"));
    }

    @Test
    public void getRoot() {
        Object root = new Object();
        ExpressionContextAdapter contextAdapter = new SimpleExpressionContextAdapter(root);
        Assert.assertEquals(root, contextAdapter.getRoot());
        contextAdapter.setRoot(new Object());
        Assert.assertNotEquals(root, contextAdapter.getRoot());
    }

}