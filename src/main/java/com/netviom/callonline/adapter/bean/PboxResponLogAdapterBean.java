package com.netviom.callonline.adapter.bean;

/**
 * Created by tanyong on 2014/12/17.
 */
public class PboxResponLogAdapterBean {
    private String respTime;
    private String usernumber;
    private String smsContent;

    public String getRespTime() {
        return respTime;
    }

    public void setRespTime(String respTime) {
        this.respTime = respTime;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }


    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }
}
