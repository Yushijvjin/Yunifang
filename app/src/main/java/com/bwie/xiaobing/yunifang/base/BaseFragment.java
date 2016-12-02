package com.bwie.xiaobing.yunifang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.xiaobing.yunifang.view.ShowingPage;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public abstract class BaseFragment extends Fragment {

    private ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showingPage = new ShowingPage(getContext()) {

            @Override
            protected void onload() {

            }

            @Override
            public View createSuccessView() {

                return BaseFragment.this.createSuccessView();
            }
        };
        BaseFragment.this.onLoad();
        return showingPage;
    }

    protected abstract View createSuccessView();

    protected abstract void onLoad();

    public void showCurrentPage(ShowingPage.StateType stateType) {
        if (showingPage != null) {

            showingPage.showCurrentPage(stateType);
        }


    }


}
