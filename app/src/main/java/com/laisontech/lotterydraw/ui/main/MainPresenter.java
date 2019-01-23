package com.laisontech.lotterydraw.ui.main;

import com.laisontech.lotterydraw.entity.Staff;
import com.laisontech.lotterydraw.interfaces.LoadDataListener;
import com.laisontech.lotterydraw.task.LoadStaffTask;
import com.laisontech.lotterydraw.ui.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    //读取文件并且获取员工信息
    // 格式    姓名|工号;姓名|工号;姓名|工号
    @Override
    public void loadStaff(String path) {
        new LoadStaffTask(new LoadDataListener<List<Staff>>() {
            @Override
            public void doing() {
                mView.loading();
            }

            @Override
            public void done(List<Staff> staffs) {
                mView.done();
                mView.loadStaffResult(staffs);
            }
        }).execute(path);
    }
}
