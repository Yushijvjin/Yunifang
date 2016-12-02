package com.bwie.xiaobing.yunifang.fragment;

import android.view.View;

import com.bwie.xiaobing.yunifang.R;
import com.bwie.xiaobing.yunifang.base.BaseFragment;
import com.bwie.xiaobing.yunifang.utils.CommonUtils;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public class MineFragment extends BaseFragment {
    @Override
    protected View createSuccessView() {

        View minefragment = CommonUtils.inflate(R.layout.minefragment);


        return minefragment;
    }

    @Override
    protected void onLoad() {

    }
}
