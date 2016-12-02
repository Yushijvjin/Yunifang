package com.bwie.xiaobing.yunifang.factory;

import android.support.v4.app.Fragment;

import com.bwie.xiaobing.yunifang.fragment.CartFragment;
import com.bwie.xiaobing.yunifang.fragment.CategoryFragment;
import com.bwie.xiaobing.yunifang.fragment.HomeFragment;
import com.bwie.xiaobing.yunifang.fragment.MineFragment;

import java.util.HashMap;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public class FragmentFactory {

    public static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            case 2:
                fragment = new CartFragment();
                break;

            case 3:
                fragment = new MineFragment();
                break;

        }

        fragmentMap.put(position,fragment);
        return  fragment;

    }


}
