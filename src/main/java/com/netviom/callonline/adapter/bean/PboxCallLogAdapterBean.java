package com.netviom.callonline.adapter.bean;

/**
 * Created by tanyong on 2014/12/11.
 */
public class PboxCallLogAdapterBean {
    public static int TOP=0;//顶部
    public static int BOTTOM=1;//底部
    public static int ALL=2;//全部背景
    public static int NOMAL=1;
    public static int TIME_ITEM=2;
    public static int TIPS_ITEM=3;
    private String usernumber;
    private String startTime;
    private int listenStatus;
    private String name;
    private int backgroundType;
    private String itemTextTime;
    private int calledTimes;
    private int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getCalledTimes() {
        return calledTimes;
    }

    public void setCalledTimes(int calledTimes) {
        this.calledTimes = calledTimes;
    }

    public String getItemTextTime() {
        return itemTextTime;
    }

    public void setItemTextTime(String itemTextTime) {
        this.itemTextTime = itemTextTime;
    }



    public String getName() {
        return name;
    }

    public int getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(int backgroundType) {
        this.backgroundType = backgroundType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public int getListenStatus() {
        return listenStatus;
    }

    public void setListenStatus(int listenStatus) {
        this.listenStatus = listenStatus;
    }
}
