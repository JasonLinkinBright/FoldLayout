package com.jason.foldlayout.bean;

/**
 * ClassName:UserInfoBean
 * Description:
 * Created by Jason on 17/6/20.
 */

public class UserInfoBean {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String name;

    /**
     * 用户身份
     */
    private String identity;

    /**
     * 职称
     */
    private String positionStr;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 点赞数量
     */
    private int approvalCount;

    /**
     * 粉丝数量
     */
    private int fansCount;

    /**
     * 关注数量
     */
    private int focusCount;

    /**
     * 他的问题数
     */
    private int questionCount;

    /**
     * 他的回答数
     */
    private int answerCount;

    /**
     * 他的分享数
     */
    private int shareCount;

    /**
     * 他关注的话题数
     */
    private int focusTopicCount;

    /**
     * 他关注的问题数
     */
    private int focusQuestionCount;

    /**
     * 关注状态 0 未关注 1 已关注 2 互相关注
     */
    private String focusType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(int approvalCount) {
        this.approvalCount = approvalCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getFocusTopicCount() {
        return focusTopicCount;
    }

    public void setFocusTopicCount(int focusTopicCount) {
        this.focusTopicCount = focusTopicCount;
    }

    public int getFocusQuestionCount() {
        return focusQuestionCount;
    }

    public void setFocusQuestionCount(int focusQuestionCount) {
        this.focusQuestionCount = focusQuestionCount;
    }

    public String getFocusType() {
        return focusType;
    }

    public void setFocusType(String focusType) {
        this.focusType = focusType;
    }
}
