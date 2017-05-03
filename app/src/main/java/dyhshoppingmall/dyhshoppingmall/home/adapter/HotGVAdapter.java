package dyhshoppingmall.dyhshoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import dyhshoppingmall.dyhshoppingmall.utils.Constants;

/**
 * data:2017/4/28.
 * author:dingyonghui(lx)
 * function:
 */

class HotGVAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.HotInfoBean> mHotInfoBeen;

    public HotGVAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        mContext = context;
        mHotInfoBeen = hot_info;
    }

    @Override
    public int getCount() {
        return mHotInfoBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotInfoBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewholder = new ViewHolder();
            viewholder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewholder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(Constants.HOME_URL_IMAGE + mHotInfoBeen.get(position).getFigure())
                .into(viewholder.iv_hot);
        viewholder.tv_name.setText(mHotInfoBeen.get(position).getName());
        viewholder.tv_price.setText("¥" + mHotInfoBeen.get(position).getCover_price());

        return convertView;
    }

    class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;


    }
}
