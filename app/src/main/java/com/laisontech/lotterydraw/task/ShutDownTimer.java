package com.laisontech.lotterydraw.task;

import android.os.Handler;

import com.laisontech.lotterydraw.entity.Staff;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

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
public class ShutDownTimer {
    private long times = 2000;
    private long startTimes;
    private List<Staff> staffList;
    private int totalSize;
    private Handler handler;
    private Random random = new Random();

    public ShutDownTimer(List<Staff> staffList, Handler handler) {
        this.staffList = staffList;
        this.handler = handler;
        totalSize = staffList == null ? 0 : staffList.size();
    }

    //在这个时间段内随机显示字符串，然后最后一秒结束时，显示最后一个。一个名字显示50ms
    public synchronized void start(final long times) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int randomIndex = 0;
                while (startTimes < times) {
                    try {
                        randomIndex = random.nextInt(totalSize);
                        handler.sendMessage(handler.obtainMessage(0, staffList.get(randomIndex)));
                        Thread.sleep(50);
                        startTimes += 50;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendMessage(handler.obtainMessage(1, staffList.get(randomIndex)));
            }
        }).start();
    }
}
