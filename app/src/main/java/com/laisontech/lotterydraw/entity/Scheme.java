package com.laisontech.lotterydraw.entity;

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
 */
public class Scheme {
    private long schemeID;
    private String schemeName;
    private List<SchemeDetail> schemeDetails;

    public Scheme() {
    }

    public Scheme(long schemeID, String schemeName, List<SchemeDetail> schemeDetails) {
        this.schemeID = schemeID;
        this.schemeName = schemeName;
        this.schemeDetails = schemeDetails;
    }

    public long getSchemeID() {
        return schemeID;
    }

    public void setSchemeID(long schemeID) {
        this.schemeID = schemeID;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public List<SchemeDetail> getSchemeDetails() {
        return schemeDetails;
    }

    public void setSchemeDetails(List<SchemeDetail> schemeDetails) {
        this.schemeDetails = schemeDetails;
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "schemeID=" + schemeID +
                ", schemeName='" + schemeName + '\'' +
                ", schemeDetails=" + schemeDetails +
                '}';
    }
}
