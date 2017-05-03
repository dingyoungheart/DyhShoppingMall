package dyhshoppingmall.dyhshoppingmall.shoppingcart.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dyhshoppingmall.dyhshoppingmall.R;

/**
 * data:2017/5/2.
 * author:dingyonghui(lx)
 * function:
 */

public class CustomShoppingButton extends LinearLayout implements View.OnClickListener {

    private TextView mTv_value;
    private ImageView mIv_add;
    private ImageView mIv_sub;

    //当前数量值,默认为1,设置对此值获取,设置的方法
    private int value = 1;
    //最大商品数量值
    private int minValue = 1;
    //最小商品数量值
    private int maxVaule = 5;

    public int getValue() {
        String trim = mTv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            value = Integer.valueOf(trim);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        mTv_value.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxVaule() {
        return maxVaule;
    }

    public void setMaxVaule(int maxVaule) {
        this.maxVaule = maxVaule;
    }

    public CustomShoppingButton(Context context) {
        super(context);
    }

    public CustomShoppingButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.goods_custumbutton, this);
        mIv_sub = (ImageView) view.findViewById(R.id.iv_sub);
        mIv_add = (ImageView) view.findViewById(R.id.iv_add);
        mTv_value = (TextView) view.findViewById(R.id.tv_value);

        //设置点击事件
        mIv_add.setOnClickListener(this);
        mIv_sub.setOnClickListener(this);

        //获得value值
        int value = getValue();
        //设置value的值
        setValue(value);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sub:
                subValue();
                break;

            case R.id.iv_add:
                addValue();
                break;
        }
    }

    private void subValue() {
        if (value>minValue){
            value--;
        }
        setValue(value);
        mOnValueChangeListener.onValueChange(value);
    }

    private void addValue() {
        if (value<maxVaule){
            value++;
        }
        setValue(value);
        mOnValueChangeListener.onValueChange(value);
    }

    public interface OnValueChangeListener{
        void onValueChange(int value);
    }

    private OnValueChangeListener mOnValueChangeListener;

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener){
        mOnValueChangeListener = onValueChangeListener;
    }


}
