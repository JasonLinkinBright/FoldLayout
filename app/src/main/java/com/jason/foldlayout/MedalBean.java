package com.jason.foldlayout;

import java.io.Serializable;

/**
 * ClassName:MedalBean
 * Description: 我的勋章类
 * Created by Jason on 17/6/20.
 */

public class MedalBean implements Serializable {
    /**
     * 勋章id
     */
    private String id;
    /**
     * 勋章图标地址
     */
    private String icon;
    /**
     * 勋章描述
     */
    private String name;
    /**
     * 勋章类型
     */
    private String type;
    /**
     * 添加时间
     */
    private long addTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}
