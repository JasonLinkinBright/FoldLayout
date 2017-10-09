package com.jason.foldlayout.bean;

/**
 * ClassName:PersonalInfoBean
 * Description:
 * Created by Jason on 17/6/20.
 */

public class PersonalInfoBean {
    /**
     * 我的知识币数量
     */
    private int goldCount;
    /**
     * 我的知识币消息数
     */
    private int goldMsgCount;
    /**
     * 我的勋章数量
     */
    private int medalCount;
    /**
     * 我的勋章消息数
     */
    private int medalMsgCount;
    /**
     * 我的粉丝消息数
     */
    private int fansMsgCount;
    /**
     * 我的草稿数量
     */
    private int draftCount;

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public int getGoldMsgCount() {
        return goldMsgCount;
    }

    public void setGoldMsgCount(int goldMsgCount) {
        this.goldMsgCount = goldMsgCount;
    }

    public int getMedalCount() {
        return medalCount;
    }

    public void setMedalCount(int medalCount) {
        this.medalCount = medalCount;
    }

    public int getMedalMsgCount() {
        return medalMsgCount;
    }

    public void setMedalMsgCount(int medalMsgCount) {
        this.medalMsgCount = medalMsgCount;
    }

    public int getFansMsgCount() {
        return fansMsgCount;
    }

    public void setFansMsgCount(int fansMsgCount) {
        this.fansMsgCount = fansMsgCount;
    }

    public int getDraftCount() {
        return draftCount;
    }

    public void setDraftCount(int draftCount) {
        this.draftCount = draftCount;
    }

}
