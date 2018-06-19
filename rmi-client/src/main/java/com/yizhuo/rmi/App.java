package com.yizhuo.rmi;

/**
 * Created by yizhuo on 2018/6/9.
 */
public class App {

    private static volatile App instance = null;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

    public static void main(String[] args) {
        App.getInstance();
    }
}
