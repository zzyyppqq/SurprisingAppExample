package com.zyp.app;

import com.zyp.BuildConfig;

/**
 * Created by zhangyipeng on 2017/6/15.
 */

public class AppConfig {
    /**在build.gradle中配置的变量*/
    public static String BASE_URL = BuildConfig.BASE_URL;
    /** 默认的base url */
    public final static String DEFAULT_BASE_URL = BuildConfig.BASE_URL;
    public final static String APPLICATION_ID = BuildConfig.APPLICATION_ID;
    public final static int VERSION_CODE = BuildConfig.VERSION_CODE;
    public final static boolean IS_LOG = BuildConfig.IS_LOG;
    public final static boolean IS_DEBUG = BuildConfig.IS_DEBUG;
    public final static String[] BASE_TEST_URL = BuildConfig.BASE_TEST_URL;


    /** app api url*/
    public static String URL_ZYP_ONE = BASE_URL + "/home/img";
    public static String URL_ZYP_TWO = BASE_URL + "/mine/list";
    public static String URL_ZYP_THREE = BASE_URL + "/center/info";


    public static void updateAllUrl(String baseUrl) {
        BASE_URL = baseUrl;
        /**切换baseUrl后重新初始化url*/
        URL_ZYP_ONE = BASE_URL + "/home/img";
        URL_ZYP_TWO = BASE_URL + "/mine/list";
        URL_ZYP_THREE = BASE_URL + "/center/info";
    }
}
