package com.jason.foldlayout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.reflect.TypeToken;
import com.jason.foldlayout.bean.PersonalInfoBean;
import com.jason.foldlayout.bean.UserInfoBean;
import com.jason.foldlayout.behavior.MineContentAdapter;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.badgeview.BGABadgeTextView;

public class MainActivity extends AppCompatActivity {
    /**
     * 头像
     */
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    /**
     * 用户名
     */
    @BindView(R.id.tvUserName)
    TextView mTvUserName;
    /**
     * 职称
     */
    @BindView(R.id.tvPost)
    TextView mTvPost;
    /**
     * 用户简介
     */
    @BindView(R.id.tv_introduction)
    TextView mTvIntroduction;
    /**
     * 我的页面背景图
     */
    @BindView(R.id.backdrop)
    ImageView mBackdrop;
    /**
     * 用户身份
     */
    @BindView(R.id.iv_identity)
    ImageView ivIdentity;
    /**
     * 用户等级
     */
    @BindView(R.id.tv_user_level)
    TextView mTvUserLevel;

    /**
     * 页面内容
     */
    @BindView(R.id.rv_content)
    RecyclerView mContentRv;
    /**
     * 用来显示点赞数量
     */
    @BindView(R.id.tvPraiseCount)
    BGABadgeTextView tvPraiseCount;
    /**
     * 点击点赞的区域
     */
    @BindView(R.id.ll_praise)
    LinearLayout llPraise;
    /**
     * 用来显示粉丝数量
     */
    @BindView(R.id.tvFansCount)
    BGABadgeTextView tvFansCount;
    /**
     * 点击粉丝的区域
     */
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    /**
     * 用来显示关注的数量
     */
    @BindView(R.id.tvAttentionCount)
    BGABadgeTextView tvAttentionCount;
    /**
     * 点击关注的区域
     */
    @BindView(R.id.ll_focus)
    LinearLayout llFocus;
    /**
     * 点击我的勋章的区域
     */
    @BindView(R.id.tv_medal)
    TextView tvMedal;
    /**
     * 显示我的勋章数量
     */
    @BindView(R.id.tv_medal_count)
    BGABadgeTextView tvMedalCount;
    /**
     * 点击我的知识币的区域
     */
    @BindView(R.id.tv_coin)
    TextView tvCoin;
    /**
     * 显示我的知识币数量
     */
    @BindView(R.id.tv_coin_count)
    BGABadgeTextView tvCoinCount;
    Unbinder unbinder;
    @BindView(R.id.tv_focus_each_other)
    TextView tvFocusEachOther;
    @BindView(R.id.ll_reward)
    LinearLayout llReward;
    /**
     * 用户Id
     */
    private String userId = null;
    List<MultipleTypeItem> multipleTypeItems;
    MineContentAdapter mineContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        // 1、列表的数据由assets的mine.json配出来
        String contentJson = "mine.json";
        // 2、根据标志加载json文件
        String mineContentStr = FileUtils.readFileFromAssetsFile(this, contentJson);
        multipleTypeItems = GsonUtils.parseJSON(mineContentStr, new TypeToken<List<MultipleTypeItem>>() {
        }.getType());
        // 3、初始化相关View
        mContentRv.setLayoutManager(new LinearLayoutManager(this));
        mineContentAdapter = new MineContentAdapter(multipleTypeItems, mContentRv);
        mContentRv.setAdapter(mineContentAdapter);
        // 4.模拟加载数据
        getUserInfo();

    }

    private void getUserInfo() {
        PersonalInfoBean personalInfoBean = new PersonalInfoBean();
        personalInfoBean.setDraftCount(11);
        personalInfoBean.setFansMsgCount(9);
        personalInfoBean.setGoldCount(80);
        personalInfoBean.setGoldMsgCount(3);
        personalInfoBean.setMedalCount(5);
        personalInfoBean.setDraftCount(2);
        Map<String, String> resultMap = GsonUtils.object2Map(personalInfoBean);
        mineContentAdapter.updateData(resultMap);

        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setId("1200000023940239");
        userInfoBean.setAvatar("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg");
        userInfoBean.setName("林林林");
        userInfoBean.setPositionStr("CEO");
        userInfoBean.setAnswerCount(4);
        userInfoBean.setApprovalCount(5);
        userInfoBean.setFansCount(11);
        userInfoBean.setFocusTopicCount(19);
        userInfoBean.setFocusQuestionCount(8);
        userInfoBean.setFocusCount(3);
        userInfoBean.setFocusType("2");
        userInfoBean.setIntroduction("撸猫上瘾。爱好和平。");
        userInfoBean.setQuestionCount(2);
        userInfoBean.setShareCount(8);
        userInfoBean.setIdentity("普通用户");
        showUserInfo(userInfoBean);
    }


    /**
     * 显示用户信息
     */
    private void showUserInfo(UserInfoBean data) {
        if (data == null) { // 如果用户信息为null，不执行
            return;
        }
        if (!TextUtils.isEmpty(data.getAvatar())) { // 头像
            Glide.with(this)
                    .load(data.getAvatar())
                    .asBitmap()
                    .into(mIvAvatar);
        }
        if (data.getName() != null) { // 昵称
            mTvUserName.setText(data.getName());
        }
        if (data.getPositionStr() != null) { // 职称
            mTvPost.setText(data.getPositionStr());
        }
        if (!TextUtils.isEmpty(data.getIntroduction())) { // 个人简介
            mTvIntroduction.setText(data.getIntroduction());
//            tvIntroductionInfo.setVisibility(View.VISIBLE);
        } else {
            mTvIntroduction.setText("");
//            tvIntroductionInfo.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getIdentity())) {
            if (TextUtils.equals(data.getIdentity(), "普通用户")) {
                mTvUserLevel.setText("如何成为专家？");
                mTvUserLevel.setTextSize(12);
                mTvUserLevel.setBackground(new ColorDrawable(Color.TRANSPARENT));
                ivIdentity.setVisibility(View.GONE);
            } else {
                mTvUserLevel.setText(data.getIdentity());
                mTvUserLevel.setTextSize(16);
                mTvUserLevel.setBackground(getResources().getDrawable(R.drawable.shape_level));
                ivIdentity.setVisibility(View.VISIBLE);
            }
        }
        if (data.getApprovalCount() >= 0) {
            tvPraiseCount.setText(String.valueOf(data.getApprovalCount())); // 点赞数量
        }
        if (data.getFansCount() >= 0) {
            tvFansCount.setText(String.valueOf(data.getFansCount())); // 粉丝数量
        }
        if (data.getFocusCount() >= 0) {
            tvAttentionCount.setText(String.valueOf(data.getFocusCount())); // 关注数量
        }

        if (!TextUtils.isEmpty(userId)) {
//            changeFocusStatus(data.getFocusType());

            mIvAvatar.setClickable(false);
            mTvUserLevel.setClickable(false);
        } else {
        }
        Map<String, String> resultMap = GsonUtils.object2Map(data);
        mineContentAdapter.updateData(resultMap);
    }


}
