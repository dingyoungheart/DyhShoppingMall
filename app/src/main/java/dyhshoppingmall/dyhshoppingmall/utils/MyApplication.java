package dyhshoppingmall.dyhshoppingmall.utils;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * data:2017/4/25.
 * author:dingyonghui(lx)
 * function:
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //okhttp的初始化
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
        //使mContext等于application的上下文
        this.mContext=this;
    }
    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }
}
