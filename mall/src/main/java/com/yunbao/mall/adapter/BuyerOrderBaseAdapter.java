package com.yunbao.mall.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunbao.common.adapter.RefreshAdapter;
import com.yunbao.common.glide.ImgLoader;
import com.yunbao.common.utils.StringUtil;
import com.yunbao.common.utils.WordUtil;
import com.yunbao.mall.R;
import com.yunbao.mall.bean.BuyerOrderBean;

public abstract class BuyerOrderBaseAdapter extends RefreshAdapter<BuyerOrderBean> {

    private String mTotalTipString;
    private String mMoneySymbol;
    private View.OnClickListener mShopClickListener;
    private View.OnClickListener mItemClickListener;
    protected ActionListener mActionListener;

    public BuyerOrderBaseAdapter(Context context) {
        super(context);
        mTotalTipString = WordUtil.getString(R.string.mall_195);
        mMoneySymbol = WordUtil.getString(R.string.money_symbol);
        mShopClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if (tag != null && mActionListener != null) {
                    mActionListener.onShopClick((BuyerOrderBean) tag);
                }
            }
        };
        mItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if (tag != null && mActionListener != null) {
                    mActionListener.onItemClick((BuyerOrderBean) tag);
                }
            }
        };
    }

    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }


    public class BaseVh extends RecyclerView.ViewHolder {

        TextView mShopName;
        TextView mStatusTip;
        ImageView mGoodsThumb;
        TextView mGoodsName;
        TextView mGoodsPrice;
        TextView mGoodsSpecName;
        TextView mGoodsNum;
        TextView mTotalTip;
        TextView mTotalPrice;

        public BaseVh(@NonNull View itemView) {
            super(itemView);
            mShopName = itemView.findViewById(R.id.shop_name);
            mStatusTip = itemView.findViewById(R.id.status_tip);
            mGoodsThumb = itemView.findViewById(R.id.goods_thumb);
            mGoodsName = itemView.findViewById(R.id.goods_name);
            mGoodsPrice = itemView.findViewById(R.id.goods_price);
            mGoodsSpecName = itemView.findViewById(R.id.goods_spec_name);
            mGoodsNum = itemView.findViewById(R.id.goods_num);
            mTotalTip = itemView.findViewById(R.id.total_tip);
            mTotalPrice = itemView.findViewById(R.id.total_price);
            mShopName.setOnClickListener(mShopClickListener);
            itemView.setOnClickListener(mItemClickListener);
        }

        public void setData(BuyerOrderBean bean) {
            mShopName.setTag(bean);
            itemView.setTag(bean);
            mShopName.setText(bean.getShopName());
            mStatusTip.setText(bean.getStatusTip());
            ImgLoader.display(mContext, bean.getGoodsSpecThumb(), mGoodsThumb);
            mGoodsName.setText(bean.getGoodsName());
            mGoodsPrice.setText(StringUtil.contact(mMoneySymbol, bean.getGoodsPrice()));
            mGoodsSpecName.setText(bean.getGoodsSpecName());
            mGoodsNum.setText(StringUtil.contact("x", bean.getGoodsNum()));
            mTotalTip.setText(String.format(mTotalTipString, bean.getGoodsNum()));
            mTotalPrice.setText(StringUtil.contact(mMoneySymbol, bean.getTotalPrice()));
        }
    }

    public interface ActionListener {
        /**
         * ??????????????????
         */
        void onShopClick(BuyerOrderBean bean);

        /**
         * ??????item
         */
        void onItemClick(BuyerOrderBean bean);

        /**
         * ????????????
         */
        void onCancelOrderClick(BuyerOrderBean bean);

        /**
         * ??????
         */
        void onPayClick(BuyerOrderBean bean);

        /**
         * ????????????
         */
        void onDeleteClick(BuyerOrderBean bean);

        /**
         * ????????????
         */
        void onWuLiuClick(BuyerOrderBean bean);

        /**
         * ????????????
         */
        void onConfirmClick(BuyerOrderBean bean);

        /**
         * ??????
         */
        void onCommentClick(BuyerOrderBean bean);

        /**
         * ????????????
         */
        void onAppendCommentClick(BuyerOrderBean bean);

        /**
         * ????????????
         */
        void onRefundClick(BuyerOrderBean bean);

    }

}
