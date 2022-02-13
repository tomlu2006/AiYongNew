package com.yunbao.live.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yunbao.common.CommonAppConfig;
import com.yunbao.common.CommonAppContext;
import com.yunbao.common.bean.UserBean;
import com.yunbao.common.http.CommonHttpUtil;
import com.yunbao.common.http.HttpCallback;
import com.yunbao.common.http.HttpClient;
import com.yunbao.common.interfaces.CommonCallback;
import com.yunbao.common.utils.L;
import com.yunbao.common.utils.MD5Util;
import com.yunbao.common.utils.StringUtil;
import com.yunbao.live.LiveConfig;

/**
 * Created by cxf on 2019/3/21.
 */

public class LiveHttpUtil {


    /**
     * 取消网络请求
     */
    public static void cancel(String tag) {
        HttpClient.getInstance().cancel(tag);
    }

    /**
     * 获取当前直播间的用户列表
     */
    public static void getUserList(String liveuid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getUserLists", LiveHttpConsts.GET_USER_LIST)
                .params("liveuid", liveuid)
                .params("stream", stream)
                .execute(callback);
    }

    /**
     * 当直播间是门票收费，计时收费或切换成计时收费的时候，观众请求这个接口
     *
     * @param liveUid 主播的uid
     * @param stream  主播的stream
     */
    public static void roomCharge(String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.roomCharge", LiveHttpConsts.ROOM_CHARGE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("liveuid", liveUid)
                .execute(callback);

    }

    /**
     * 当直播间是计时收费的时候，观众每隔一分钟请求这个接口进行扣费
     *
     * @param liveUid 主播的uid
     * @param stream  主播的stream
     */
    public static void timeCharge(String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.timeCharge", LiveHttpConsts.TIME_CHARGE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("liveuid", liveUid)
                .execute(callback);
    }


    /**
     * 获取用户余额
     */
    public static void getCoin(HttpCallback callback) {
        HttpClient.getInstance().get("Live.getCoin", LiveHttpConsts.GET_COIN)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .execute(callback);
    }

    /**
     * 获取用户的直播记录
     *
     * @param touid 对方的uid
     */
    public static void getLiveRecord(String touid, int p, HttpCallback callback) {
        HttpClient.getInstance().get("User.getLiverecord", LiveHttpConsts.GET_LIVE_RECORD)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("touid", touid)
                .params("p", p)
                .execute(callback);
    }

    /**
     * 获取直播回放url
     *
     * @param recordId 视频的id
     */
    public static void getAliCdnRecord(String recordId, HttpCallback callback) {
        HttpClient.getInstance().get("User.getAliCdnRecord", LiveHttpConsts.GET_ALI_CDN_RECORD)
                .params("id", recordId)
                .execute(callback);
    }


    /**
     * 获取主播印象列表
     */
    public static void getAllImpress(String touid, HttpCallback callback) {
        HttpClient.getInstance().get("User.GetUserLabel", LiveHttpConsts.GET_ALL_IMPRESS)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("touid", touid)
                .execute(callback);
    }

    /**
     * 给主播设置印象
     */
    public static void setImpress(String touid, String ImpressIDs, HttpCallback callback) {
        HttpClient.getInstance().get("User.setUserLabel", LiveHttpConsts.SET_IMPRESS)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("touid", touid)
                .params("labels", ImpressIDs)
                .execute(callback);
    }


    /**
     * 获取当前直播间的管理员列表
     */
    public static void getAdminList(String liveUid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getAdminList", LiveHttpConsts.GET_ADMIN_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .execute(callback);
    }

    /**
     * 主播设置或取消直播间的管理员
     */
    public static void setAdmin(String liveUid, String touid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.setAdmin", LiveHttpConsts.SET_ADMIN)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("touid", touid)
                .execute(callback);
    }

    /**
     * 获取直播间的禁言列表
     */
    public static void getLiveShutUpList(String liveUid, int p, HttpCallback callback) {
        HttpClient.getInstance().get("Livemanage.getShutList", LiveHttpConsts.GET_LIVE_SHUT_UP_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("p", p)
                .execute(callback);
    }

    /**
     * 直播间解除禁言
     */
    public static void liveCancelShutUp(String liveUid, String toUid, HttpCallback callback) {
        HttpClient.getInstance().get("Livemanage.cancelShut", LiveHttpConsts.LIVE_CANCEL_SHUT_UP)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("touid", toUid)
                .execute(callback);
    }

    /**
     * 获取直播间的拉黑列表
     */
    public static void getLiveBlackList(String liveUid, int p, HttpCallback callback) {
        HttpClient.getInstance().get("Livemanage.getKickList", LiveHttpConsts.GET_LIVE_BLACK_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("p", p)
                .execute(callback);
    }

    /**
     * 直播间解除拉黑
     */
    public static void liveCancelBlack(String liveUid, String toUid, HttpCallback callback) {
        HttpClient.getInstance().get("Livemanage.cancelKick", LiveHttpConsts.LIVE_CANCEL_BLACK)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("touid", toUid)
                .execute(callback);
    }


    /**
     * 直播结束后，获取直播收益，观看人数，时长等信息
     */
    public static void getLiveEndInfo(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.stopInfo", LiveHttpConsts.GET_LIVE_END_INFO)
                .params("stream", stream)
                .execute(callback);
    }

    /**
     * 获取直播间举报内容列表
     */
    public static void getLiveReportList(HttpCallback callback) {
        HttpClient.getInstance().get("Live.getReportClass", LiveHttpConsts.GET_LIVE_REPORT_LIST)
                .execute(callback);
    }

    /**
     * 举报用户
     */
    public static void setReport(String touid, String content, HttpCallback callback) {
        HttpClient.getInstance().get("Live.setReport", LiveHttpConsts.SET_REPORT)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("touid", touid)
                .params("content", content)
                .execute(callback);
    }

    /**
     * 直播间点击聊天列表和头像出现的弹窗
     */
    public static void getLiveUser(String touid, String liveUid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getPop", LiveHttpConsts.GET_LIVE_USER)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("touid", touid)
                .params("liveuid", liveUid)
                .execute(callback);
    }

    /**
     * 主播或管理员踢人
     */
    public static void kicking(String liveUid, String touid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.kicking", LiveHttpConsts.KICKING)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("touid", touid)
                .execute(callback);
    }


    /**
     * 主播或管理员禁言
     */
    public static void setShutUp(String liveUid, String stream, int type, String touid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.setShutUp", LiveHttpConsts.SET_SHUT_UP)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .params("type", type)
                .params("touid", touid)
                .execute(callback);
    }


    /**
     * 超管关闭直播间或禁用账户
     *
     * @param type 0表示关闭当前直播 1表示禁播，2表示封禁账号
     */
    public static void superCloseRoom(String liveUid, int type, HttpCallback callback) {
        HttpClient.getInstance().get("Live.superStopRoom", LiveHttpConsts.SUPER_CLOSE_ROOM)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("type", type)
                .execute(callback);
    }


    /**
     * 守护商品类型列表
     */
    public static void getGuardBuyList(HttpCallback callback) {
        HttpClient.getInstance().get("Guard.getList", LiveHttpConsts.GET_GUARD_BUY_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .execute(callback);
    }

    /**
     * 购买守护接口
     */
    public static void buyGuard(String liveUid, String stream, int guardId, HttpCallback callback) {
        HttpClient.getInstance().get("Guard.BuyGuard", LiveHttpConsts.BUY_GUARD)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .params("guardid", guardId)
                .execute(callback);
    }


    /**
     * 查看主播的守护列表
     */
    public static void getGuardList(String liveUid, int p, HttpCallback callback) {
        HttpClient.getInstance().get("Guard.GetGuardList", LiveHttpConsts.GET_GUARD_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("p", p)
                .execute(callback);
    }


    /**
     * 观众跟主播连麦时，获取自己的流地址
     */
    public static void getLinkMicStream(HttpCallback callback) {
        HttpClient.getInstance().get("Linkmic.RequestLVBAddrForLinkMic", LiveHttpConsts.GET_LINK_MIC_STREAM)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .execute(callback);
    }

    /**
     * 主播连麦成功后，要把这些信息提交给服务器
     *
     * @param touid    连麦用户ID
     * @param pull_url 连麦用户播流地址
     */
    public static void linkMicShowVideo(String touid, String pull_url) {
        HttpClient.getInstance().get("Live.showVideo", LiveHttpConsts.LINK_MIC_SHOW_VIDEO)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", CommonAppConfig.getInstance().getUid())
                .params("touid", touid)
                .params("pull_url", pull_url)
                .execute(new HttpCallback() {
                    @Override
                    public void onSuccess(int code, String msg, String[] info) {

                    }
                });
    }


    /**
     * 主播设置是否允许观众发起连麦
     */
    public static void setLinkMicEnable(boolean linkMicEnable, HttpCallback callback) {
        HttpClient.getInstance().get("Linkmic.setMic", LiveHttpConsts.SET_LINK_MIC_ENABLE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("ismic", linkMicEnable ? 1 : 0)
                .execute(callback);
    }


    /**
     * 观众检查主播是否允许连麦
     */
    public static void checkLinkMicEnable(String liveUid, HttpCallback callback) {
        HttpClient.getInstance().get("Linkmic.isMic", LiveHttpConsts.CHECK_LINK_MIC_ENABLE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("liveuid", liveUid)
                .execute(callback);
    }

    /**
     * 连麦pk检查对方主播在线状态
     */
    public static void livePkCheckLive(String liveUid, String stream, String uidStream, HttpCallback callback) {
        HttpClient.getInstance().get("Livepk.checkLive", LiveHttpConsts.LIVE_PK_CHECK_LIVE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .params("uid_stream", uidStream)
                .execute(callback);
    }

    /**
     * 直播间发红包
     */
    public static void sendRedPack(String stream, String coin, String count, String title, int type, int sendType, HttpCallback callback) {
        HttpClient.getInstance().get("Red.SendRed", LiveHttpConsts.SEND_RED_PACK)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("coin", coin)
                .params("nums", count)
                .params("des", title)
                .params("type", type)
                .params("type_grant", sendType)
                .execute(callback);
    }

    /**
     * 获取直播间红包列表
     */
    public static void getRedPackList(String stream, HttpCallback callback) {
        String sign = MD5Util.getMD5("stream=" + stream + "&" + CommonHttpUtil.SALT);
        HttpClient.getInstance().get("Red.GetRedList", LiveHttpConsts.GET_RED_PACK_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("sign", sign)
                .execute(callback);
    }

    /**
     * 直播间抢红包
     */
    public static void robRedPack(String stream, int redPackId, HttpCallback callback) {
        String uid = CommonAppConfig.getInstance().getUid();
        String sign = MD5Util.getMD5("redid=" + redPackId + "&stream=" + stream + "&uid=" + uid + "&" + CommonHttpUtil.SALT);
        HttpClient.getInstance().get("Red.RobRed", LiveHttpConsts.ROB_RED_PACK)
                .params("uid", uid)
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("redid", redPackId)
                .params("sign", sign)
                .execute(callback);
    }


    /**
     * 直播间红包领取详情
     */
    public static void getRedPackResult(String stream, int redPackId, HttpCallback callback) {
        String uid = CommonAppConfig.getInstance().getUid();
        String sign = MD5Util.getMD5("redid=" + redPackId + "&stream=" + stream + "&" + CommonHttpUtil.SALT);
        HttpClient.getInstance().get("Red.GetRedRobList", LiveHttpConsts.GET_RED_PACK_RESULT)
                .params("uid", uid)
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("redid", redPackId)
                .params("sign", sign)
                .execute(callback);
    }

    /**
     * 发送弹幕
     */
    public static void sendDanmu(String content, String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.sendBarrage", LiveHttpConsts.SEND_DANMU)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .params("giftid", "1")
                .params("giftcount", "1")
                .params("content", content)
                .execute(callback);
    }

    /**
     * 检查直播间状态，是否收费 是否有密码等
     *
     * @param liveUid 主播的uid
     * @param stream  主播的stream
     */
    public static void checkLive(String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.checkLive", LiveHttpConsts.CHECK_LIVE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 观众进入直播间
     */
    public static void enterRoom(String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.enterRoom", LiveHttpConsts.ENTER_ROOM)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("city", CommonAppConfig.getInstance().getCity())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 获取礼物列表，同时会返回剩余的钱
     *
     * @param live_type 1语音直播间  0普通直播
     */
    public static void getGiftList(int live_type, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getGiftList", LiveHttpConsts.GET_GIFT_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("live_type", live_type)
                .execute(callback);
    }

    /**
     * 观众给主播送礼物
     *
     * @param touids 需要被送礼物的用户，多个用户时用英文,分隔
     */
    public static void sendGift(String liveUid, String stream, String touids, int giftId, String giftCount, int ispack, int is_sticker, HttpCallback callback) {
        HttpClient.getInstance().get("Live.sendGift", LiveHttpConsts.SEND_GIFT)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .params("touids", touids)
                .params("giftid", giftId)
                .params("ispack", ispack)
                .params("is_sticker", is_sticker)
                .params("giftcount", TextUtils.isEmpty(giftCount) ? "1" : giftCount)
                .execute(callback);
    }

    /**
     * 连麦pk搜索主播
     */

    public static void livePkSearchAnchor(String key, int p, HttpCallback callback) {
        HttpClient.getInstance().get("Livepk.Search", LiveHttpConsts.LIVE_PK_SEARCH_ANCHOR)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("key", key)
                .params("p", p)
                .execute(callback);
    }


    /**
     * 获取主播连麦pk列表
     */
    public static void getLivePkList(int p, HttpCallback callback) {
        HttpClient.getInstance().get("Livepk.GetLiveList", LiveHttpConsts.GET_LIVE_PK_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("p", p)
                .execute(callback);
    }

    /**
     * 主播添加背景音乐时，搜索歌曲
     *
     * @param key      关键字
     * @param callback
     */
    public static void searchMusic(String key, HttpCallback callback) {
        HttpClient.getInstance().get("Livemusic.searchMusic", LiveHttpConsts.SEARCH_MUSIC)
                .params("key", key)
                .execute(callback);
    }

    /**
     * 获取歌曲的地址 和歌词的地址
     */
    public static void getMusicUrl(String musicId, HttpCallback callback) {
        HttpClient.getInstance().get("Livemusic.getDownurl", LiveHttpConsts.GET_MUSIC_URL)
                .params("audio_id", musicId)
                .execute(callback);
    }


    /**
     * 主播开播
     *
     * @param title       直播标题
     * @param type        直播类型 普通 密码 收费等
     * @param typeVal     密码 价格等
     * @param isVoiceRoom 是否是语音聊天室
     * @param callback
     */
    public static void createRoom(String title, int liveClassId, int type, String typeVal, int isShop, String thumb, boolean openLocation, boolean isVoiceRoom, HttpCallback callback) {
        CommonAppConfig appConfig = CommonAppConfig.getInstance();
        UserBean u = appConfig.getUserBean();
        if (u == null) {
            return;
        }
        HttpClient.getInstance().get("Live.createRoom", LiveHttpConsts.CREATE_ROOM)
                .params("uid", u.getId())
                .params("token", appConfig.getToken())
                .params("user_nicename", u.getUserNiceName())
                .params("avatar", u.getAvatar())
                .params("avatar_thumb", u.getAvatarThumb())
                .params("city", openLocation ? appConfig.getCity() : "")
                .params("province", openLocation ? appConfig.getProvince() : "")
                .params("lat", openLocation ? String.valueOf(appConfig.getLat()) : "")
                .params("lng", openLocation ? String.valueOf(appConfig.getLng()) : "")
                .params("title", title)
                .params("liveclassid", liveClassId)
                .params("type", type)
                .params("type_val", typeVal)
                .params("isshop", isShop)
                .params("thumb", thumb)
                .params("live_type", isVoiceRoom ? 1 : 0)
                .params("deviceinfo", LiveConfig.getSystemParams())
                .execute(callback);
    }

    /**
     * 修改直播状态
     */
    public static void changeLive(String stream) {
        HttpClient.getInstance().get("Live.changeLive", LiveHttpConsts.CHANGE_LIVE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("status", "1")
                .execute(new HttpCallback() {
                    @Override
                    public void onSuccess(int code, String msg, String[] info) {
                        L.e("开播---changeLive---->" + info[0]);
                    }
                });
    }

    /**
     * 主播结束直播
     */
    public static void stopLive(String stream, HttpCallback callback) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        CommonAppConfig appConfig = CommonAppConfig.getInstance();
        String uid = appConfig.getUid();
        String token = appConfig.getToken();
        String sign = MD5Util.getMD5(StringUtil.contact("stream=", stream, "&time=", time, "&token=", token, "&uid=", uid, "&", CommonHttpUtil.SALT));
        HttpClient.getInstance().get("Live.stopRoom", LiveHttpConsts.STOP_LIVE)
                .params("stream", stream)
                .params("uid", uid)
                .params("token", token)
                .params("time", time)
                .params("sign", sign)
                .execute(callback);
    }

    /**
     * 主播开播前获取sdk类型  0金山  1腾讯
     */
    public static void getLiveSdk(HttpCallback callback) {
        HttpClient.getInstance().get("Live.getSDK", LiveHttpConsts.GET_LIVE_SDK)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .execute(callback);
    }


    /**
     * 腾讯sdk 跟主播连麦时，获取主播的低延时流
     */
    public static void getTxLinkMicAccUrl(String originStreamUrl, HttpCallback callback) {
        HttpClient.getInstance().get("Linkmic.RequestPlayUrlWithSignForLinkMic", LiveHttpConsts.GET_TX_LINK_MIC_ACC_URL)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("originStreamUrl", originStreamUrl)
                .execute(callback);
    }


    /**
     * 连麦时候 主播混流
     */
    public static void linkMicTxMixStream(String mergeparams, HttpCallback callback) {
        HttpClient.getInstance().post("Linkmic.MergeVideoStream", LiveHttpConsts.LINK_MIC_TX_MIX_STREAM)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("mergeparams", mergeparams)
                .execute(callback);
    }


    /**
     * 我是哪些直播间的管理员，返回这些直播间列表
     */
    public static void getMyAdminRoomList(int p, HttpCallback callback) {
        HttpClient.getInstance().get("Livemanage.GetRoomList", LiveHttpConsts.GET_MY_ADMIN_ROOM_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("p", p)
                .execute(callback);
    }


    /**
     * 获取直播间奖池等级
     */
    public static void getLiveGiftPrizePool(String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Jackpot.GetJackpot", LiveHttpConsts.GET_LIVE_GIFT_PRIZE_POOL)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .execute(callback);
    }

    /**
     * 主播checkLive
     */
    public static void anchorCheckLive(String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.checkLiveing", LiveHttpConsts.ANCHOR_CHECK_LIVE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 获取直播间信息
     */
    public static void getLiveInfo(String liveUid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getLiveInfo", LiveHttpConsts.GET_LIVE_INFO)
                .params("liveuid", liveUid)
                .execute(callback);
    }

    /**
     * 获取转盘礼物列表
     */
    public static void getTurntable(int live_type,HttpCallback callback) {
        HttpClient.getInstance().get("Turntable.GetTurntable", LiveHttpConsts.GET_TURN_TABLE)
                .params("live_type",live_type)
                .execute(callback);
    }

    /**
     * 转盘抽奖
     */

    public static void turn(String id, String liveUid, String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Turntable.Turn", LiveHttpConsts.TURN)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("id", id)
                .params("stream", stream)
                .params("liveuid", liveUid)
                .execute(callback);
    }

    /**
     * 转盘抽奖记录
     */

    public static void getTurnRecord(int live_type,int p, HttpCallback callback) {
        HttpClient.getInstance().get("Turntable.GetWin", LiveHttpConsts.GET_WIN)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("live_type",live_type)
                .params("p", p)
                .execute(callback);
    }

    /*获取背包礼物*/
    public static void getBackpack(int live_type, HttpCallback callback) {
        HttpClient.getInstance().get("Backpack.getBackpack", LiveHttpConsts.GET_BACK_PACK)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("live_type", live_type)
                .execute(callback);
    }

    /*获取直播间在售商品*/
    public static void getSale(int p, String liveUid, HttpCallback callback) {
        HttpClient.getInstance().get("Shop.GetSale", LiveHttpConsts.GET_SALE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("p", p)
                .params("liveuid", liveUid)
                .execute(callback);
    }


    /**
     * 添加或者移除直播间在售商品
     */
    public static void shopSetSale(String goodsid, int issale, HttpCallback callback) {
        HttpClient.getInstance().get("Shop.SetSale", LiveHttpConsts.SET_SALE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("goodsid", goodsid)
                .params("issale", issale)
                .execute(callback);
    }


    /**
     * 主播增删直播间代售的平台商品
     */
    public static void setPlatGoodsSale(String goodsid, int issale, HttpCallback callback) {
        HttpClient.getInstance().get("Shop.setPlatformGoodsSale", LiveHttpConsts.SET_PLAT_GOODS_SALE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("goodsid", goodsid)
                .params("issale", issale)
                .execute(callback);
    }


    /**
     * 展示或取消直播间的商品
     */
    public static void setLiveGoodsShow(String goodsid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.setLiveGoodsIsShow", LiveHttpConsts.SET_LIVE_GOODS_SHOW)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("goodsid", goodsid)
                .execute(callback);
    }


    /**
     * 获取道具礼物贴纸列表
     */
    public static void getLiveStickerList(String beautyKey, final CommonCallback<String> commonCallback) {
        String sign = MD5Util.getMD5(StringUtil.contact("package_name=", CommonAppConfig.PACKAGE_NAME, "&source=android&", beautyKey));
        OkGo.<String>get("http://data.facegl.com/appapi/gift/index")
                .params("package_name", CommonAppConfig.PACKAGE_NAME)
                .params("source", "android")
                .params("sign", sign)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject obj = JSON.parseObject(response.body());
                            if (commonCallback != null) {
                                commonCallback.callback(obj.getString("info"));
                            }
                        } catch (Exception e) {

                        }
                    }
                });

    }


    /**
     * 直播间添加商品 搜索商品
     */
    public static void searchLiveGoodsList(int p, String keyword, HttpCallback callback) {
        HttpClient.getInstance().get("Shop.searchShopGoods", LiveHttpConsts.SEARCH_LIVE_GOODS_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("keywords", keyword)
                .params("p", p)
                .execute(callback);
    }


    /**
     * 直播间添加商品 搜索商品
     */
    public static void searchLivePlatGoodsList(int p, String keyword, HttpCallback callback) {
        HttpClient.getInstance().get("Shop.searchOnsalePlatformGoods", LiveHttpConsts.SEARCH_LIVE_PLAT_GOODS_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("keywords", keyword)
                .params("p", p)
                .execute(callback);
    }

    /**
     * 获取每日任务列表
     */
    public static void getDailyTask(String liveUid, int isLive, HttpCallback callback) {
        HttpClient.getInstance().get("User.seeDailyTasks", LiveHttpConsts.GET_DAILY_TASK)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("liveuid", liveUid)
                .params("islive", isLive)
                .execute(callback);
    }


    /**
     * 每日任务--分享直播间
     */
    public static void dailyTaskShareLive() {
        HttpClient.getInstance().get("Live.shareLiveRoom", LiveHttpConsts.DAILY_TASK_SHARE_LIVE)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .execute(CommonHttpUtil.NO_CALLBACK);
    }

    /**
     * 每日任务--领取奖励
     */
    public static void dailyTaskReward(String taskId, HttpCallback callback) {
        HttpClient.getInstance().get("User.receiveTaskReward", LiveHttpConsts.DAILY_TASK_REWARD)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("taskid", taskId)
                .execute(callback);
    }


    /**
     * 语音聊天室--上麦申请
     */
    public static void applyVoiceLiveMic(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.applyVoiceLiveMic", LiveHttpConsts.APPLY_VOICE_LIVE_MIC)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 语音聊天室--取消上麦申请
     */
    public static void cancelVoiceLiveMic(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.cancelVoiceLiveMicApply", LiveHttpConsts.CANCEL_VOICE_LIVE_MIC)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 语音聊天室--获取上麦申请列表
     */
    public static void getVoiceMicApplyList(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getVoiceMicApplyList", LiveHttpConsts.GET_VOICE_MIC_APPLY_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 语音聊天室--上麦的用户自己主动下麦
     */
    public static void userDownVoiceMic(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.userCloseVoiceMic", LiveHttpConsts.USER_DOWN_VOICE_MIC)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 语音聊天室--主播同意/拒绝用户上麦申请
     *
     * @param applyUid 申请上麦用户的uid
     * @param status   0 拒绝 1 同意
     */
    public static void handleVoiceMicApply(String stream, String applyUid, int status, HttpCallback callback) {
        HttpClient.getInstance().get("Live.handleVoiceMicApply", LiveHttpConsts.HANDLE_VOICE_MIC_APPLY)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("apply_uid", applyUid)
                .params("status", status)
                .execute(callback);
    }


    /**
     * 语音聊天室--主播获取控麦列表
     */
    public static void getVoiceControlList(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.anchorGetVoiceMicList", LiveHttpConsts.GET_VOICE_CONTROL_LIST)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 语音聊天室--主播对空麦位设置禁麦或取消禁麦
     *
     * @param position 麦位
     * @param status   0禁麦   1取消禁麦
     */
    public static void banEmptyMicPosition(String stream, int position, int status, HttpCallback callback) {
        HttpClient.getInstance().get("Live.changeVoiceEmptyMicStatus", LiveHttpConsts.BAN_EMPTY_MIC_POSITION)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("position", position)
                .params("status", status)
                .execute(callback);
    }


    /**
     * 语音聊天室--主播对麦位上的用户开麦/闭麦
     *
     * @param position 麦位
     * @param status   0关麦 1开麦
     */
    public static void changeVoiceMicOpen(String stream, int position, int status, HttpCallback callback) {
        HttpClient.getInstance().get("Live.changeVoiceMicStatus", LiveHttpConsts.CHANGE_VOICE_MIC_OPEN)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("position", position)
                .params("status", status)
                .execute(callback);
    }


    /**
     * 语音聊天室--主播或管理员将连麦用户下麦
     */
    public static void closeUserVoiceMic(String stream, String toUid, HttpCallback callback) {
        HttpClient.getInstance().get("Live.closeUserVoiceMic", LiveHttpConsts.CLOSE_USER_VOICE_MIC)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .params("touid", toUid)
                .execute(callback);
    }


    /**
     * 语音聊天室--上麦用户获取推拉流地址
     */
    public static void getVoiceMicStream(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getVoiceMicStream", LiveHttpConsts.GET_VOICE_MIC_STREAM)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


    /**
     * 语音聊天室--麦上用户获取语音聊天室内主播和麦上其他用户的低延迟播流地址
     */
    public static void getVoiceLivePullStreams(String stream, HttpCallback callback) {
        HttpClient.getInstance().get("Live.getVoiceLivePullStreams", LiveHttpConsts.GET_VOICE_LIVE_PULL_STREAMS)
                .params("uid", CommonAppConfig.getInstance().getUid())
                .params("token", CommonAppConfig.getInstance().getToken())
                .params("stream", stream)
                .execute(callback);
    }


}
