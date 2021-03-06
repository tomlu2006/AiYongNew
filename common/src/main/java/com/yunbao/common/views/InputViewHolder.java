package com.yunbao.common.views;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yunbao.common.CommonAppContext;
import com.yunbao.common.R;
import com.yunbao.common.activity.AbsActivity;
import com.yunbao.common.adapter.ImChatFacePagerAdapter;
import com.yunbao.common.custom.InterceptFrameLayout;
import com.yunbao.common.interfaces.OnFaceClickListener;
import com.yunbao.common.interfaces.PermissionCallback;
import com.yunbao.common.utils.DpUtil;
import com.yunbao.common.utils.FaceTextUtil;
import com.yunbao.common.utils.FloatWindowHelper;
import com.yunbao.common.utils.PermissionUtil;
import com.yunbao.common.utils.ScreenDimenUtil;
import com.yunbao.common.utils.ToastUtil;
import com.yunbao.common.utils.WordUtil;

public class InputViewHolder extends AbsViewHolder implements View.OnClickListener, OnFaceClickListener {

    private static final String TAG = "InputViewHolder";
    private View mSpace;
    private ViewGroup mGroupFace;
    private ViewGroup mGroupMore;
    private ImageView mBtnVoice;
    private ImageView mBtnFace;
    private EditText mEditText;
    private View mBtnVoiceRecord;
    private TextView mTvVoiceRecord;

    private Drawable mKeyBoardDrawable;
    private Drawable mFaceDrawable;
    private Drawable mVoiceDrawable;
    private Drawable mVoiceUnPressedDrawable;
    private Drawable mVoicePressedDrawable;
    private String mPressSayString;
    private String mUnPressStopString;
    private int mCancelVoiceHeight;


    private int mTopLayoutId;
    private int mFaceLayoutId;
    private boolean mUseVoice;//??????????????????
    private boolean mUseFace;//??????????????????
    private boolean mUseMore;//??????????????????
    private boolean mIsDialog;//?????????DialogFragment????????????,??????????????????
    private boolean mShowFace;
    private boolean mShowKeyBoard;
    private boolean mShowMore;
    private boolean mShowVoice;
    private InputMethodManager imm;
    private ActionListener mActionListener;
    private PermissionCallback mVoicePermissionCallback;
    private boolean mNeedShowFace;
    private String mEditHintString;


    public InputViewHolder(Context context, ViewGroup parentView, Object... args) {
        super(context, parentView, args);
    }

    @Override
    protected void processArguments(Object... args) {
        int len = args.length;
        if (len > 0) {
            mIsDialog = (boolean) args[0];
        }
        if (len > 1) {
            mTopLayoutId = (int) args[1];
        }

        if (len > 2) {
            mFaceLayoutId = (int) args[2];
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_input;
    }

    @Override
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup topGroup = (ViewGroup) findViewById(R.id.group_top);
        View topView = inflater.inflate(mTopLayoutId, topGroup, false);
        mEditText = topView.findViewById(R.id.edit);
        mEditHintString = mEditText.getHint().toString();
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    clickSend();
                    return true;
                }
                return false;
            }
        });
        if (mIsDialog) {
            InterceptFrameLayout editWrap = topView.findViewById(R.id.edit_wrap);
            editWrap.setOnInterceptListener(new InterceptFrameLayout.OnInterceptListener() {
                @Override
                public boolean onInterceptCall() {
                    if (mShowFace) {
                        hideFace();
                    }
                    if (mShowMore) {
                        hideMore();
                    }
                    return false;
                }
            });
        }
        ImageView btnVoice = topView.findViewById(R.id.btn_voice);
        mUseVoice = btnVoice != null;
        if (mUseVoice) {
            mBtnVoice = btnVoice;
            mVoiceDrawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_input_voice);
            mVoiceUnPressedDrawable = ContextCompat.getDrawable(mContext, R.drawable.bg_input_voice_0);
            mVoicePressedDrawable = ContextCompat.getDrawable(mContext, R.drawable.bg_input_voice_1);
            mBtnVoice.setImageDrawable(mVoiceDrawable);
            mBtnVoice.setOnClickListener(this);
            mBtnVoiceRecord = topView.findViewById(R.id.btn_voice_record);
            mTvVoiceRecord = topView.findViewById(R.id.tv_voice_record);
            mPressSayString = WordUtil.getString(R.string.im_press_say);
            mUnPressStopString = WordUtil.getString(R.string.im_unpress_stop);
            mCancelVoiceHeight = ScreenDimenUtil.getInstance().getScreenHeight() - DpUtil.dp2px(40);
            mBtnVoiceRecord.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent e) {
                    switch (e.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (mTvVoiceRecord != null) {
                                mTvVoiceRecord.setBackground(mVoicePressedDrawable);
                                mTvVoiceRecord.setText(mUnPressStopString);
                            }
                            if (mActionListener != null) {
                                mActionListener.onVoiceRecordStart();
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            if (mTvVoiceRecord != null) {
                                mTvVoiceRecord.setBackground(mVoiceUnPressedDrawable);
                                mTvVoiceRecord.setText(mPressSayString);
                            }
                            if (e.getRawY() < mCancelVoiceHeight) {
                                if (mActionListener != null) {
                                    mActionListener.onVoiceRecordCancel();
                                }
                                ToastUtil.show(R.string.video_comment_voice_tip_1);
                            } else {
                                if (mActionListener != null) {
                                    mActionListener.onVoiceRecordEnd();
                                }
                            }
                            break;
                    }
                    return true;
                }
            });
        }
        ImageView btnFace = topView.findViewById(R.id.btn_face);
        mUseFace = btnFace != null;
        if (mUseFace) {
            mBtnFace = btnFace;
            mFaceDrawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_input_face);
            mBtnFace.setImageDrawable(mFaceDrawable);
            mBtnFace.setOnClickListener(this);
            mGroupFace = findViewById(R.id.group_face);
            View faceView = inflater.inflate(mFaceLayoutId, mGroupFace, false);
            faceView.findViewById(R.id.btn_send).setOnClickListener(this);
            ViewPager viewPager = faceView.findViewById(R.id.viewPager);
            ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
            layoutParams.height = (int) (ScreenDimenUtil.getInstance().getScreenWdith() / 7f * 3 + 0.5f);
            viewPager.requestLayout();
            viewPager.setOffscreenPageLimit(10);
            final RadioGroup radioGroup = faceView.findViewById(R.id.radio_group);
            ImChatFacePagerAdapter adapter = new ImChatFacePagerAdapter(mContext, this);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            for (int i = 0, pageCount = adapter.getCount(); i < pageCount; i++) {
                RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.view_chat_indicator, radioGroup, false);
                radioButton.setId(i + 10000);
                if (i == 0) {
                    radioButton.setChecked(true);
                }
                radioGroup.addView(radioButton);
            }
            mGroupFace.addView(faceView);

        }
        View btnMore = topView.findViewById(R.id.btn_more);
        mUseMore = btnMore != null;
        if (mUseMore) {
            btnMore.setOnClickListener(this);
            mGroupMore = findViewById(R.id.group_more);
        }
        if (mUseVoice || mUseFace) {
            mKeyBoardDrawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_input_keyboard);
        }
        topGroup.addView(topView);
        mSpace = findViewById(R.id.space);
        imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        mEditText.requestFocus();
    }

    public void addMoreView(View view) {
        mGroupMore.addView(view);
    }

    /**
     * ????????????
     */
    public void hideKeyBoard() {
//        L.e(TAG, "----????????????------>hideKeyBoard");
        if (imm != null) {
            View curFocus = mEditText;
            if (curFocus == null) {
                if (mContext != null) {
                    curFocus = ((Activity) mContext).getCurrentFocus();
                }
            }
            imm.hideSoftInputFromWindow(curFocus != null ? curFocus.getWindowToken() : null, 0);
        }
    }


    /**
     * ????????????
     */
    public void showKeyBoard() {
//        L.e(TAG, "----????????????------>showKeyBoard");
        if (imm != null && mEditText != null) {
            mEditText.requestFocus();
            imm.showSoftInput(mEditText, InputMethodManager.SHOW_FORCED);
        }
    }


    /**
     * ????????????
     */
    private void hideFace() {
//        L.e(TAG, "----????????????------>hideFace");
        if (!mUseFace) {
            return;
        }
        if (mGroupFace != null && mGroupFace.getVisibility() != View.GONE) {
            mGroupFace.setVisibility(View.GONE);
        }
        if (mBtnFace != null) {
            mBtnFace.setImageDrawable(mFaceDrawable);
        }
        mShowFace = false;
    }

    /**
     * ????????????
     */
    public void showFace() {
//        L.e(TAG, "----????????????------>showFace");
        if (!mUseFace) {
            return;
        }
        if (mShowMore) {
            hideMore();
        }
        if (mShowVoice) {
            hideVoice();
        }
        if (mGroupFace != null && mGroupFace.getVisibility() != View.VISIBLE) {
            mGroupFace.setVisibility(View.VISIBLE);
        }
        if (mBtnFace != null) {
            mBtnFace.setImageDrawable(mKeyBoardDrawable);
        }
        mShowFace = true;
    }


    /**
     * ???????????????????????????
     */
    private void clickFace() {
        if (mIsDialog) {
            if (mShowKeyBoard) {
                mNeedShowFace = true;
                hideKeyBoard();
            } else {
                if (mShowFace) {
                    hideFace();
                    CommonAppContext.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showKeyBoard();
                        }
                    }, 200);
                } else {
                    showFace();
                }
            }
        } else {
            if (mShowKeyBoard) {
                hideKeyBoard();
                showFace();
            } else {
                if (mShowFace) {
                    hideFace();
                    showKeyBoard();
                } else {
                    showFace();
                }
            }
        }


    }

    /**
     * ??????????????????
     */
    private void showMore() {
        if (!mUseMore) {
            return;
        }
        if (mShowKeyBoard) {
            hideKeyBoard();
        }
        if (mShowFace) {
            hideFace();
        }
        if (mShowVoice) {
            hideVoice();
        }
        if (mGroupMore != null && mGroupMore.getVisibility() != View.VISIBLE) {
            mGroupMore.setVisibility(View.VISIBLE);
        }
        mShowMore = true;
    }

    /**
     * ??????????????????
     */
    private void hideMore() {
        if (!mUseMore) {
            return;
        }
        if (mGroupMore != null && mGroupMore.getVisibility() != View.GONE) {
            mGroupMore.setVisibility(View.GONE);
        }
        mShowMore = false;
    }

    /**
     * ????????????
     */
    private void showVoice() {
        if (!mUseVoice) {
            return;
        }
        if (!FloatWindowHelper.checkVoice(false)) {
            return;
        }
        if (mVoicePermissionCallback == null) {
            mVoicePermissionCallback = new PermissionCallback() {
                @Override
                public void onAllGranted() {
                    if (mShowKeyBoard) {
                        hideKeyBoard();
                    }
                    if (mShowFace) {
                        hideFace();
                    }
                    if (mShowMore) {
                        hideMore();
                    }
                    if (mBtnVoiceRecord != null && mBtnVoiceRecord.getVisibility() != View.VISIBLE) {
                        mBtnVoiceRecord.setVisibility(View.VISIBLE);
                    }
                    if (mBtnVoice != null) {
                        mBtnVoice.setImageDrawable(mKeyBoardDrawable);
                    }
                    mShowVoice = true;
                }
            };
        }
        PermissionUtil.request((AbsActivity) mContext, mVoicePermissionCallback,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO);


    }


    /**
     * ????????????
     */
    private void hideVoice() {
        if (!mUseVoice) {
            return;
        }
        if (mBtnVoiceRecord != null && mBtnVoiceRecord.getVisibility() != View.GONE) {
            mBtnVoiceRecord.setVisibility(View.GONE);
        }
        if (mBtnVoice != null) {
            mBtnVoice.setImageDrawable(mVoiceDrawable);
        }
        mShowVoice = false;
    }


    /**
     * ????????????
     */
    private void clickSend() {
        if (mEditText != null) {
            String content = mEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(content)) {
                if (mActionListener != null) {
                    mActionListener.onSendClick(content);
                }
            }
        }
    }

    /**
     * ????????????
     */
    private void clickVoice() {
        if (mShowVoice) {
            hideVoice();
            showKeyBoard();
        } else {
            showVoice();
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_voice) {
            clickVoice();
        } else if (i == R.id.btn_face) {
            clickFace();
        } else if (i == R.id.btn_more) {
            showMore();
        } else if (i == R.id.btn_send) {
            clickSend();
        }

    }

    /**
     * ????????????????????????
     */
    public void onKeyBoardHeightChanged(int keyboardHeight) {
        mShowKeyBoard = keyboardHeight > 100;
        if (mShowKeyBoard) {
            if (mShowFace) {
                hideFace();
            }
            if (mShowMore) {
                hideMore();
            }
        } else {
            if (mNeedShowFace) {
                mNeedShowFace = false;
                showFace();
            }
        }
        if (!mIsDialog) {
            mSpace.setPadding(0, 0, 0, keyboardHeight);
        }
    }


    /**
     * ????????????????????????
     */
    @Override
    public void onFaceClick(String str, int faceImageRes) {
        if (mEditText != null) {
            Editable editable = mEditText.getText();
            editable.insert(mEditText.getSelectionStart(), FaceTextUtil.getFaceImageSpan(str, faceImageRes));
        }
    }

    /**
     * ????????????????????????
     */
    @Override
    public void onFaceDeleteClick() {
        if (mEditText != null) {
            int selection = mEditText.getSelectionStart();
            String text = mEditText.getText().toString();
            if (selection > 0) {
                String text2 = text.substring(selection - 1, selection);
                if ("]".equals(text2)) {
                    int start = text.lastIndexOf("[", selection);
                    if (start >= 0) {
                        mEditText.getText().delete(start, selection);
                    } else {
                        mEditText.getText().delete(selection - 1, selection);
                    }
                } else {
                    mEditText.getText().delete(selection - 1, selection);
                }
            }
        }
    }

    /**
     * ???????????????????????????????????????
     */
    public boolean hideKeyBoardFaceMore() {
        boolean hasHide = false;
        if (mShowFace) {
            hideFace();
            hasHide = true;
        }
        if (mShowMore) {
            hideMore();
            hasHide = true;
        }
        if (mShowKeyBoard) {
            hideKeyBoard();
            hasHide = true;
        }
        return hasHide;
    }

    public void clearEditText() {
        if (mEditText != null) {
            mEditText.setText(null);
        }
    }

    public void setDefaultHint() {
        if (mEditText != null) {
            mEditText.setHint(mEditHintString);
        }
    }


    public void setEditHint(String hint) {
        if (mEditText != null) {
            mEditText.setHint(hint);
        }
    }


    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }

    @Override
    public void onDestroy() {
        mActionListener = null;
        super.onDestroy();
    }


    public static abstract class ActionListener {
        public abstract void onSendClick(String text);

        /**
         * ????????????
         */
        public void onVoiceRecordStart() {

        }

        /**
         * ????????????
         */
        public void onVoiceRecordEnd() {

        }

        /**
         * ????????????
         */
        public void onVoiceRecordCancel() {

        }


    }
}
