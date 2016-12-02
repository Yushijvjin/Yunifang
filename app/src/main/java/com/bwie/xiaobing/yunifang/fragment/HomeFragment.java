package com.bwie.xiaobing.yunifang.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.xiaobing.yunifang.R;
import com.bwie.xiaobing.yunifang.base.BaseData;
import com.bwie.xiaobing.yunifang.base.BaseFragment;
import com.bwie.xiaobing.yunifang.bean.HomeRoot;
import com.bwie.xiaobing.yunifang.utils.CommonUtils;
import com.bwie.xiaobing.yunifang.utils.URLUtils;
import com.bwie.xiaobing.yunifang.view.MyRoolViewPager;
import com.bwie.xiaobing.yunifang.view.ShowingPage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public class HomeFragment extends BaseFragment {

    public String data;
    private MyHomeData myHomeData;
    private HomeRoot homeRoot;

    int[] dotArray = new int[]{R.mipmap.page_indicator_focused, R.mipmap.page_indicator_unfocused};

    ArrayList<String> imgUrlList = new ArrayList<>();
    ArrayList<ImageView> dotList = new ArrayList<>();
    private LinearLayout ll_dots;
    private MyRoolViewPager myRoolViewPager;

    @Override
    protected void onLoad() {


        myHomeData = new MyHomeData();
        myHomeData.getData(URLUtils.homeUrl, URLUtils.homeArgs, 0, BaseData.NORMALTIME);


    }

    @Override
    protected View createSuccessView() {

        View view = initView();

        initRoolViewPager();

        return view;
    }

    @NonNull
    private View initView() {
        View view = CommonUtils.inflate(R.layout.fragment_home);
        ll_dots = (LinearLayout) view.findViewById(R.id.ll_dots);
        myRoolViewPager = (MyRoolViewPager) view.findViewById(R.id.myRoolViewPager);
        return view;
    }

    private void initRoolViewPager() {
        List<HomeRoot.DataBean.Ad1Bean> ad1 = homeRoot.getData().getAd1();
        for (int i = 0; i < ad1.size(); i++) {
            imgUrlList.add(ad1.get(i).getImage());
        }
        initDots(ad1);
        myRoolViewPager.initData(imgUrlList, dotArray,dotList);
        myRoolViewPager.startViewPager();
        myRoolViewPager.setOnPageClickListener(new MyRoolViewPager.OnPageClickListener() {
            @Override
            public void setOnPage(int position) {
//                Integer integer = new Integer();

                Toast.makeText(getContext(),"我要跳转到详情了",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initDots(List<HomeRoot.DataBean.Ad1Bean> ad1) {
        dotList.clear();

        for (int i = 0; i < ad1.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            if (i == 0) {
                imageView.setImageResource(dotArray[0]);
            } else {
                imageView.setImageResource(dotArray[1]);
            }
            dotList.add(imageView);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(CommonUtils.dip2px(10), CommonUtils.dip2px(5), CommonUtils.dip2px(10), CommonUtils.dip2px(5));
            ll_dots.addView(imageView, params);


        }
    }

    class MyHomeData extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            HomeFragment.this.data = data;
            //data有可能为空

            Gson gson = new Gson();
            homeRoot = gson.fromJson(data, HomeRoot.class);


            //再展示
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

        }


    }


}
