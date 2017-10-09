package com.jason.foldlayout.behavior;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jason.foldlayout.BadgeUtils;
import com.jason.foldlayout.MedalBean;
import com.jason.foldlayout.MultipleTypeItem;
import com.jason.foldlayout.R;
import com.jason.foldlayout.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.badgeview.BGABadgeTextView;

/**
 * ClassName:MineContentAdapter
 * Description:
 * Created by Jason on 17/6/20.
 */

public class MineContentAdapter extends BaseMultiItemQuickAdapter<MultipleTypeItem> implements IsChildRequestScrollListener, SupportNeedExpandListener {
    RecyclerView mRecyclerView;
    String userId;
    Map<String, String> msgMap = new HashMap<>();
    List<MedalBean> medals = new ArrayList<>();
    /**
     * 显示带数字和背景的红点
     */
    List<String> badgeWithNumberList = new ArrayList<String>() {{
        add("myMsgCount");
        add("questionMsgCount");
        add("answerMsgCount");
        add("shareMsgCount");
    }};
    /**
     * 显示不带数字和背景的红点
     */
    List<String> badgeWithoutNumberAndBgList = new ArrayList<String>() {{
        add("courseMsgCount");
        add("testMsgCount");
        add("questionnaireMsgCount");
        add("classMsgCount");
    }};
    /**
     * 显示带数字不带背景的红点
     */
    List<String> badgeWithoutBgList = new ArrayList<String>() {{
        add("draftCount");
        add("focusQuestionCount");
        add("questionCount");
        add("answerCount");
        add("shareCount");
    }};

    public MineContentAdapter(List<MultipleTypeItem> data, RecyclerView recyclerView) {
        super(data);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(onScrollListener);
        addItemType(MultipleTypeItem.ITEM_TYPE_NORMAL, R.layout.layout_rv_normal_item);
        addItemType(MultipleTypeItem.ITEM_TYPE_DIVIDER_BOLD, R.layout.layout_rv_divider_bold);
        addItemType(MultipleTypeItem.ITEM_TYPE_DIVIDER_NORMAL, R.layout.layout_rv_divider_normal);
//        addItemType(MultipleTypeItem.ITEM_TYPE_MEDAL, R.layout.layout_rv_medal);
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0
                    && mNeedExpendListener != null) {
                mNeedExpendListener.needExpand();
            }
        }
    };

    @Override
    protected void convert(BaseViewHolder holder, final MultipleTypeItem item) {
        switch (item.getItemType()) {
            case MultipleTypeItem.ITEM_TYPE_NORMAL:
                BGABadgeTextView badgeTextView = holder.getView(R.id.bga_item);
                BadgeUtils.hideBadge(badgeTextView);
                int resId = mContext.getResources().getIdentifier(item.getIcName(), "drawable", mContext.getPackageName());
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resId);
                int width = bitmap.getWidth();
                bitmap.recycle();
                int drawablePadding = UiUtils.sp2px(mContext, 35);
                int pictureWidth = 45; // 第一个图标的宽度为45px
                if (width > pictureWidth) {
                    drawablePadding -= (width - pictureWidth);
                } else if (width < pictureWidth) {
                    drawablePadding += (pictureWidth - width);
                }
                badgeTextView.setCompoundDrawablePadding(drawablePadding);
                badgeTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, R.drawable.icon_next, 0);
                badgeTextView.setText(item.getItemText());

                String keyName = item.getKeyName();
                String badgeNumber = msgMap.get(keyName);
                if (badgeNumber == null) {
                    badgeNumber = "0";
                }
                int num = Integer.parseInt(badgeNumber);
                if (num == 0) {
                    BadgeUtils.hideBadge(badgeTextView);
                } else if (badgeWithNumberList.contains(keyName)) {
                    BadgeUtils.showCenterBadgeView(badgeTextView, badgeNumber);
                    String itemUrl = item.getUrl();
//                    if (TextUtils.equals(keyName, "questionMsgCount")) {
//                        itemUrl = String.format(mContext.getString(R.string.url_my_notice), "type=question");
//                    } else if (TextUtils.equals(keyName, "answerMsgCount")) {
//                        itemUrl = String.format(mContext.getString(R.string.url_my_notice), "type=answer");
//                    } else if (TextUtils.equals(keyName, "shareMsgCount")) {
//                        itemUrl = String.format(mContext.getString(R.string.url_my_notice), "type=share");
//                    }
                    item.setUrl(itemUrl);
                } else if (badgeWithoutNumberAndBgList.contains(keyName)) {
                    BadgeUtils.showCircleBadgeView(badgeTextView);
                } else if (badgeWithoutBgList.contains(keyName)) {
                    BadgeUtils.showOtherUserBadgeView(badgeTextView, badgeNumber);
                } else {
                    BadgeUtils.hideBadge(badgeTextView);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        NextStepBean nextStepBean = new NextStepBean();
//                        nextStepBean.setUrl(item.getUrl());
//                        Navigator.INSTANCE.startNavigate(nextStepBean);
                    }
                });

                if (TextUtils.isEmpty(userId)) {
                    return;
                }
                if (item.getUrl().contains("?")) {
                    item.setUrl(item.getUrl() + "&userId=" + userId);
                } else {
                    item.setUrl(item.getUrl() + "?userId=" + userId);
                }
                break;
            case MultipleTypeItem.ITEM_TYPE_MEDAL:
//                RecyclerView rvMedal= holder.getView(R.id.rv_medal);
//                rvMedal.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL, false));
//                MedalAdapter medalAdapter = new MedalAdapter(R.layout.layout_medal_item, medals);
//                rvMedal.setAdapter(medalAdapter);
                break;
        }
    }

    @Override
    public boolean requestScroll(boolean up) {
        return (up && ViewCompat.canScrollVertically(mRecyclerView, 1)) ||
                (!up && (ViewCompat.canScrollVertically(mRecyclerView, -1) ||
                        !(((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0)));
    }

    private NeedExpandListener mNeedExpendListener;

    @Override
    public void setNeedExpendListener(NeedExpandListener listener) {
        mNeedExpendListener = listener;
    }

    @Override
    public NeedExpandListener getNeedExpendListener() {
        return mNeedExpendListener;
    }

    /**
     * 更新数据
     *
     * @param dataMap
     */
    public void updateData(Map<String, String> dataMap) {
        msgMap.putAll(dataMap);
        notifyDataSetChanged();
    }

    public void updateUrl(String userId) {
        this.userId = userId;
        notifyDataSetChanged();
    }

    public void updateMedalData(List<MedalBean> medalBeanList) {
        if (medalBeanList == null) {
            return;
        }
        this.medals = medalBeanList;
        notifyItemChanged(0);
    }
}

