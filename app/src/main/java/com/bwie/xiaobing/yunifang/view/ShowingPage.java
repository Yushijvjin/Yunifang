package com.bwie.xiaobing.yunifang.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bwie.xiaobing.yunifang.R;
import com.bwie.xiaobing.yunifang.utils.CommonUtils;

/**
 * Created by 李小兵 on 2016/11/28.
 */
public abstract class ShowingPage extends FrameLayout implements View.OnClickListener {
    public static final int STATE_UNLOAD = 1;
    public static final int STATE_LOAD = 2;
    public static final int STATE_LOAD_ERROR = 3;
    public static final int STATE_LOAD_EMPTY = 4;
    public static final int STATE_LOAD_SUCCESS = 5;

    public int currentState = STATE_UNLOAD;
    private View showingpage_load_empty;
    private View showingpage_loading;
    private View showingpage_unload;
    private View showingpage_load_error;
    private LayoutParams layoutParams;

    private View showingpage_success;
    /**
     * 重新加载
     */
    private Button bt_reload;

    public ShowingPage(Context context) {
        super(context);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

//初始化
        if (showingpage_load_empty == null) {
            showingpage_load_empty = CommonUtils.inflate(R.layout.showingpage_load_empty);
            this.addView(showingpage_load_empty, layoutParams);

        }
        //初始化正在加载，加入到布局中
        if (showingpage_loading == null) {
            showingpage_loading = CommonUtils.inflate(R.layout.showingpage_loading);
            this.addView(showingpage_loading, layoutParams);

        }
        if (showingpage_unload == null) {
            showingpage_unload = CommonUtils.inflate(R.layout.showingpage_unload);
            this.addView(showingpage_unload, layoutParams);

        }
        if (showingpage_load_error == null) {
            showingpage_load_error = CommonUtils.inflate(R.layout.showingpage_load_error);

            bt_reload = (Button) showingpage_load_error.findViewById(R.id.bt_reload);
            bt_reload.setOnClickListener(this);
            this.addView(showingpage_load_error, layoutParams);
        }

        showPage();
//        onload();
    }

    //提供一个请求结束之后，设置当前状态，展示界面的方法  6
    public void showCurrentPage(StateType stateType) {
        //获取枚举中的数字,并赋值给当前类型
        this.currentState = stateType.getCurrentState();
        //展示一下
        showPage();
    }

    protected abstract void onload();

    //展示界面
    public void showPage() {
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                showUIPage();
            }
        });
    }

    public void showUIPage() {
        //根据当前转态进行展示
        if (showingpage_unload != null) {
            showingpage_unload.setVisibility(currentState == STATE_UNLOAD ? View.VISIBLE : View.GONE);

        }
        if (showingpage_loading != null) {
            showingpage_loading.setVisibility(currentState == STATE_LOAD ? View.VISIBLE : View.GONE);

        }
        if (showingpage_load_empty != null) {
            showingpage_load_empty.setVisibility(currentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);

        }
        if (showingpage_load_error != null) {
            showingpage_load_error.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);

        }

        //成功的状态没有成功的页面
        if (showingpage_success == null && currentState == STATE_LOAD_SUCCESS) {
            //加载成功的页面-添加到当前的界面
            showingpage_success = createSuccessView();
            this.addView(showingpage_success, layoutParams);

        }
        if (showingpage_success != null) {
            showingpage_success.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);

        }
    }
    /**
     * 创建请求成功的界面
     *
     * @return
     */
    public abstract View createSuccessView();


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_reload:
                resetView();
                break;
        }
    }
    /**
     * 重置
     */


    private void resetView() {
        //重新加载
        //1--重置状态
        if (currentState != STATE_UNLOAD) {
            currentState = STATE_UNLOAD;
        }
        //2展示界面效果
        showPage();
        //3重新加载
        onload();
    }


    /**
     * 枚举类
     */
    public enum StateType {
        //请求类型
        STATE_LOAD_ERROR(3), STATE_LOAD_EMPTY(4), STATE_LOAD_SUCCESS(5);
        private final int currentState;

        StateType(int currentState) {
            this.currentState = currentState;
        }

        public int getCurrentState() {
            return currentState;
        }
    }
}
