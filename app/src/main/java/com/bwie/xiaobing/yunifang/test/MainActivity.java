package com.bwie.xiaobing.yunifang.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.xiaobing.yunifang.R;
import com.bwie.xiaobing.yunifang.factory.FragmentFactory;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private RadioGroup rg_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return FragmentFactory.getFragment(position);

            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < rg_main.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) rg_main.getChildAt(i);
                    if (position == i) {
                        rb.setTextColor(Color.RED);

                    } else {
                        rb.setTextColor(Color.GRAY);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {



            }
        });

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home_page:

                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.rb_cagetory:
//                        RadioButton one = (RadioButton) group.getChildAt(1);
//                        one.setTextColor(Color.RED);
                        viewPager.setCurrentItem(1);

                        break;
                    case R.id.rb_cart:

                        viewPager.setCurrentItem(2);

                        break;
                    case R.id.rb_mine:

                        viewPager.setCurrentItem(3);

                        break;


                }
            }
        });

    }
}
