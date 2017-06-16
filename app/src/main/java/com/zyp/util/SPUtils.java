package com.zyp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.zyp.app.MyApplication;
import com.zyp.log.Logger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * 统一ShredPreferences封装类
 */
public class SPUtils {
    /*
     * 对SharedPreferences的使用做了封装，put(), get(), remore(), clear()方法；使用时指定存储或查询的文件名
     * 对commit操作使用SharedPreferencesCompat.apply()代替
     *  原因：commit()方法是同步的，一般使用过程中commit()操作是在UI线程中，所以尽可能异步。
     *  为了兼容apply() new API, SharedPreferencesCompat做了兼容
     */
    public static final String DEFAULT_FILE_NAME = "com.zyp";
    public static final String COM_ZYP_PREFERENCES = MyApplication.getApplication().getPackageName() + "_preferences";


    public static SharedPreferences getSP(String spName) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp;
    }

    public static SharedPreferences.Editor getSPEdit(String spName) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.edit();
    }

    public static void putStringSync(String spName, String key, String value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putDoubleSync(String spName, String key, double value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, String.valueOf(value));
        editor.commit();
    }

    public static void putFloatSync(String spName, String key, float value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void putLongSync(String spName, String key, long value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void putBooleanSync(String spName, String key, boolean value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void putIntSync(String spName, String key, int value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putStringSetSync(String spName, String key, Set<String> value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }


    //    ===
    public static void putString(SharedPreferences sp, String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putDouble(SharedPreferences sp, String key, double value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, String.valueOf(value));
        ShredPreferencesCompat.apply(editor);
    }

    public static void putFloat(SharedPreferences sp, String key, float value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putLong(SharedPreferences sp, String key, long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putBoolean(SharedPreferences sp, String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putInt(SharedPreferences sp, String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putStringSet(SharedPreferences sp, String key, Set<String> value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        ShredPreferencesCompat.apply(editor);
    }


    public static void putString(String spName, String key, String value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putDouble(String spName, String key, double value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, String.valueOf(value));
        ShredPreferencesCompat.apply(editor);
    }

    public static void putFloat(String spName, String key, float value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putLong(String spName, String key, long value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putBoolean(String spName, String key, boolean value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putInt(String spName, String key, int value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static void putStringSet(String spName, String key, Set<String> value) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        ShredPreferencesCompat.apply(editor);
    }

    public static int getInt(String spName, String key, int defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static String getString(String spName, String key, String defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static float getFloat(String spName, String key, float defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static boolean getBoolean(String spName, String key, boolean defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static long getLong(String spName, String key, long defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    public static double getDouble(String spName, String key, double defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return Double.parseDouble(sp.getString(key, String.valueOf(defaultValue)));
    }

    public static double getDouble(SharedPreferences sp, String key, double defaultValue) {
        return Double.parseDouble(sp.getString(key, String.valueOf(defaultValue)));
    }

    public static Set<String> getStringSet(String spName, String key, Set<String> defaultValue) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String spName, String key) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        ShredPreferencesCompat.apply(editor);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(SharedPreferences sp, String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        ShredPreferencesCompat.apply(editor);
    }

    /**
     * 清除指定文件所有数据
     * SPUtils.getInstance(context, filename).clear();
     */
    public static void clear(String spName) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        ShredPreferencesCompat.apply(editor);
    }

    public static void clear(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        ShredPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String spName, String key) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param spName
     * @return
     */
    public static Map<String, ?> getAll(String spName) {
        SharedPreferences sp = MyApplication.getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 删除sp的xml文件
     * @param spName
     */
    public static void deleteSPXmlFile(String spName) {
        String spDir = CommonUtil.getAppInnerDataDirPath() + "/shared_prefs";
        FileUtils.deleteFile(spDir, spName+".xml");
    }


    /**
     * 创建一个兼容apply()方法的类
     */
    public static class ShredPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {

            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

}
