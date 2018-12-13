package com.longfor.longjian.measure.app;

/**
 * 单例模式
 */
public class Singleton {
    private static volatile Singleton s;
    public static Singleton getInstance(){
        synchronized (Singleton.class){
            if(s==null){
                s=new Singleton();
            }
        }
        return s;
    }

}
