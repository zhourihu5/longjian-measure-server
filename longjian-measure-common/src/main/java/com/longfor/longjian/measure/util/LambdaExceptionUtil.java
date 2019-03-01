package com.longfor.longjian.measure.util;

import com.longfor.longjian.measure.util.infac.ThrowingConsumer;

import java.util.function.Consumer;

/**
 * lambda表达式异常处理，避免代码冗余
 */
public class LambdaExceptionUtil {
    public LambdaExceptionUtil() {
    }

    /**
     * map 转 vo 时的异常
     * @return
     */
    public static <T> Consumer<T> throwingConsumerWrapper(
            ThrowingConsumer<T, Exception> throwingConsumer) {

        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException("mapToVo Exception");
            }
        };
    }
}
