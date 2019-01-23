package com.laisontech.lotterydraw.ui.main;


import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laisontech.lotterydraw.R;
import com.laisontech.lotterydraw.entity.SchemeDetail;
import com.laisontech.lotterydraw.entity.Staff;
import com.laisontech.lotterydraw.task.ShutDownTimer;
import com.laisontech.lotterydraw.ui.config.ConfigActivity;
import com.laisontech.lotterydraw.ui.mvp.MVPBaseActivity;
import com.laisontech.lotterydraw.utils.FileUtils;
import com.laisontech.lotterydraw.utils.SchemeUtils;
import com.laisontech.lotterydraw.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 抽奖软件需要实现的有数据的显示，动画的实现，以及怎么导入信息
 * 数据的移除与添加
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_list)
    TextView tvList;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_surplus)
    TextView tvSurplus;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Staff> mCacheStaffs;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String staffPath = Environment.getExternalStorageDirectory() + "/莱宸花名册.txt";
    private int totalPrizeCount;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.loadStaff(staffPath);
    }

    @Override
    public void loadStaffResult(List<Staff> staffs) {
        Log.e(TAG, "loadStaffResult: " + staffs);
        mCacheStaffs = staffs;
        //加载奖项方案,默认从数据库中获取这边只是测试
        int scheme = SpUtils.getScheme();
        if (scheme == 0) {
            List<SchemeDetail> schemeDetail = SchemeUtils.getDefaultSchemeDetail(mCacheStaffs == null ? 0 : mCacheStaffs.size());
            if (schemeDetail == null) {
                showToast("请配置奖项方案");
                openActivity(ConfigActivity.class);
            } else {
                //显示UI
                setUI(schemeDetail);
            }
        }
    }

    private void setUI(List<SchemeDetail> details) {
        viewPager.setPageMargin(30);
        viewPager.setAdapter(new PrizeAdapter(details));
        viewPager.setOffscreenPageLimit(details.size());
        totalPrizeCount = SchemeUtils.getPrizeCount(details);
        tvSurplus.setText("剩余抽奖人数：" + (totalPrizeCount) + "人");
    }

    private class PrizeAdapter extends PagerAdapter {
        List<SchemeDetail> details;

        public PrizeAdapter(List<SchemeDetail> details) {
            this.details = details;
        }

        private List<View> buildViews() {
            if (details == null) return null;
            List<View> views = new ArrayList<>();
            for (SchemeDetail detail : details) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.prize_layout, null);
                views.add(view);
            }
            return views;
        }

        @Override
        public int getCount() {
            return details == null ? 0 : details.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            final View view = buildViews().get(position);
            TextView tvType = view.findViewById(R.id.tv_prize_type);
            tvType.setText(details.get(position).getPrizeName());
            final TextView tvName = view.findViewById(R.id.tv_name);
            Button btn = view.findViewById(R.id.btn_start);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //抽奖
                    new ShutDownTimer(mCacheStaffs, new TextHandler(MainActivity.this, tvName)).start(2000);
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(buildViews().get(position));
        }

    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.tv_config, R.id.tv_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_config:
                //配置奖项 默认为三个奖项 1等奖1个 2等奖3个  3等奖5个

                break;
            case R.id.tv_list:

                break;
        }
    }

    private class TextHandler extends Handler {
        private Context context;
        private TextView tv;

        private TextHandler(Context context, TextView tv) {
            super(Looper.getMainLooper());
            this.context = context;
            this.tv = tv;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    tv.setText((((Staff) msg.obj).getName()));
                    break;
                case 1:
                    Staff lastStaff = (Staff) msg.obj;
                    tv.setText((lastStaff.getName()));
                    mCacheStaffs.remove(lastStaff);
                    totalPrizeCount--;
                    tvSurplus.setText("剩余抽奖人数：" + totalPrizeCount + "人");
                    Toast.makeText(context, "抽奖结束", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
