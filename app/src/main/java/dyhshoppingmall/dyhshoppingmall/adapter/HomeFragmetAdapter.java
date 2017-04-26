package dyhshoppingmall.dyhshoppingmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

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

    //五个条目类型
    private static final int BANNER = 0;
    private static final int CHANNEL = 1;
    private static final int ACT = 2;
    private static final int SECKILL = 3;
    private static final int RECOMMEND = 4;
    private static final int HOT = 5;

    private final Context mContext;    //传参用的上下文
    private final ResultBeanData.ResultBean mResult;  //请求数据的resultBean类
    private final LayoutInflater mLayoutInflater;  //加载布局的容器

    public int currentType = BANNER;    //  设置一个默认的条目类型默认为BANNER


    public HomeFragmetAdapter(Context content, ResultBeanData.ResultBean result) {
        mContext = content;
        mResult = result;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //判断当前的条目类型,展示相应的数据界面
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext);
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item, null), mContext);
        }
        return null;
    }

    //判断当前的条目类型,创建对应的holder并给其设置数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mResult.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(mResult.getChannel_info());
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
        return 2;
    }

    /**
     *     条目类型1  Banner无限轮播的ViewHolder
     */
    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Banner banner;

        public BannerViewHolder(View itemView, Context context) {
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
            banner.setIndicatorGravity(BannerConfig.RIGHT); //设置指示器位置（当banner模式中有指示器时）
            //banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position"+position, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();//开启banner轮播器
        }
    }

    /**
     *  条目类型 2  频道类的ViewHolder
     */
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        public GridView gvChannel;
        public Context mContext;

        public ChannelViewHolder(View itemView, Context mContext) {
            super(itemView);
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);
            this.mContext = mContext;
        }
        //设置数据
        public void setData(final List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            // 给其中的GridView的适配器传值
            gvChannel.setAdapter(new ChannelAdapter(mContext, channel_info));
            //设置GridView的点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
