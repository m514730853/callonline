package com.netviom.callonline.adapter.bean;

import com.netviom.callonline.listener.DoAction;

/**
 * Created by tanyong on 2014/12/12.
 */
public class ConfigAdapterBean {
    private String name;
    private DoAction doAction;
    private int imgDrawableLeft;

    public int getImgDrawableLeft() {
        return imgDrawableLeft;
    }

    public void setImgDrawableLeft(int imgDrawableLeft) {
        this.imgDrawableLeft = imgDrawableLeft;
    }

    public DoAction getDoAction() {
        return doAction;
    }

    public void setDoAction(DoAction doAction) {
        this.doAction = doAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
