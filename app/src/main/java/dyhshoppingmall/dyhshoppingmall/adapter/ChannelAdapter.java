package dyhshoppingmall.dyhshoppingmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import dyhshoppingmall.dyhshoppingmall.common.Constants;

/**
 * data:2017/4/26.
 * author:dingyonghui(lx)
 * function:
 */

class ChannelAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ChannelInfoBean> mChannel_info;

    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        mContext = context;
        mChannel_info = channel_info;
    }

    @Override
    public int getCount() {
        return mChannel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return mChannel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String imageUrl = mChannel_info.get(position).getImage();
        String url=Constants.HOME_URL_IMAGE+imageUrl;
        Glide.with(mContext)
                .load(url)
                .into(viewHolder.mIvChannel);
        viewHolder.mTvChannel.setText(mChannel_info.get(position).getChannel_name());

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.iv_channel)
        ImageView mIvChannel;
        @Bind(R.id.tv_channel)
        TextView mTvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
