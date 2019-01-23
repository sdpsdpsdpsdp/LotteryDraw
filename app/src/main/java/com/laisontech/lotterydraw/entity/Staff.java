package com.laisontech.lotterydraw.entity;

import android.os.Parcel;
import android.os.Parcelable;

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
 */
public class Staff implements Parcelable {
    private String name;
    private String JobNo;

    public Staff() {
    }

    public Staff(String name, String jobNo) {
        this.name = name;
        JobNo = jobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNo() {
        return JobNo;
    }

    public void setJobNo(String jobNo) {
        JobNo = jobNo;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", JobNo='" + JobNo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.JobNo);
    }

    protected Staff(Parcel in) {
        this.name = in.readString();
        this.JobNo = in.readString();
    }

    public static final Parcelable.Creator<Staff> CREATOR = new Parcelable.Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel source) {
            return new Staff(source);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };
}
