package com.bwie.xiaobing.yunifang.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 李小兵 on 2016/11/30.
 */
public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<View> slist;

    public MyPagerAdapter(ArrayList<View> slist) {
        this.slist = slist;
    }

    @Override
    public int getCount() {
        return slist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView(slist.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(slist.get(position), 0);//添加页卡
        return slist.get(position);
    }
}
