package com.yunbao.phonelive;

import android.text.TextUtils;

//import com.meihu.beautylibrary.MHSDK;
//import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.live.TXLiveBase;
import com.umeng.commonsdk.UMConfigure;
import com.yunbao.common.CommonAppConfig;
import com.yunbao.common.CommonAppContext;
import com.yunbao.common.utils.DecryptUtil;
import com.yunbao.common.utils.L;
import com.yunbao.im.utils.ImMessageUtil;
import com.yunbao.im.utils.ImPushUtil;


/**
 * Created by cxf on 2017/8/3.
 */

public class AppContext extends CommonAppContext {

    private boolean mBeautyInited;

    @Override
    public void onCreate() {
        super.onCreate();
        //腾讯云直播鉴权url
        String liveLicenceUrl = "http://license.vod2.myqcloud.com/license/v1/96c55a5faee51bc346995a8/TXLiveSDK.licence";
        //腾讯云直播鉴权key
        String liveKey = "7410cc8a1fcbd2af4b8ba0aa9";
        //腾讯云视频鉴权url
        String ugcLicenceUrl = "http://license.vod2.myqcloud.com/license/v1/26aa72deba26e80ca417516c/TXUgcSDK.licence";
        //腾讯云视频鉴权key
        String ugcKey = "512e9149eccf49d21531ed2c8";
        TXLiveBase.getInstance().setLicence(this, liveLicenceUrl, liveKey, ugcLicenceUrl, ugcKey);
        L.setDeBug(BuildConfig.DEBUG);
        //初始化腾讯bugly
        CrashReport.initCrashReport(this);
        CrashReport.setAppVersion(this, CommonAppConfig.getInstance().getVersion());
        //初始化ShareSdk
//        MobSDK.init(this, "2aa0d8b53d", "e57996c8cb251395a37f1edb54b");
        //初始化极光推送
        ImPushUtil.getInstance().init(this);
        //初始化极光IM
        ImMessageUtil.getInstance().init();
        //初始化友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);

//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this);
//        }
//        PLShortVideoEnv.init(this);
    }

    /**
     * 初始化美狐
     */
    public void initBeautySdk(String beautyKey) {
        if (!TextUtils.isEmpty(beautyKey)) {
            if (!mBeautyInited) {
                mBeautyInited = true;
                if (CommonAppConfig.isYunBaoApp()) {
                    beautyKey = DecryptUtil.decrypt(beautyKey);
                }
//                MHSDK.init(this, beautyKey);
                CommonAppConfig.getInstance().setMhBeautyEnable(true);
                L.e("美狐初始化------->" + beautyKey);
            }
        } else {
            CommonAppConfig.getInstance().setMhBeautyEnable(false);
        }
    }

}
