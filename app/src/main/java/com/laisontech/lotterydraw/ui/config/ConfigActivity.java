package com.laisontech.lotterydraw.ui.config;


import com.laisontech.lotterydraw.R;
import com.laisontech.lotterydraw.ui.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 */

public class ConfigActivity extends MVPBaseActivity<ConfigContract.View, ConfigPresenter> implements ConfigContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_config;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
