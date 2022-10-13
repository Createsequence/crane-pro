package cn.createsequence.crane.core.support.expression;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangchengxing
 */
@AllArgsConstructor
@NoArgsConstructor
public class SimpleExpressionContextAdapter implements ExpressionContextAdapter {

    /**
     * root object
     */
    @Setter
    @Getter
    private Object root;

    /**
     * variables
     */
    @Getter
    private final Map<String, Object> variables = new HashMap<>(4);

    /**
     * register variables
     *
     * @param name  name
     * @param value values
     */
    @Override
    public void registerVariables(String name, Object value) {
        variables.put(name, value);
    }

}
