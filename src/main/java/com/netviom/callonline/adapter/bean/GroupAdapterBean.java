package com.netviom.callonline.adapter.bean;

import com.nvm.common.response.vo.ContactsResp;

import java.util.List;

/**
 * Created by tanyong on 2014/12/12.
 */
public class GroupAdapterBean {
    private String name;
    private boolean group;
    private boolean expand;
    private String phoneNumber="";
    private String id;
    private boolean isTips=false;
    private List<ContactsResp> childList;

    public List<ContactsResp> getChildList() {
        return childList;
    }

    public void setChildList(List<ContactsResp> childList) {
        this.childList = childList;
    }

    public boolean isTips() {
        return isTips;
    }

    public void setTips(boolean isTips) {
        this.isTips = isTips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }
}
