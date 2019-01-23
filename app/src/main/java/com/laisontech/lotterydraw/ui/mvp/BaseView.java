package com.laisontech.lotterydraw.ui.mvp;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public interface BaseView {
    Context getContext();

    void showError(String msg);

    void showError(@StringRes int msgId);

    void loading();

    void done();
}
