package cn.createsequence.crane.core.util;

import cn.hutool.core.text.CharSequenceUtil;

import java.util.function.Function;

/**
 * exception utils
 *
 * @author huangchengxing
 */
public class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static <X extends Throwable> X create(Function<String, X> exceptionFactory, String messageTemplate, Object... args) {
        return exceptionFactory.apply(
            CharSequenceUtil.format(messageTemplate, args)
        );
    }

}
