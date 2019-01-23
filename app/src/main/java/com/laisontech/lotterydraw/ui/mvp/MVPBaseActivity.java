package com.laisontech.lotterydraw.ui.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laisontech.lotterydraw.App;
import com.laisontech.lotterydraw.R;
import com.laisontech.lotterydraw.weight.CustomCircleDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView {
    public T mPresenter;
    private CustomCircleDialog mDialogWaiting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initData();

    protected abstract void initEvent();

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void showError(int msgId) {
        showToast(msgId);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void loading() {
        showWaitingDialog("加载中...");
    }

    @Override
    public void done() {
        hideWaitingDialog();
    }

    protected void showToast(Object obj) {
        String msg = "";
        if (obj instanceof Integer) {
            msg = getStringRes((Integer) obj);
        } else if (obj instanceof String) {
            msg = (String) obj;
        }
        if (msg.isEmpty()) return;
        Toast.makeText(App.localContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected String getStringRes(@StringRes int resId) {
        return getResources().getString(resId);
    }

    //不能进行取消的dialog框
    protected void showWaitingDialog(String tip) {
        showWaitingDialog(tip, false);
    }

    //不能进行取消的dialog框
    protected void showWaitingDialog(int resId) {
        showWaitingDialog(getStringRes(resId), false);
    }

    /**
     * 显示等待提示框
     */
    protected void showWaitingDialog(String tip, boolean needCancelable) {
        View view = View.inflate(this, R.layout.include_dialog_waiting, null);
        if (mDialogWaiting == null) {
            mDialogWaiting = new CustomCircleDialog(this, R.style.CustomCircleDialog);
        }
        if (!TextUtils.isEmpty(tip)) {
            ((TextView) view.findViewById(R.id.tv_load_title)).setText(tip);
        }
        mDialogWaiting.setView(view);
        mDialogWaiting.setCanceledOnTouchOutside(false);
        mDialogWaiting.setCancelable(needCancelable);
        if (mDialogWaiting != null && !mDialogWaiting.isShowing()) {
            mDialogWaiting.show();
        }
    }

    /**
     * 隐藏等待提示框
     */
    protected void hideWaitingDialog() {
        if (mDialogWaiting != null && mDialogWaiting.isShowing()) {
            mDialogWaiting.dismiss();
        }
    }

    protected void openActivity(Class<?> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
}
