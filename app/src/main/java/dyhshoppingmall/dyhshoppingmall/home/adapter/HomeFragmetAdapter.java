package dyhshoppingmall.dyhshoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.magicviewpager.transformer.RotateUpPageTransformer;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.activity.GoodsInfoActivity;
import dyhshoppingmall.dyhshoppingmall.bean.GoodsBean;
import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import dyhshoppingmall.dyhshoppingmall.home.utils.OnSeckillRecyclerViewListener;
import dyhshoppingmall.dyhshoppingmall.utils.Constants;
import dyhshoppingmall.dyhshoppingmall.utils.GlideImageLoader;


/**
 * data:2017/4/25.
 * author:dingyonghui(lx)
 * function:首页的整个框架的RecyclerView的适配器
 * 加载多种条目
 */

public class HomeFragmetAdapter extends RecyclerView.Adapter {

    public static final String GOODS_BEAN = "goods_bean";

    private static final int BANNER = 0;//banner 广告
    private static final int CHANNEL = 1;//频道
    private static final int ACT = 2;//活动
    private static final int SECKILL = 3;//秒杀
    private static final int RECOMMEND = 4;//推荐
    private static final int HOT = 5;//热卖

    private Context mContext;    //传参用的上下文
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
        } else if (viewType == ACT) {
            return new ActViewHolder(mLayoutInflater.inflate(R.layout.act_item, null), mContext);
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item, null), mContext);
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item, null), mContext);
        } else if (viewType == HOT) {
            return new HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item, null), mContext);
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
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(mResult.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(mResult.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder channelViewHolder = (RecommendViewHolder) holder;
            channelViewHolder.setData(mResult.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder channelViewHolder = (HotViewHolder) holder;
            channelViewHolder.setData(mResult.getHot_info());
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
        return 6;
    }

    /**
     * 条目类型1  Banner无限轮播的ViewHolder
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
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();//开启banner轮播器
        }
    }

    /**
     * 条目类型 2  频道类的ViewHolder
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

    /**
     * 条目类型 3  活动类的ViewHolder
     */
    class ActViewHolder extends RecyclerView.ViewHolder {

        private final ViewPager act_vp;
        private final Context mContext;

        public ActViewHolder(View itemView, Context context) {
            super(itemView);
            act_vp = (ViewPager) itemView.findViewById(R.id.act_vp);
            mContext = context;
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_vp.setOffscreenPageLimit(3);
            act_vp.setPageMargin(20);
            act_vp.setAdapter(new MyActPagerAdapter(mContext, act_info));
            //设置动画效果
//            act_vp.setPageTransformer(true, new RotateDownPageTransformer());
            act_vp.setPageTransformer(true, new RotateUpPageTransformer());
            /**
             * 目前可选动画
             AlphaPageTransformer
             RotateDownPageTransformer
             RotateUpPageTransformer
             RotateYTransformer
             NonPageTransformer
             ScaleInTransformer
             动画间可以自由组合，例如：
             mViewPager.setPageTransformer(true,new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));

             */

        }

    }

    /**
     * 条目类型 4  秒杀
     */
    //定义一个handler来实现倒计时模块
    private int dt;
    private TextView mSeckill_tv_time;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                dt = dt - 1000;//循环减1秒
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//设置24小时式的时间类型
                mSeckill_tv_time.setText(format.format(new Date(dt)));
                handler.sendEmptyMessageDelayed(0, 1000);//发送一个消息
                if (dt == 0) {//当倒计时为0的时候,把消息移除掉
                    handler.removeMessages(0);
                }
            }
        }
    };

    class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final TextView mSeckill_tv_more;
        private final RecyclerView seckill_rv;

        public SeckillViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            mSeckill_tv_time = (TextView) itemView.findViewById(R.id.seckill_tv_time);
            mSeckill_tv_more = (TextView) itemView.findViewById(R.id.seckill_tv_more);
            seckill_rv = (RecyclerView) itemView.findViewById(R.id.seckill_rv);
        }


        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //获取倒计时的时间
            dt = Integer.parseInt(seckill_info.getEnd_time()) - Integer.parseInt(seckill_info.getStart_time());
            Log.d("dt", "" + dt);
            //每隔一秒发送一个消息到handler
            handler.sendEmptyMessageDelayed(0, 1000);

            //定义一个适配器
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info);
            seckill_rv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            seckill_rv.setAdapter(adapter);

            //RecyclerView的点击事件 回调 子adapter中定义的 OnSeckillRecyclerViewListener 接口的方法
            adapter.setOnSeckillRecyclerViewListener(new OnSeckillRecyclerViewListener() {
                @Override
                public void OnItemClickListener(int position) {
//                    Toast.makeText(mContext, "Biu点击了☆" + position, Toast.LENGTH_SHORT).show();
                    String cover_price = seckill_info.getList().get(position).getCover_price();
                    String name = seckill_info.getList().get(position).getName();
                    String figure = seckill_info.getList().get(position).getFigure();
                    String product_id = seckill_info.getList().get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                    EventBus.getDefault().postSticky(goodsBean);

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
//                    intent.putExtra(GOODS_BEAN, goodsBean);
                    mContext.startActivity(intent);//跳转商品详情页
                }
            });

        }


    }

    /**
     * 条目类型 5  推荐
     */
    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final GridView mGv_recom;
        private final TextView mRecom_tv_more;

        //绑定控件
        public RecommendViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            mRecom_tv_more = (TextView) itemView.findViewById(R.id.recom_tv_more);
            mGv_recom = (GridView) itemView.findViewById(R.id.gv_recom);
        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            ComGVAdapter adapter = new ComGVAdapter(mContext, recommend_info);
            mGv_recom.setAdapter(adapter);
            mGv_recom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "Biu点击了条目" + position, Toast.LENGTH_SHORT).show();
                    String cover_price = recommend_info.get(position).getCover_price();
                    String name = recommend_info.get(position).getName();
                    String figure = recommend_info.get(position).getFigure();
                    String product_id = recommend_info.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);

                    EventBus.getDefault().postSticky(goodsBean);

//                    intent.putExtra(GOODS_BEAN, goodsBean);
                    mContext.startActivity(intent);//跳转商品详情页
                }
            });
        }
    }

    /**
     * 条目类型 6  热卖
     */
    class HotViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final GridView gv_hot;
        private final TextView mTv_more_hot;

        //绑定控件
        public HotViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            mTv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            HotGVAdapter adapter = new HotGVAdapter(mContext, hot_info);
            gv_hot.setAdapter(adapter);
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "Biling点击了条目" + position, Toast.LENGTH_SHORT).show();
                    String cover_price = hot_info.get(position).getCover_price();
                    String name = hot_info.get(position).getName();
                    String figure = hot_info.get(position).getFigure();
                    String product_id = hot_info.get(position).getProduct_id();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    EventBus.getDefault().postSticky(goodsBean);

//                    intent.putExtra(GOODS_BEAN, goodsBean);
                    mContext.startActivity(intent);//跳转商品详情页
                }
            });
        }
    }


}
