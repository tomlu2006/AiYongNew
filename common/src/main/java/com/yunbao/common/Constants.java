package com.yunbao.common;

/**
 * Created by cxf on 2018/6/7.
 */

public class Constants {
    public static final String URL = "url";
    public static final String PAYLOAD = "payload";
    public static final String SEX = "sex";
    public static final String NICK_NAME = "nickname";
    public static final String AVATAR = "avatar";
    public static final String SIGN = "sign";
    public static final String TO_UID = "toUid";
    public static final String FROM_LIVE_ROOM = "fromLiveRoom";
    public static final String TO_NAME = "toName";
    public static final String STREAM = "stream";
    public static final String LIMIT = "limit";
    public static final String UID = "uid";
    public static final String TIP = "tip";
    public static final String FIRST_LOGIN = "firstLogin";
    public static final String USER_BEAN = "userBean";
    public static final String CLASS_ID = "classID";
    public static final String CLASS_NAME = "className";
    public static final String CHECKED_ID = "checkedId";
    public static final String CHECKED_COIN = "checkedCoin";
    public static final String LIVE_DANMU_PRICE = "danmuPrice";
    public static final String COIN_NAME = "coinName";
    public static final String AT_NAME = "atName";
    public static final String LIVE_BEAN = "liveBean";
    public static final String LIVE_TYPE = "liveType";
    public static final String LIVE_KEY = "liveKey";
    public static final String LIVE_POSITION = "livePosition";
    public static final String LIVE_TYPE_VAL = "liveTypeVal";
    public static final String LIVE_UID = "liveUid";
    public static final String LIVE_STREAM = "liveStream";
    public static final String LIVE_HOME = "liveHome";
    public static final String LIVE_FOLLOW = "liveFollow";
    public static final String LIVE_NEAR = "liveNear";
    public static final String LIVE_CLASS_PREFIX = "liveClass_";
    public static final String LIVE_CLASS_RECOMMEND = "liveRecommend";
    public static final String LIVE_ADMIN_ROOM = "liveAdminRoom";
    public static final String HAS_GAME = "hasGame";
    public static final String OPEN_FLASH = "openFlash";
    public static final String SHARE_QR_CODE_FILE = "shareQrCodeFile.png";
    public static final String ANCHOR = "anchor";
    public static final String FOLLOW = "follow";
    public static final String DIAMONDS = "??????";
    public static final String VOTES = "??????";
    public static final String SCORE = "??????";
    public static final String PAY_TYPE_ALI = "ali";
    public static final String PAY_TYPE_WX = "wx";
    public static final String PAY_TYPE_PAYPAL = "paypal";
    public static final String PAY_TYPE_BALANCE = "balance";
    public static final String PAY_BUY_COIN_ALI = "Charge.getAliOrder";
    public static final String PAY_BUY_COIN_WX = "Charge.getWxOrder";
    public static final String PAY_BUY_COIN_PAYPAL = "Charge.getBraintreePaypalOrder";

    public static final String PACKAGE_NAME_ALI = "com.eg.android.AlipayGphone";//??????????????????
    public static final String PACKAGE_NAME_WX = "com.tencent.mm";//???????????????
    public static final String PACKAGE_NAME_QQ = "com.tencent.mobileqq";//QQ?????????
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String ADDRESS = "address";
    public static final String SCALE = "scale";
    public static final String SELECT_IMAGE_PATH = "selectedImagePath";
    public static final String COPY_PREFIX = "copy://";
    public static final String TEL_PREFIX = "tel://";
    public static final String AUTH_PREFIX = "auth://";
    public static final int GUARD_TYPE_NONE = 0;
    public static final int GUARD_TYPE_MONTH = 1;
    public static final int GUARD_TYPE_YEAR = 2;

    public static final String DOWNLOAD_MUSIC = "downloadMusic";
    public static final String LINK = "link";
    public static final String REPORT = "report";
    public static final String SAVE = "save";
    public static final String DELETE = "delete";
    public static final String GOODS = "goods";
    public static final String MAX_COUNT = "maxCount";
    public static final String USE_CAMERA = "useCamera";
    public static final String USE_PREVIEW = "usePreview";


    public static final int SETTING_MODIFY_PWD = 15;
    public static final int SETTING_UPDATE_ID = 16;
    public static final int SETTING_CLEAR_CACHE = 18;
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 2;
    public static final int FOLLOW_FROM_FOLLOW = 1002;
    public static final int FOLLOW_FROM_FANS = 1003;
    public static final int FOLLOW_FROM_SEARCH = 1004;
    public static final String IM_FROM_HOME = "imFromHome";
    //??????????????????
    public static final int LIVE_TYPE_NORMAL = 0;//????????????
    public static final int LIVE_TYPE_PWD = 1;//????????????
    public static final int LIVE_TYPE_PAY = 2;//????????????
    public static final int LIVE_TYPE_TIME = 3;//????????????
    //?????????????????????
    public static final int LIVE_FUNC_BEAUTY = 2001;//??????
    public static final int LIVE_FUNC_CAMERA = 2002;//???????????????
    public static final int LIVE_FUNC_FLASH = 2003;//?????????
    public static final int LIVE_FUNC_MUSIC = 2004;//??????
    public static final int LIVE_FUNC_SHARE = 2005;//??????
    public static final int LIVE_FUNC_GAME = 2006;//??????
    public static final int LIVE_FUNC_RED_PACK = 2007;//??????
    public static final int LIVE_FUNC_LINK_MIC = 2008;//??????
    public static final int LIVE_FUNC_MIRROR = 2009;//??????
    public static final int LIVE_FUNC_TASK = 2010;//????????????
    public static final int LIVE_FUNC_LUCK = 2011;//????????????
    public static final int LIVE_FUNC_PAN = 2012;//??????
    //socket
    public static final String SOCKET_CONN = "conn";
    public static final String SOCKET_BROADCAST = "broadcastingListen";
    public static final String SOCKET_SEND = "broadcast";
    public static final String SOCKET_STOP_PLAY = "stopplay";//?????????????????????
    public static final String SOCKET_STOP_LIVE = "stopLive";//?????????????????????
    public static final String SOCKET_SEND_MSG = "SendMsg";//?????????????????????????????????????????????  PS:?????????????????????????????????????????????????????????????????????,???????????????????????????
    public static final String SOCKET_LIGHT = "light";//??????
    public static final String SOCKET_SEND_GIFT = "SendGift";//?????????
    public static final String SOCKET_SEND_BARRAGE = "SendBarrage";//?????????
    public static final String SOCKET_LEAVE_ROOM = "disconnect";//??????????????????
    public static final String SOCKET_LIVE_END = "StartEndLive";//??????????????????
    public static final String SOCKET_SYSTEM = "SystemNot";//????????????
    public static final String SOCKET_KICK = "KickUser";//??????
    public static final String SOCKET_SHUT_UP = "ShutUpUser";//??????
    public static final String SOCKET_SET_ADMIN = "setAdmin";//????????????????????????
    public static final String SOCKET_CHANGE_LIVE = "changeLive";//????????????????????????
    public static final String SOCKET_UPDATE_VOTES = "updateVotes";//???????????????????????????????????????????????????
    public static final String SOCKET_FAKE_FANS = "requestFans";//?????????
    public static final String SOCKET_LINK_MIC = "ConnectVideo";//??????
    public static final String SOCKET_LINK_MIC_ANCHOR = "LiveConnect";//????????????
    public static final String SOCKET_LINK_MIC_PK = "LivePK";//??????PK
    public static final String SOCKET_BUY_GUARD = "BuyGuard";//????????????
    public static final String SOCKET_RED_PACK = "SendRed";//??????
    public static final String SOCKET_LUCK_WIN = "luckWin";//??????????????????
    public static final String SOCKET_PRIZE_POOL_WIN = "jackpotWin";//????????????
    public static final String SOCKET_PRIZE_POOL_UP = "jackpotUp";//????????????
    public static final String SOCKET_GIFT_GLOBAL = "Sendplatgift";//????????????
    public static final String SOCKET_LIVE_GOODS_SHOW = "goodsLiveShow";//?????????????????????
    public static final String SOCKET_LIVE_GOODS_FLOAT = "shopGoodsLiveFloat";//?????????????????????
    public static final String SOCKET_VOICE_ROOM = "voiceRoom";//???????????????
    //??????socket
    public static final String SOCKET_GAME_ZJH = "startGame";//?????????
    public static final String SOCKET_GAME_HD = "startLodumaniGame";//????????????
    public static final String SOCKET_GAME_NZ = "startCattleGame";//????????????
    public static final String SOCKET_GAME_ZP = "startRotationGame";//????????????
    public static final String SOCKET_GAME_EBB = "startShellGame";//?????????

    public static final int SOCKET_WHAT_CONN = 0;
    public static final int SOCKET_WHAT_DISCONN = 2;
    public static final int SOCKET_WHAT_BROADCAST = 1;
    //socket ????????????
    public static final int SOCKET_USER_TYPE_NORMAL = 30;//????????????
    public static final int SOCKET_USER_TYPE_ADMIN = 40;//???????????????
    public static final int SOCKET_USER_TYPE_ANCHOR = 50;//??????
    public static final int SOCKET_USER_TYPE_SUPER = 60;//??????

    //?????????????????????1??????????????????2???????????????3???????????????
    public static final int CASH_ACCOUNT_ALI = 1;
    public static final int CASH_ACCOUNT_WX = 2;
    public static final int CASH_ACCOUNT_BANK = 3;
    public static final String CASH_ACCOUNT_ID = "cashAccountID";
    public static final String CASH_ACCOUNT = "cashAccount";
    public static final String CASH_ACCOUNT_NAME = "cashAccountName";
    public static final String CASH_ACCOUNT_TYPE = "cashAccountType";


    public static final int RED_PACK_TYPE_AVERAGE = 0;//????????????
    public static final int RED_PACK_TYPE_SHOU_QI = 1;//???????????????
    public static final int RED_PACK_SEND_TIME_NORMAL = 0;//????????????
    public static final int RED_PACK_SEND_TIME_DELAY = 1;//????????????

    public static final int JPUSH_TYPE_NONE = 0;
    public static final int JPUSH_TYPE_LIVE = 1;//??????
    public static final int JPUSH_TYPE_MESSAGE = 2;//??????

    public static final String VIDEO_HOME = "videoHome";
    public static final String VIDEO_USER = "videoUser_";
    public static final String VIDEO_KEY = "videoKey";
    public static final String VIDEO_POSITION = "videoPosition";
    public static final String VIDEO_SINGLE = "videoSingle";
    public static final String VIDEO_PAGE = "videoPage";
    public static final String VIDEO_BEAN = "videoBean";
    public static final String VIDEO_ID = "videoId";
    public static final String VIDEO_COMMENT_BEAN = "videoCommnetBean";
    public static final String VIDEO_FACE_OPEN = "videoOpenFace";
    public static final String VIDEO_FACE_HEIGHT = "videoFaceHeight";
    public static final String VIDEO_DURATION = "videoDuration";
    public static final String VIDEO_PATH = "videoPath";
    public static final String VIDEO_PATH_WATER = "videoPathWater";
    public static final String VIDEO_FROM_RECORD = "videoFromRecord";
    public static final String VIDEO_MUSIC_ID = "videoMusicId";
    public static final String VIDEO_HAS_BGM = "videoHasBgm";
    public static final String VIDEO_MUSIC_NAME_PREFIX = "videoMusicName_";
    public static final String VIDEO_SAVE_TYPE = "videoSaveType";
    public static final int VIDEO_SAVE_SAVE_AND_PUB = 1;//???????????????
    public static final int VIDEO_SAVE_SAVE = 2;//?????????
    public static final int VIDEO_SAVE_PUB = 3;//?????????

    public static final String MOB_QQ = "qq";
    public static final String MOB_QZONE = "qzone";
    public static final String MOB_WX = "wx";
    public static final String MOB_WX_PYQ = "wchat";
    public static final String MOB_FACEBOOK = "facebook";
    public static final String MOB_TWITTER = "twitter";
    public static final String MOB_PHONE = "phone";

    public static final String LIVE_SDK = "liveSdk";
    public static final String LIVE_CONFIG = "liveConfig";
    public static final int LIVE_SDK_KSY = 0;//????????????
    public static final int LIVE_SDK_TX = 1;//????????????


    public static final int LINK_MIC_TYPE_NORMAL = 0;//?????????????????????
    public static final int LINK_MIC_TYPE_ANCHOR = 1;//?????????????????????

    public static final String HAVE_STORE = "haveStore";
    public static final String VOICE_CHAT_ROOM = "voiceChatRoom";


    public static final String MONEY_SIGN = "??";

    public static final String CHOOSE_IMG = "chooseImage";
    public static final String CHOOSE_LOCATION = "chooseLocation";
    public static final String ACTIVE_BEAN = "activeBean";
    //????????????
    public static final int ACTIVE_TYPE_TEXT = 0;//0???????????????
    public static final int ACTIVE_TYPE_IMAGE = 1;//1?????????+?????????
    public static final int ACTIVE_TYPE_VIDEO = 2;//2?????????+?????????
    public static final int ACTIVE_TYPE_VOICE = 3;//3?????????+??????


    public static final int GIFT_TYPE_NORMAL = 0;//????????????
    public static final int GIFT_TYPE_DAO = 1;//??????
    public static final int GIFT_TYPE_PACK = 2;//??????


    public static final String MALL_APPLY_FAILED = "mallApplyFailed";
    public static final String MALL_APPLY_MANAGE_CLASS = "mallApplyManageClass";
    public static final String MALL_APPLY_BOND = "mallApplyBond";
    public static final String MALL_GOODS_CLASS = "mallGoodsClass";
    public static final String MALL_GOODS_ID = "mallGoodsId";
    public static final String MALL_GOODS_NAME = "mallGoodsName";
    public static final String MALL_GOODS_FROM_SHOP = "mallGoodsFromShop";
    public static final String MALL_CERT_IMG = "mallCertImg";
    public static final String MALL_CERT_TEXT = "mallCertText";

    public static final String MALL_REFUND_NAME = "mallRefundName";
    public static final String MALL_REFUND_PHONE = "mallRefundPhone";
    public static final String MALL_REFUND_PROVINCE = "mallRefundProvince";
    public static final String MALL_REFUND_CITY = "mallRefundCity";
    public static final String MALL_REFUND_ZONE = "mallRefundZone";
    public static final String MALL_REFUND_ADDRESS = "mallRefundAddress";

    public static final String MALL_BUYER_ADDRESS = "mallBuyerAddress";
    public static final String MALL_GOODS_SPEC = "mallGoodsSpec";
    public static final String MALL_GOODS_COUNT = "mallGoodsCount";
    public static final String MALL_POSTAGE = "mallPostage";
    public static final String MALL_SHOP_NAME = "mallShopName";

    public static final String MALL_ORDER_ID = "mallOrderId";
    public static final String MALL_ORDER_MONEY = "mallOrderMoney";
    public static final String MALL_ORDER_INDEX = "mallOrderIndex";
    public static final String MALL_PAY_GOODS_ORDER = "Buyer.goodsOrderPay";
    public static final String MALL_PAY_CONTENT_ALI = "Paidprogram.getAliOrder";
    public static final String MALL_PAY_CONTENT_WX = "Paidprogram.getWxOrder";
    public static final String MALL_PAY_CONTENT_PAYPAL = "Paidprogram.getBraintreePaypalOrder";

    // ???????????? -1????????????0????????????1????????????2????????????3????????????4????????????5??????
    public static final int MALL_ORDER_STATUS_CLOSE = -1;
    public static final int MALL_ORDER_STATUS_WAIT_PAY = 0;
    public static final int MALL_ORDER_STATUS_WAIT_SEND = 1;
    public static final int MALL_ORDER_STATUS_WAIT_RECEIVE = 2;
    public static final int MALL_ORDER_STATUS_WAIT_COMMENT = 3;
    public static final int MALL_ORDER_STATUS_COMMENT = 4;
    public static final int MALL_ORDER_STATUS_REFUND = 5;

    public static final String MALL_CASH_BALANCE = "mallCashBalance";
    public static final String MALL_CASH_TOTAL = "mallCashTotal";
    public static final String MALL_IM_ADMIN = "goodsorder_admin";
    public static final String MALL_PLAT_UID = "1";


    public static final int VOICE_CTRL_EMPTY = 0;//?????????????????????
    public static final int VOICE_CTRL_BAN = 2;//????????????????????????????????????
    public static final int VOICE_CTRL_CLOSE = -1;//?????????????????????????????????
    public static final int VOICE_CTRL_OPEN = 1;//?????????????????????
    public static final String VOICE_FACE = "voiceRoomFace";


}
