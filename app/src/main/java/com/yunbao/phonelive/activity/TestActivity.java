package com.yunbao.phonelive.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;
import com.yunbao.common.CommonAppConfig;
import com.yunbao.common.Constants;
import com.yunbao.common.bean.UserBean;
import com.yunbao.common.http.HttpCallback;
import com.yunbao.common.interfaces.CommonCallback;
import com.yunbao.common.utils.ToastUtil;
import com.yunbao.main.activity.MainActivity;
import com.yunbao.main.activity.RecommendActivity;
import com.yunbao.main.dialog.LoginForbiddenDialogFragment;
import com.yunbao.main.http.MainHttpUtil;
import com.yunbao.phonelive.R;
import com.yunbao.video.activity.VideoPlayActivity;
import com.yunbao.video.bean.VideoBean;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by cxf on 2018/9/17.
 */
public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    private boolean mFirstLogin;//是否是第一次登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.loginTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.vedioTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });
    }

    private void login(){
        MainHttpUtil.login("13602684354", "visitor", "86", new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                onLoginSuccess(code, msg, info);
            }
        });
    }

    //登录成功！
    private void onLoginSuccess(int code, String msg, String[] info) {
        if (code == 0) {
            if (info.length > 0) {
                JSONObject obj = JSON.parseObject(info[0]);
                String uid = obj.getString("id");
                String token = obj.getString("token");
                mFirstLogin = obj.getIntValue("isreg") == 1;
                CommonAppConfig.getInstance().setLoginInfo(uid, token, true);
                getBaseUserInfo();
                //友盟统计登录
//                MobclickAgent.onProfileSignIn(mLoginType, uid);
            }
        } else if (code == 1002) {
            if (info.length > 0) {
                LoginForbiddenDialogFragment fragment = new LoginForbiddenDialogFragment();
                JSONObject obj = JSON.parseObject(info[0]);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TIP, obj.getString("ban_reason"));
                bundle.putString(Constants.UID, obj.getString("ban_tip"));
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "LoginForbiddenDialogFragment");
            }
        } else {
            ToastUtil.show(msg);
        }
    }

    /**
     * 获取用户信息
     */
    private void getBaseUserInfo() {
        MainHttpUtil.getBaseInfo(new CommonCallback<UserBean>() {
            @Override
            public void callback(UserBean bean) {
//                if (mFirstLogin) {
//                    RecommendActivity.forward(TestActivity.this, mFirstLogin);
//                } else {
//                    MainActivity.forward(TestActivity.this, mFirstLogin);
//                }
//                finish();
            }
        });
    }

    private void playVideo(){
        VideoBean bean = new Gson().fromJson("{\"ad_url\":\"\",\"addtime\":\"2022-02-06 05:11:31\",\"anyway\":\"0.450\",\"avatar\":\"http://aiyongpic.china-sns.cn/android_2_20220202_193409_8189683.jpg?imageView2/2/w/600/h/600\",\"city\":\"\",\"classid\":\"1\",\"comments\":\"3\",\"datetime\":\"6天前\",\"goods_type\":\"0\",\"goodsid\":\"0\",\"href\":\"http://aiyongpic.china-sns.cn/android_2_20220206_051132_9711722.mp4\",\"href_w\":\"http://aiyongpic.china-sns.cn/android_2_20220206_051132_9711722.mp4\",\"id\":\"1\",\"is_ad\":\"0\",\"isattent\":\"0\",\"islike\":\"0\",\"isstep\":\"0\",\"lat\":\"0.0\",\"likes\":\"0\",\"lng\":\"0.0\",\"music_id\":\"0\",\"shares\":\"0\",\"status\":\"1\",\"steps\":\"0\",\"thumb\":\"http://aiyongpic.china-sns.cn/android_2_20220206_051132_4061266.jpg\",\"thumb_s\":\"http://aiyongpic.china-sns.cn/android_2_20220206_051132_4061266.jpg?imageView2/2/w/200/h/200\",\"title\":\"新年快乐，哦耶\",\"type\":\"0\",\"uid\":\"2\",\"user_nicename\":\"lutao\",\"userinfo\":{\"avatar\":\"http://aiyongpic.china-sns.cn/android_2_20220202_193409_8189683.jpg?imageView2/2/w/600/h/600\",\"avatar_thumb\":\"http://aiyongpic.china-sns.cn/android_2_20220202_193409_8189683.jpg?imageView2/2/w/200/h/200\",\"birthday\":\"2009-02-02\",\"city\":\"好像在火星\",\"consumption\":\"0\",\"id\":\"2\",\"issuper\":\"1\",\"level\":\"1\",\"level_anchor\":\"1\",\"liang\":{\"name\":\"0\"},\"location\":\"北京市北京市平谷区\",\"province\":\"\",\"sex\":\"1\",\"signature\":\"哈罗，虎年大吉！\",\"user_nicename\":\"lutao\",\"user_status\":\"1\",\"vip\":{\"type\":\"0\"},\"votestotal\":\"0\"},\"views\":\"2\"}", VideoBean.class);
        VideoPlayActivity.forwardSingle(this,bean);
    }
}
