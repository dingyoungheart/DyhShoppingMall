package dyhshoppingmall.dyhshoppingmall.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.adapter.HomeFragmetAdapter;
import dyhshoppingmall.dyhshoppingmall.bean.ResultBeanData;
import okhttp3.Call;

import static dyhshoppingmall.dyhshoppingmall.common.Constants.HOME_URL;

/**
 * data:2017/4/24.
 * author:dingyonghui(lx)
 * function:
 */

public class FragmentHome extends BaseFragment {


    private View view;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView recyclerview_home;
    private ImageButton imagebutton_top;
    private ResultBeanData.ResultBean mResult;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.from(mContext).inflate(R.layout.fragment_home, null);
        tv_search_home= (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home= (TextView) view.findViewById(R.id.tv_message_home);
        recyclerview_home= (RecyclerView) view.findViewById(R.id.recyclerview_home);
        imagebutton_top= (ImageButton) view.findViewById(R.id.imagebutton_top);

        //给按钮设置点击监听事件
        initListerner();
        return view;
    }

    private void initListerner() {
        //回到顶部按钮监听
        imagebutton_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerview_home.scrollToPosition(0);//回到recyclerview的顶部 第一个position
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
            }
        });
        //搜索监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "输入搜索信息", Toast.LENGTH_SHORT).show();
            }
        });
        //查看消息监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "查看消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        String url=HOME_URL;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(mContext, "成功", Toast.LENGTH_SHORT).show();
                        //json解析数据
                        processprocessData(response);
                        Log.d("dyh",response);
                        HomeFragmetAdapter adapter=new HomeFragmetAdapter(mContext,mResult);
                        recyclerview_home.setAdapter(adapter);
                        recyclerview_home.setLayoutManager(new LinearLayoutManager(mContext));


                    }
                });

    }

    private void processprocessData(String jsonData) {
        ResultBeanData resultBeanData = JSON.parseObject(jsonData, ResultBeanData.class);
        mResult = resultBeanData.getResult();
        Log.d("dyh",resultBeanData.toString());

    }

}
