package cn.createsequence.crane.core.support.expression;

import java.util.Map;

/**
 * <p>An adapter of expression context,
 * used to provide parameters and methods during expression execution
 *
 * @author huangchengxing
 */
public interface ExpressionContextAdapter {

    /**
     * Get root object
     *
     * @return root object
     */
    Object getRoot();

    /**
     * Set root object
     *
     * @param root root
     */
    void setRoot(Object root);

    /**
     * register variables
     *
     * @param name name
     * @param value values
     */
    void registerVariables(String name, Object value);

    /**
     * Get all registered variables
     *
     * @return all registered variables
     */
    Map<String, Object> getVariables();

}
