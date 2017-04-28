package dyhshoppingmall.dyhshoppingmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import dyhshoppingmall.dyhshoppingmall.common.Constants;
import dyhshoppingmall.dyhshoppingmall.holder.SeckillRecyclerViewHolder;
import dyhshoppingmall.dyhshoppingmall.utils.OnSeckillRecyclerViewListener;

/**
 * data:2017/4/27.
 * author:dingyonghui(lx)
 * function:秒杀模块下面的RecyclerView的适配器
 */

class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewHolder> {


    private final Context mContext;
    private final ResultBeanData.ResultBean.SeckillInfoBean mSeckill_info;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> mList;
    private SeckillRecyclerViewHolder mHolder;

    //创建接口的成员变量  (还有一种方法 是在Viewholder里写,然后在这里调用方法.)
    private OnSeckillRecyclerViewListener mOnSeckillRecyclerViewListener;
    //定义一个抽象方法 参数是接口
    public void setOnSeckillRecyclerViewListener(OnSeckillRecyclerViewListener onSeckillRecyclerViewListener) {
        mOnSeckillRecyclerViewListener = onSeckillRecyclerViewListener;
    }


    public SeckillRecyclerViewAdapter(Context context, ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
        mContext = context;
        mSeckill_info = seckill_info;
        mList = mSeckill_info.getList();
    }

    //加载Vieholder的布局
    @Override
    public SeckillRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mHolder = new SeckillRecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_seckill, null));
        return mHolder;
    }

    @Override
    public void onBindViewHolder(SeckillRecyclerViewHolder holder, final int position) {
        //设置图片
        Glide.with(mContext)
                .load(Constants.HOME_URL_IMAGE + mList.get(position).getFigure())
                .into(mHolder.mIv_dd);
        //设置价格
        mHolder.tv_price1.setText("¥" + mList.get(position).getCover_price());
        mHolder.tv_price2.setText("¥" + mList.get(position).getOrigin_price());

        //条目的点击事件 回调接口方法,传一个 position
        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSeckillRecyclerViewListener.OnItemClickListener(position);
            }
        });


    }

    //获取RecyclerView的条目个数
    @Override
    public int getItemCount() {
        return mList.size();
    }


}
