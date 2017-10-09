package com.jason.foldlayout.behavior;

/**
 * ClassName:SupportNeedExpandListener
 * Description: 是否支持NeedExpandListener
 * Created by Jason on 17/6/20.
 */

public interface SupportNeedExpandListener {
    void setNeedExpendListener(NeedExpandListener listener);

    NeedExpandListener getNeedExpendListener();
}
