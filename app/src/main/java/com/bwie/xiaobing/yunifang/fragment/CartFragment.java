package com.bwie.xiaobing.yunifang.fragment;

import android.view.View;
import android.widget.TextView;

import com.bwie.xiaobing.yunifang.base.BaseFragment;
import com.bwie.xiaobing.yunifang.view.ShowingPage;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public class CartFragment extends BaseFragment {

    private String s;

    @Override
    protected View createSuccessView() {

        TextView textView = new TextView(getContext());
        textView.setText(s);
        return textView;
    }

    @Override
    protected void onLoad() {

        RequestParams re = new RequestParams("http://www.baidu.com");
        x.http().get(re, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                CartFragment.this.s = s;
                CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);

            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
