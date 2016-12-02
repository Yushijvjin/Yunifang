package com.bwie.xiaobing.yunifang.fragment;

import android.view.View;

import com.bwie.xiaobing.yunifang.base.BaseFragment;
import com.bwie.xiaobing.yunifang.view.ShowingPage;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public class CategoryFragment extends BaseFragment {
    @Override
    protected View createSuccessView() {
        return null;
    }

    @Override
    protected void onLoad() {
        CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

    }
}
