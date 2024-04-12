package com.rain.shiro.framework.manager.factory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
public class AsyncFactory {

    /**
     * 记录登录信息
     *
     * @return 任务task
     */
    public static TimerTask record() {
        return new TimerTask() {
            @Override
            public void run() {
                // 异步处理业务
                System.out.println("异步处理业务todo...");
            }
        };
    }
}
