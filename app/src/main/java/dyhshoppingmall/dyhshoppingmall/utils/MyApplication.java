package dyhshoppingmall.dyhshoppingmall.utils;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * data:2017/4/25.
 * author:dingyonghui(lx)
 * function:
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //okhttp的初始化
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
