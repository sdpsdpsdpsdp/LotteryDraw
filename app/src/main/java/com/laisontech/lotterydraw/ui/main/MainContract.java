package com.laisontech.lotterydraw.ui.main;

import com.laisontech.lotterydraw.entity.Staff;
import com.laisontech.lotterydraw.ui.mvp.BasePresenter;
import com.laisontech.lotterydraw.ui.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 */

public class MainContract {
    interface View extends BaseView {
        void loadStaffResult(List<Staff> staffs);
    }

    interface Presenter extends BasePresenter<View> {

        void loadStaff(String path);
    }
}
