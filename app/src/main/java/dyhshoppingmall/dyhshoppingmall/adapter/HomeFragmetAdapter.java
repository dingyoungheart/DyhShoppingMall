package dyhshoppingmall.dyhshoppingmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import dyhshoppingmall.dyhshoppingmall.common.Constants;
import dyhshoppingmall.dyhshoppingmall.utils.GlideImageLoader;

/**
 * data:2017/4/25.
 * author:dingyonghui(lx)
 * function:
 */

public class HomeFragmetAdapter extends RecyclerView.Adapter {

    private static final int BANNER = 0;
    private static final int CHANNEL = 1;
    private static final int ACT = 2;
    private static final int SECKILL = 3;
    private static final int RECOMMEND = 4;
    private static final int HOT = 5;
    private final Context mContext;
    private final ResultBeanData.ResultBean mResult;
    private final LayoutInflater mLayoutInflater;

    public int currentType = BANNER;


    public HomeFragmetAdapter(Context content, ResultBeanData.ResultBean result) {
        mContext = content;
        mResult = result;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext, mResult);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mResult.getBanner_info());
        }
    }

    //根据position判断条目类型 返回条目类型
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    // 返回总条目类型
    @Override
    public int getItemCount() {
        return 1;
    }


    //  条目类型1  Banner无限轮播的ViewHolder
    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Banner banner;

        public BannerViewHolder(View itemView, Context context, ResultBeanData.ResultBean resultBean) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {

            //定义一个集合来存储 图片地址
            List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < mResult.getBanner_info().size(); i++) {
                imageUris.add(Constants.HOME_URL_IMAGE + mResult.getBanner_info().get(i).getImage());
                Log.d("img", imageUris.toString());
            }
            banner.setImageLoader(new GlideImageLoader()); //设置图片加载器
            banner.setImages(imageUris);//设置图片集合
            banner.setBannerAnimation(Transformer.DepthPage);  //设置banner动画效果
            banner.isAutoPlay(true);//设置自动轮播，默认为true
            banner.setDelayTime(1500); //设置轮播时间
            banner.setIndicatorGravity(BannerConfig.CENTER); //设置指示器位置（当banner模式中有指示器时）
            banner.start();
        }
    }
}
