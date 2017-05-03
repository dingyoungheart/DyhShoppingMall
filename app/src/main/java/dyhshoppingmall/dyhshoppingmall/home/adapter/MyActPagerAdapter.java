package dyhshoppingmall.dyhshoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import dyhshoppingmall.dyhshoppingmall.utils.Constants;

/**
 * data:2017/4/27.
 * author:dingyonghui(lx)
 * function:活动模块的ViewPager的适配器
 */

class MyActPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<ResultBeanData.ResultBean.ActInfoBean> mAct_info;

    public MyActPagerAdapter(Context context, List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
        mContext = context;
        mAct_info = act_info;
    }

    @Override
    public int getCount() {
        return mAct_info.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(Constants.HOME_URL_IMAGE+mAct_info.get(position).getIcon_url())
                .into(imageView);

        //container 相当于ViewPager控件,必须给他添加进去 否则无法显示
        container.addView(imageView);

        return imageView;
    }

    //销毁视图的方法
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
