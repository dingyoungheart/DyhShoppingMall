package dyhshoppingmall.dyhshoppingmall.adapter;

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
import dyhshoppingmall.dyhshoppingmall.common.Constants;

/**
 * data:2017/4/28.
 * author:dingyonghui(lx)
 * function:
 */

class ComGVAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.RecommendInfoBean> mRecommendInfoBeen;

    public ComGVAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        mContext = context;
        mRecommendInfoBeen = recommend_info;
    }

    @Override
    public int getCount() {
        return mRecommendInfoBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecommendInfoBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            viewholder = new ViewHolder();
            viewholder.iv_recom = (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewholder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(Constants.HOME_URL_IMAGE + mRecommendInfoBeen.get(position).getFigure())
                .into(viewholder.iv_recom);
        viewholder.tv_name.setText(mRecommendInfoBeen.get(position).getName());
        viewholder.tv_price.setText("¥" + mRecommendInfoBeen.get(position).getCover_price());

        return convertView;
    }

    class ViewHolder {
        ImageView iv_recom;
        TextView tv_name;
        TextView tv_price;


    }
}
