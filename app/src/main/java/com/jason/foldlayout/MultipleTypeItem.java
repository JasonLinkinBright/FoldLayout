package com.jason.foldlayout;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * ClassName:MultiTypeItem
 * Description:
 * Created by Jason on 17/6/20.
 */

public class MultipleTypeItem implements MultiItemEntity, Serializable {
    public static final int ITEM_TYPE_NORMAL = 1;
    public static final int ITEM_TYPE_DIVIDER_NORMAL = 2;
    public static final int ITEM_TYPE_DIVIDER_BOLD = 3;
    public static final int ITEM_TYPE_MEDAL = 4;
    private String icName;
    private String itemText;
    private int itemType;
    private String url;
    private String keyName;

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getIcName() {
        return icName;
    }

    public void setIcName(String icName) {
        this.icName = icName;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}

