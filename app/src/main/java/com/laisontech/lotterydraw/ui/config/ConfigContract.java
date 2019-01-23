package com.laisontech.lotterydraw.ui.config;

import android.content.Context;

import com.laisontech.lotterydraw.ui.mvp.BasePresenter;
import com.laisontech.lotterydraw.ui.mvp.BaseView;

/**
 * MVPPlugin
 */

public class ConfigContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
