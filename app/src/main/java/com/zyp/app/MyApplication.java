package com.zyp.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;

import com.squareup.leakcanary.LeakCanary;
import com.zyp.constant.SharePrefsConstant;
import com.zyp.util.CommonUtil;
import com.zyp.util.SPUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by zhangyipeng on 2017/6/15.
 */

public class MyApplication extends Application {

    private static MyApplication application;

    /**
     * 维护Activity的List
     */
    private static List<Activity> mActivitys = Collections.synchronizedList(new LinkedList<Activity>());


    @Override
    public void onCreate() {
        /**严苛模式主要检测两大问题，一个是线程策略，即TreadPolicy，另一个是VM策略，即VmPolicy。*/
        if (AppConfig.IS_DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        super.onCreate();

        application = this;

        /**检测当前进程名称是否为应用包名，否则return （像百度地图等sdk需要在单独的进程中执行，会多次执行Application的onCreate()方法，所以为了只初始化一次应用配置，作此判断）*/
        if (!CommonUtil.getCurProcessName(this).equals(getPackageName())) {
            return;
        }

        /**初始化一些应用配置*/
        init();
    }

    private void init() {

        /**注册ActivityListener*/
        registerActivityListener();

        /**crash异常捕获*/
        if (AppConfig.IS_DEBUG) {
            CustomActivityOnCrash.install(this);
        }

        /**初始化base url*/
        if (AppConfig.IS_DEBUG) {
            final String base_url = SPUtils.getString(SPUtils.DEFAULT_FILE_NAME, SharePrefsConstant.SP_BASE_URL, "");
            if (TextUtils.isEmpty(base_url)) return;
            AppConfig.updateAllUrl(base_url);
        }

        /**初始化LeakCanary */
        if (AppConfig.IS_DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }
    }

    public static MyApplication getApplication() {
        return application;
    }


    public static void finishAllActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        for (Activity activity : mActivitys) {
            activity.finish();
        }
    }


    /**
     * 获取当前Activity（栈中最后一个压入得）
     */
    public static Activity getCurrentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return null;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        return activity;
    }

    /**
     * 注册ActivityListener
     */
    private void registerActivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    if (null == mActivitys) {
                        return;
                    }
                    mActivitys.add(activity);
                }
                @Override
                public void onActivityStarted(Activity activity) {}
                @Override
                public void onActivityResumed(Activity activity) {}
                @Override
                public void onActivityPaused(Activity activity) {}
                @Override
                public void onActivityStopped(Activity activity) {}
                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
                @Override
                public void onActivityDestroyed(Activity activity) {
                    if (null == activity && mActivitys.isEmpty()) {
                        return;
                    }
                    if (mActivitys.contains(activity)) {
                        mActivitys.remove(activity);
                    }
                }
            });
        }
    }
}
