package cn.createsequence.crane.core.support.expression;

import cn.createsequence.crane.core.support.converter.TypeConverter;
import cn.hutool.core.exceptions.CheckedUtil;
import com.google.common.collect.MapMaker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ognl.Ognl;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

/**
 * @author huangchengxing
 */
@RequiredArgsConstructor
public class OgnlExpressionExecutor implements ExpressionExecutor {

    /**
     * expression cache
     */
    private final ConcurrentMap<String, Object> expressionsCache = new MapMaker().weakKeys().makeMap();

    /**
     * type converter
     */
    private final TypeConverter typeConverter;

    /**
     * Execute the expressionAdapter according to the context and return the execution result
     *
     * @param expressionAdapter expressionAdapter
     * @param context    context
     * @return execution result, or null if non-result for expressionAdapter
     */
    @SneakyThrows
    @Override
    public Object execute(ExpressionAdapter expressionAdapter, ExpressionContextAdapter context) {
        Object parsedExpression = parseExpression(expressionAdapter);
        Object result = Ognl.getValue(parsedExpression, context.getVariables(), context.getRoot());
        Class<?> resultType = expressionAdapter.getResultType();
        return Objects.nonNull(resultType) ?
            typeConverter.convert(result, resultType) : result;
    }

    private Object parseExpression(ExpressionAdapter expressionAdapter) {
        return expressionsCache.computeIfAbsent(
            expressionAdapter.getExpression(), CheckedUtil.uncheck(Ognl::parseExpression)::call
        );
    }



}
