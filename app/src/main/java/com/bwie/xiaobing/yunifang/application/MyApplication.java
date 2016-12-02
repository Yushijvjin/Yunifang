package com.bwie.xiaobing.yunifang.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.bwie.xiaobing.yunifang.utils.ImageLoaderUtils;

import org.xutils.x;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public class MyApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = Process.myTid();
        ImageLoaderUtils.initConfiguration(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);

    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler(){

        return handler;
    }
    //获取主线程
    public static Thread getMainThread(){

        return Thread.currentThread();
    }

    /**
     * 获取整个应用的上下文
     * @return
     */
    public static Context getContext(){

        return context;


    }

}
