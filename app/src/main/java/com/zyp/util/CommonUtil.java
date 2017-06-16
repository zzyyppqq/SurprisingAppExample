package com.zyp.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import com.zyp.app.MyApplication;

import java.io.File;

/**
 * Created by zhangyipeng on 2017/6/15.
 */

public class CommonUtil {
    /**
     * 获取当前进程名称
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return "";
    }


    /**
     * Toast提示
     */
    public static void toastMsg(String message) {
        Toast.makeText(MyApplication.getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * unicode转gbk
     * @param dataStr
     * @return
     */
    public static String unicode2GBK(String dataStr) {
        int index = 0;
        StringBuilder builder = new StringBuilder();

        int li_len = dataStr.length();
        while (index < li_len) {
            if (index >= li_len - 1||index + 6 > li_len
                    || !"\\u".equals(dataStr.substring(index, index + 2))) {
                builder.append(dataStr.charAt(index));

                index++;
                continue;
            }

            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);

            char letter = (char) Integer.parseInt(charStr, 16);

            builder.append(letter);
            index += 6;
        }

        return builder.toString();
    }


    public static String getAppInnerDataDirPath(){
        try {
            File cachefile = MyApplication.getApplication().getCacheDir();
            String cachePath = cachefile.getAbsolutePath();
            return cachePath.substring(0, cachePath.lastIndexOf("/"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 导出应用私有数据到 /sdcard/包名/ 目录下
     *
     */
    public static void copyDBAndSP2Sdcard() {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String[] params) {
                try {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" + MyApplication.getApplication().getPackageName();
                    String sourceFile = CommonUtil.getAppInnerDataDirPath() + "/databases";
                    String targetFile =  path + "/databases";
                    FileUtils.copyFile2Folder(sourceFile, targetFile);
                    String sourceFile2 = CommonUtil.getAppInnerDataDirPath() + "/shared_prefs";
                    String targetFile2 = path + "/shared_prefs/";
                    FileUtils.copyFile2Folder(sourceFile2, targetFile2);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean b) {
                super.onPostExecute(b);
                if (b) {
                    CommonUtil.toastMsg("databases与shared_prefs数据导出成功");
                }
            }
        }.execute();
    }
}
