package com.laisontech.lotterydraw.utils;

import com.laisontech.lotterydraw.entity.Scheme;
import com.laisontech.lotterydraw.entity.SchemeDetail;

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
 */
public class SchemeUtils {
    //员工数量限定最少9个才可以 default的id是0
    public static List<SchemeDetail> getDefaultSchemeDetail(int staffCount) {
        if (staffCount < 9) return null;
        List<SchemeDetail> schemeDetails = new ArrayList<>();
        SchemeDetail schemeDetail1 = new SchemeDetail(0, 1, "一等奖", 1);
        SchemeDetail schemeDetail2 = new SchemeDetail(0, 2, "二等奖", 3);
        SchemeDetail schemeDetail3 = new SchemeDetail(0, 3, "三等奖", 5);
        schemeDetails.add(schemeDetail1);
        schemeDetails.add(schemeDetail2);
        schemeDetails.add(schemeDetail3);
        return schemeDetails;
    }

    public static int getPrizeCount(List<SchemeDetail> details) {
        if (details == null || details.size() < 1) return 0;
        int count = 0;
        for (SchemeDetail detail : details) {
            count += detail.getPrizeCount();
        }
        return count;
    }
}
