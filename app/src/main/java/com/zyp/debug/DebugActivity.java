package com.zyp.debug;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zyp.R;
import com.zyp.app.AppConfig;
import com.zyp.app.MyApplication;
import com.zyp.constant.SharePrefsConstant;
import com.zyp.log.Logger;
import com.zyp.ui.activity.SplashActivity;
import com.zyp.util.SPUtils;

public class DebugActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "DebugActivity";
    private String[] testUrls = AppConfig.BASE_TEST_URL;
    private RecyclerView mRecyclerView;
    private DebugRecycleViewAdapter mAdapter;
    private TextView tv_base_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        final float density = getResources().getDisplayMetrics().density;

        tv_base_url = (TextView) findViewById(R.id.tv_base_url);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new SpaceItemVHDecoration((int) (7.5 * density)));
        mAdapter = new DebugRecycleViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setDatas(testUrls);

        mAdapter.setListener(this);

        initData();
    }

    private void initData() {
        final String baseUrl = AppConfig.BASE_URL;
        tv_base_url.setText("BaseUrl选择 (base_ur = " + baseUrl+")");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppConfig.IS_DEBUG) {
            String testUrl = testUrls[position];
            Logger.d(TAG,testUrl);
            AppConfig.updateAllUrl(testUrl);
            SPUtils.putString(SPUtils.DEFAULT_FILE_NAME, SharePrefsConstant.SP_BASE_URL,testUrl);
            MyApplication.finishAllActivity();
            startActivity(new Intent(this, SplashActivity.class));
        }
    }



    public static class SpaceItemVHDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemVHDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(space, space, space, space);
        }
    }
    class DebugRecycleViewAdapter extends RecyclerView.Adapter<DebugRecycleViewAdapter.DebugViewHolder> {

        private Context mContext;
        private final LayoutInflater mInflater;
        private String[] datas;
        private AdapterView.OnItemClickListener mListener;

        public void setListener(AdapterView.OnItemClickListener listener) {
            mListener = listener;
        }

        public DebugRecycleViewAdapter(Context context) {
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
        }


        @Override

        public DebugViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = mInflater.inflate(R.layout.item_debug, parent, false);
            return new DebugViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DebugViewHolder holder, final int position) {
            if (datas == null) return;
            holder.tv_base_url.setText(datas[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(null, holder.itemView, position, holder.itemView.getId());
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }

        public void setDatas(String[] datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        class DebugViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_base_url;
            public View itemView;

            public DebugViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                tv_base_url = (TextView) itemView.findViewById(R.id.tv_base_url);
            }
        }
    }
}
