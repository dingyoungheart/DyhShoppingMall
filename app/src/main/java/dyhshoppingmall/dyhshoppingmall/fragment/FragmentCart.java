package dyhshoppingmall.dyhshoppingmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dyhshoppingmall.dyhshoppingmall.R;

/**
 * data:2017/4/24.
 * author:dingyonghui(lx)
 * function:
 */

public class FragmentCart extends BaseFragment{


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.from(mContext).inflate(R.layout.fragment_cart,null);
        return view;
    }

    @Override
    protected void initData() {

    }
}
