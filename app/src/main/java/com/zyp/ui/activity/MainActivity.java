package com.zyp.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zyp.BuildConfig;
import com.zyp.R;
import com.zyp.app.AppConfig;
import com.zyp.app.MyApplication;
import com.zyp.constant.SharePrefsConstant;
import com.zyp.debug.DebugActivity;
import com.zyp.log.Logger;
import com.zyp.util.CommonUtil;
import com.zyp.util.FileUtils;
import com.zyp.util.SPUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView mTextMessage;
    private TextView tv_buile_info;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };
    private Button btn_switch_debug_url;
    private Button btn_switch_deafult_url;
    private Button btn_export_data;
    private TextView tv_base_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        tv_base_url = (TextView) findViewById(R.id.tv_base_url);
        tv_buile_info = (TextView) findViewById(R.id.tv_buile_info);
        btn_switch_debug_url = (Button) findViewById(R.id.btn_switch_debug_url);
        btn_switch_deafult_url = (Button) findViewById(R.id.btn_switch_deafult_url);
        btn_export_data = (Button) findViewById(R.id.btn_export_data);
        btn_switch_debug_url.setOnClickListener(this);
        btn_switch_deafult_url.setOnClickListener(this);
        btn_export_data.setOnClickListener(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initData();
    }

    private void initData() {
        tv_buile_info.setText("build_time : "+ BuildConfig.BUILD_TIME+"\r\nbuild_host : "+BuildConfig.BUILD_HOST+"\r\nbuild_revision : "+BuildConfig.LASR_GIT_COMMIT_SHA);

        final String baseUrl = AppConfig.BASE_URL;
        tv_base_url.setText("当前应用base_ur = " + baseUrl);
        if (AppConfig.IS_DEBUG) {
            final String base_url = SPUtils.getString(SPUtils.DEFAULT_FILE_NAME, SharePrefsConstant.SP_BASE_URL, "");
            if (TextUtils.isEmpty(base_url)) return;
            CommonUtil.toastMsg("baseUrl = " + baseUrl);
        }
        Logger.d(TAG, baseUrl);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch_debug_url:
                if (AppConfig.IS_DEBUG) {
                    startActivity(new Intent(this, DebugActivity.class));
                }
                break;
            case R.id.btn_switch_deafult_url:
                if (AppConfig.IS_DEBUG) {
                    SPUtils.putString(SPUtils.DEFAULT_FILE_NAME, SharePrefsConstant.SP_BASE_URL, "");
                    //SPUtils.deleteSPXmlFile(SPUtils.DEFAULT_FILE_NAME);
                    AppConfig.updateAllUrl(AppConfig.DEFAULT_BASE_URL);
                    MyApplication.finishAllActivity();
                    startActivity(new Intent(this, SplashActivity.class));
                }
                break;
            case R.id.btn_export_data:
                if (AppConfig.IS_DEBUG) {
                    CommonUtil.copyDBAndSP2Sdcard();
                }
                break;
        }
    }
}
