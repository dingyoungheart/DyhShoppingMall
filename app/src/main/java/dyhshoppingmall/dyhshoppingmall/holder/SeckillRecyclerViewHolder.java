package dyhshoppingmall.dyhshoppingmall.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dyhshoppingmall.dyhshoppingmall.R;

/**
 * data:2017/4/27.
 * author:dingyonghui(lx)
 * function:秒杀模块 子RecyclerView的Viewholder
 */

public class SeckillRecyclerViewHolder extends RecyclerView.ViewHolder {

    public final ImageView mIv_dd;
    public final TextView tv_price1;
    public final TextView tv_price2;


    public SeckillRecyclerViewHolder(View itemView) {
        super(itemView);
        mIv_dd = (ImageView) itemView.findViewById(R.id.iv_dd);
        tv_price1 = (TextView) itemView.findViewById(R.id.tv_price1);
        tv_price2 = (TextView) itemView.findViewById(R.id.tv_price2);



    }
}
