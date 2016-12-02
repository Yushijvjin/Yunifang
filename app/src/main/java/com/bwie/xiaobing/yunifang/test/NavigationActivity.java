package com.bwie.xiaobing.yunifang.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bwie.xiaobing.yunifang.R;
import com.bwie.xiaobing.yunifang.adapter.MyPagerAdapter;
import com.bwie.xiaobing.yunifang.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NavigationActivity extends AppCompatActivity {
    private TextView tv_navigation;

    private SharedPreferences sp;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if (msg.what == 0) {
                int aa = msg.arg1;
                tv_navigation.setText("" + aa);
                if (aa == 0) {
                    jump();
                }

            } else if (msg.what == 1) {
                jump();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getSharedPreferences("xiaobing", MODE_PRIVATE);
        boolean one = sp.getBoolean("one", true);
        if (one) {
            setContentView(R.layout.activity_navigation);
            ViewPager vp_dao = (ViewPager) findViewById(R.id.vp_dao);

            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("one", false);
            edit.commit();

            ArrayList<View> slist = new ArrayList<>();
            View view1 = CommonUtils.inflate(R.layout.page1);
            View view2 = CommonUtils.inflate(R.layout.page2);
            View view3 = CommonUtils.inflate(R.layout.page3);
            slist.add(view1);
            slist.add(view2);
            slist.add(view3);
            vp_dao.setAdapter(new MyPagerAdapter(slist));

            vp_dao.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                    new Thread() {

                        @Override
                        public void run() {
                            super.run();
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(1);

                        }
                    }.start();
                }
            });


        } else {
            setContentView(R.layout.navigation);
            tv_navigation = (TextView) findViewById(R.id.tv_navigation);

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int a = 3;

                @Override
                public void run() {

//                    Thread.sleep(1000);
                    Message m = Message.obtain();
                    m.arg1 = a;
                    a--;
                    handler.sendMessage(m);
                    handler.obtainMessage(0, m);

                }
            };
            timer.schedule(task, 1000, 1000);
        }
    }

    private void jump() {
        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
