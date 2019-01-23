package com.laisontech.lotterydraw.entity;

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
public class SchemeDetail {
    private long schemeID;
    private long prizeID;
    private String prizeName;
    private int prizeCount;

    public SchemeDetail(long schemeID) {
        this.schemeID = schemeID;
    }

    public SchemeDetail(long schemeID, long prizeID, String prizeName, int prizeCount) {
        this.schemeID = schemeID;
        this.prizeID = prizeID;
        this.prizeName = prizeName;
        this.prizeCount = prizeCount;
    }

    public long getSchemeID() {
        return schemeID;
    }

    public void setSchemeID(long schemeID) {
        this.schemeID = schemeID;
    }

    public long getPrizeID() {
        return prizeID;
    }

    public void setPrizeID(long prizeID) {
        this.prizeID = prizeID;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(int prizeCount) {
        this.prizeCount = prizeCount;
    }

    @Override
    public String toString() {
        return "SchemeDetail{" +
                "schemeID=" + schemeID +
                ", prizeID=" + prizeID +
                ", prizeName='" + prizeName + '\'' +
                ", prizeCount=" + prizeCount +
                '}';
    }
}
