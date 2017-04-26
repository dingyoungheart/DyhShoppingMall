package dyhshoppingmall.dyhshoppingmall.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyhshoppingmall.dyhshoppingmall.R;
import dyhshoppingmall.dyhshoppingmall.fragment.BaseFragment;
import dyhshoppingmall.dyhshoppingmall.fragment.FragmentCart;
import dyhshoppingmall.dyhshoppingmall.fragment.FragmentClassify;
import dyhshoppingmall.dyhshoppingmall.fragment.FragmentCom;
import dyhshoppingmall.dyhshoppingmall.fragment.FragmentHome;
import dyhshoppingmall.dyhshoppingmall.fragment.FragmentUser;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rd_home)
    RadioButton mRdHome;
    @Bind(R.id.rd_classify)
    RadioButton mRdClassify;
    @Bind(R.id.rg)
    RadioGroup mRg;
    @Bind(R.id.framelayout)
    FrameLayout mFramelayout;
    @Bind(R.id.rd_com)
    RadioButton mRdCom;
    @Bind(R.id.rd_cart)
    RadioButton mRdCart;
    @Bind(R.id.rd_user)
    RadioButton mRdUser;
    private List<BaseFragment> mFragments;
    private BaseFragment mBaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //去除title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置默认选中主页的按钮
        mRg.check(R.id.rd_home);
        addFragment();           //先调用添加fragment的方法
        ReplaceFragment(mFragments.get(0));  //默认选择 首页的fragment

    }

    //    添加fragment的方法
    private void addFragment() {
        //定义一个 泛型为 BaseFragment 的集合
        mFragments = new ArrayList<>();
        mFragments.add(new FragmentHome());
        mFragments.add(new FragmentClassify());
        mFragments.add(new FragmentCom());
        mFragments.add(new FragmentCart());
        mFragments.add(new FragmentUser());
    }


    @OnClick({R.id.rd_home, R.id.rd_classify, R.id.rd_com, R.id.rd_cart, R.id.rd_user})
    public void onViewClicked(View view) {
        int position = 0;    //定义一个变量 设置每个按钮的值
        switch (view.getId()) {
            case R.id.rd_home:
                position = 0;
                break;
            case R.id.rd_classify:
                position = 1;
                break;
            case R.id.rd_com:
                position = 2;
                break;
            case R.id.rd_cart:
                position = 3;
                break;
            case R.id.rd_user:
                position = 4;
                break;
        }
        BaseFragment fragment = mFragments.get(position);
        ReplaceFragment(fragment);
    }

    private void ReplaceFragment(BaseFragment baseFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启事务
        if (mBaseFragment != null) {//判断如果 上面定义的mBaseFragment 不为空,就将它隐藏
            transaction.hide(mBaseFragment);
        }
        if (!baseFragment.isAdded()) { //如果参数的 baseFragment没有被添加,则将其添加
            transaction.add(R.id.framelayout, baseFragment);
        }
        transaction.show(baseFragment);// 显示 参数的 baseFragment
        transaction.commit();
        mBaseFragment = baseFragment;

    }


}
