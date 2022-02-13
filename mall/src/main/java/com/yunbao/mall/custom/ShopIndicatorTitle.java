package com.yunbao.mall.custom;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class ShopIndicatorTitle extends LinearLayout implements IMeasurablePagerTitleView {

    private SimplePagerTitleView mTitleView;

    public ShopIndicatorTitle(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    public void setTitleView(SimplePagerTitleView titleView) {
        mTitleView = titleView;
    }

    @Override
    public int getContentLeft() {
        return mTitleView.getContentLeft() + getLeft();
    }

    @Override
    public int getContentTop() {
        return mTitleView.getContentTop();
    }

    @Override
    public int getContentRight() {
        return mTitleView.getContentRight() + getLeft();
    }

    @Override
    public int getContentBottom() {
        return mTitleView.getContentBottom();
    }

    @Override
    public void onSelected(int index, int totalCount) {
        mTitleView.onSelected(index, totalCount);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        mTitleView.onDeselected(index, totalCount);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        mTitleView.onLeave(index, totalCount, leavePercent, leftToRight);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        mTitleView.onEnter(index, totalCount, enterPercent, leftToRight);
    }

    public SimplePagerTitleView getTitleView() {
        return mTitleView;
    }
}
