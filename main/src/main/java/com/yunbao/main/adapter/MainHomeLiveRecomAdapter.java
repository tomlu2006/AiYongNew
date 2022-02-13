package com.yunbao.main.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunbao.common.adapter.RefreshAdapter;
import com.yunbao.common.glide.ImgLoader;
import com.yunbao.live.bean.LiveBean;
import com.yunbao.main.R;
import com.yunbao.main.utils.MainIconUtil;

import java.util.List;

/**
 * Created by cxf on 2018/9/26.
 * 首页 直播 推荐
 */

public class MainHomeLiveRecomAdapter extends RefreshAdapter<LiveBean> {

    private View.OnClickListener mOnClickListener;

    public MainHomeLiveRecomAdapter(Context context, List<LiveBean> list) {
        super(context, list);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canClick()) {
                    return;
                }
                int position = (int) v.getTag();
                mOnItemClickListener.onItemClick(mList.get(position), position);
            }
        };
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return -1;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == -1) {
            return new Vh(mInflater.inflate(R.layout.item_main_home_live_recom_0, parent, false));
        }
        return new Vh(mInflater.inflate(R.layout.item_main_home_live_recom, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        ((Vh) vh).setData(mList.get(position), position);
    }


    class Vh extends RecyclerView.ViewHolder {

        ImageView mCover;
        ImageView mAvatar;
        TextView mName;
        TextView mTitle;
        TextView mNum;
        ImageView mType;
        ImageView mImgGoodsIcon;

        public Vh(View itemView) {
            super(itemView);
            mCover = (ImageView) itemView.findViewById(R.id.cover);
            mAvatar = (ImageView) itemView.findViewById(R.id.avatar);
            mName = (TextView) itemView.findViewById(R.id.name);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mNum = (TextView) itemView.findViewById(R.id.num);
            mType = (ImageView) itemView.findViewById(R.id.type);
            mImgGoodsIcon = (ImageView) itemView.findViewById(R.id.img_goods_icon);
            itemView.setOnClickListener(mOnClickListener);
        }

        void setData(LiveBean bean, int position) {
            itemView.setTag(position);
            ImgLoader.display(mContext, bean.getThumb(), mCover);
            ImgLoader.display(mContext, bean.getAvatar(), mAvatar);
            mName.setText(bean.getUserNiceName());
            if (TextUtils.isEmpty(bean.getTitle())) {
                if (mTitle.getVisibility() == View.VISIBLE) {
                    mTitle.setVisibility(View.GONE);
                }
            } else {
                if (mTitle.getVisibility() != View.VISIBLE) {
                    mTitle.setVisibility(View.VISIBLE);
                }
                mTitle.setText(bean.getTitle());
            }

            if (mImgGoodsIcon != null) {
                if (bean.getIsshop() == 1) {
                    if (mImgGoodsIcon.getVisibility() != View.VISIBLE) {
                        mImgGoodsIcon.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (mImgGoodsIcon.getVisibility() == View.VISIBLE) {
                        mImgGoodsIcon.setVisibility(View.INVISIBLE);
                    }
                }
            }
            mNum.setText(bean.getNums());
            mType.setImageResource(MainIconUtil.getLiveTypeIcon(bean.getType()));
        }
    }

}
