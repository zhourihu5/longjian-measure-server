package com.longfor.longjian.measure.consts.util.infac;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
