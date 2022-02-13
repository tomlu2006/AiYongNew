package com.yunbao.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunbao.common.Constants;
import com.yunbao.common.glide.ImgLoader;
import com.yunbao.main.R;
import com.yunbao.main.bean.RecommendBean;

import java.util.List;

/**
 * Created by cxf on 2017/10/23.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.Vh> {

    private Context mContext;
    private List<RecommendBean> mList;
    private LayoutInflater mInflater;
    private View.OnClickListener mOnClickListener;
    private Drawable mCheckedDrawable;
    private Drawable mUnCheckedDrawable;

    public RecommendAdapter(Context context, List<RecommendBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if (tag != null) {
                    int position = (int) tag;
                    RecommendBean bean = mList.get(position);
                    bean.toggleChecked();
                    notifyItemChanged(position, Constants.PAYLOAD);
                }
            }
        };
        mCheckedDrawable = ContextCompat.getDrawable(context, R.mipmap.icon_recommend_1);
        mUnCheckedDrawable = ContextCompat.getDrawable(context, R.mipmap.icon_recommend_0);
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(mInflater.inflate(R.layout.item_recommend, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position, @NonNull List<Object> payloads) {
        Object payload = payloads.size() > 0 ? payloads.get(0) : null;
        vh.setData(mList.get(position), position, payload);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Vh extends RecyclerView.ViewHolder {

        ImageView mAvatar;
        ImageView mRadioButton;
        TextView mName;
        TextView mFans;

        public Vh(View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.avatar);
            mRadioButton = itemView.findViewById(R.id.radioButton);
            mName = itemView.findViewById(R.id.name);
            mFans = itemView.findViewById(R.id.fans);
            itemView.setOnClickListener(mOnClickListener);
        }

        void setData(RecommendBean bean, int position, Object payload) {
            itemView.setTag(position);
            if (payload == null) {
                ImgLoader.displayAvatar(mContext, bean.getAvatar(), mAvatar);
                mName.setText(bean.getUserNiceName());
                mFans.setText(bean.getFans());
            }
            mRadioButton.setImageDrawable(bean.isChecked() ? mCheckedDrawable : mUnCheckedDrawable);
        }
    }

    public String getCheckedUid() {
        if (mList == null || mList.size() == 0) {
            return "";
        }
        StringBuilder sb = null;
        for (RecommendBean bean : mList) {
            if (bean.isChecked()) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(bean.getId());
                sb.append(",");
            }
        }
        if (sb == null) {
            return "";
        }
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
