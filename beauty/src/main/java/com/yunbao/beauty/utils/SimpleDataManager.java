package com.yunbao.beauty.utils;

import com.alibaba.fastjson.JSONObject;
import com.yunbao.beauty.interfaces.IBeautyEffectListener;
import com.yunbao.common.http.CommonHttpUtil;

public class SimpleDataManager {

    private int mMeiBai;
    private int mMoPi;
    private int mHongRun;
    private IBeautyEffectListener mMeiYanChangedListener;

    private static SimpleDataManager sInstance;

    private SimpleDataManager() {

    }

    public static SimpleDataManager getInstance() {
        if (sInstance == null) {
            synchronized (MhDataManager.class) {
                if (sInstance == null) {
                    sInstance = new SimpleDataManager();
                }
            }
        }
        return sInstance;
    }

    public SimpleDataManager create() {
        mMeiBai = 0;
        mMoPi = 0;
        mHongRun = 0;
        return this;
    }

    public void release() {
        mMeiBai = 0;
        mMoPi = 0;
        mHongRun = 0;
        mMeiYanChangedListener = null;
    }

    public void setMeiYanChangedListener(IBeautyEffectListener meiYanChangedListener) {
        mMeiYanChangedListener = meiYanChangedListener;
    }

    public void setData(int meiBai, int moPi, int hongRun) {
        mMeiBai = meiBai;
        mMoPi = moPi;
        mHongRun = hongRun;
        if (mMeiYanChangedListener != null) {
            mMeiYanChangedListener.onMeiYanChanged(meiBai, true, moPi, true, hongRun, true);
        }
    }

    public void setMeiBai(int meiBai) {
        if (mMeiBai != meiBai) {
            mMeiBai = meiBai;
            if (mMeiYanChangedListener != null) {
                mMeiYanChangedListener.onMeiYanChanged(meiBai, true, mMoPi, false, mHongRun, false);
            }
        }
    }

    public void setMoPi(int moPi) {
        if (mMoPi != moPi) {
            mMoPi = moPi;
            if (mMeiYanChangedListener != null) {
                mMeiYanChangedListener.onMeiYanChanged(mMeiBai, false, moPi, true, mHongRun, false);
            }
        }
    }

    public void setHongRun(int hongRun) {
        if (mHongRun != hongRun) {
            mHongRun = hongRun;
            if (mMeiYanChangedListener != null) {
                mMeiYanChangedListener.onMeiYanChanged(mMeiBai, false, mMoPi, false, hongRun, true);
            }
        }
    }

    public void setFilter(int filterName){
        if (mMeiYanChangedListener != null) {
            mMeiYanChangedListener.onFilterChanged(filterName);
        }
    }

    public int getMeiBai() {
        return mMeiBai;
    }

    public int getMoPi() {
        return mMoPi;
    }

    public int getHongRun() {
        return mHongRun;
    }

    public void saveBeautyValue() {
        JSONObject obj = new JSONObject();
        obj.put("skin_whiting", mMeiBai);
        obj.put("skin_smooth", mMoPi);
        obj.put("skin_tenderness", mHongRun);
        CommonHttpUtil.setBeautyValue(obj.toJSONString());
    }
}
