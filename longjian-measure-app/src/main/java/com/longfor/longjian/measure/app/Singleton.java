package com.longfor.longjian.measure.app;

/**
 * 单例模式
 */
public class Singleton {
    private static Singleton singleton;
    public static Singleton getInstance() {
        if(singleton == null) {
            synchronized(Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
