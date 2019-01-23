package com.laisontech.lotterydraw.task;

import android.os.AsyncTask;

import com.laisontech.lotterydraw.entity.Staff;
import com.laisontech.lotterydraw.interfaces.LoadDataListener;
import com.laisontech.lotterydraw.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ..................................................................
 * .         The Buddha said: I guarantee you have no bug!          .
 * .                                                                .
 * .                            _ooOoo_                             .
 * .                           o8888888o                            .
 * .                           88" . "88                            .
 * .                           (| -_- |)                            .
 * .                            O\ = /O                             .
 * .                        ____/`---'\____                         .
 * .                      .   ' \\| |// `.                          .
 * .                       / \\||| : |||// \                        .
 * .                     / _||||| -:- |||||- \                      .
 * .                       | | \\\ - /// | |                        .
 * .                     | \_| ''\---/'' | |                        .
 * .                      \ .-\__ `-` ___/-. /                      .
 * .                   ___`. .' /--.--\ `. . __                     .
 * .                ."" '< `.___\_<|>_/___.' >'"".                  .
 * .               | | : `- \`.;`\ _ /`;.`/ - ` : | |               .
 * .                 \ \ `-. \_ __\ /__ _/ .-` / /                  .
 * .         ======`-.____`-.___\_____/___.-`____.-'======          .
 * .                            `=---='                             .
 * ..................................................................
 * Created by SDP on 2019/1/23.
 * 从文件中获取用户列表
 */
public class LoadStaffTask extends AsyncTask<String, Void, List<Staff>> {
    private LoadDataListener<List<Staff>> listener;

    public LoadStaffTask(LoadDataListener<List<Staff>> listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        if (listener != null) {
            listener.doing();
        }
    }

    @Override
    protected List<Staff> doInBackground(String... strings) {
        if (strings == null || strings.length != 1) return null;
        try {
            String staffPath = strings[0];//获取花名册的路径
            byte[] bytes = FileUtils.readBytesFromFile(staffPath);
            if (bytes == null) return null;
            String staffStr = new String(bytes,"utf-8");
            //拆分
            staffStr = staffStr.replace("\n","");
            staffStr = staffStr.replace("\r","");
            staffStr = staffStr.replace(" ","");
            List<Staff> staffList = new ArrayList<>();
            if (staffStr.contains(";")) {
                String[] split1 = staffStr.split(";");
                for (String sta : split1) {
                    getStaff(staffList, sta);
                }
            } else {//没有包含；则就是一组
                getStaff(staffList, staffStr);
            }
            return staffList;
        } catch (Exception e) {
            return null;
        }
    }

    private void getStaff(List<Staff> staffList, String sta) {
        if (sta.contains("&")) {
            String[] split = sta.split("&");
            if (split.length == 2) {
                Staff staff = new Staff(split[0], split[1]);
                staffList.add(staff);
            }
        }
    }

    @Override
    protected void onPostExecute(List<Staff> staffs) {
        if (listener != null) {
            listener.done(staffs);
        }
    }
}
