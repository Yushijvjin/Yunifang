package com.bwie.xiaobing.yunifang.base;

import android.text.TextUtils;

import com.bwie.xiaobing.yunifang.utils.CommonUtils;
import com.bwie.xiaobing.yunifang.utils.MD5Encoder;
import com.bwie.xiaobing.yunifang.view.ShowingPage;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 李小兵 on 2016/11/29.
 */
public abstract class BaseDataXutils {

    private final File fileDir;
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;

    //缓存数据存储到哪里
    public BaseDataXutils() {
        File cacheDir = CommonUtils.getContext().getCacheDir();

        fileDir = new File(cacheDir, "yunifang");
        if (!fileDir.exists()) {
            fileDir.mkdir();

        }


    }

    /**
     * @param path      请求地址
     * @param args      请求参数
     * @param index     页码索引
     * @param validTime 有效时间
     */

    public void getData(String path, String args, int index, int validTime) {
//先判断有效时间
        if (validTime == 0) {
            //直接获取最新数据
            getDataFromNet(path, args, index, validTime);
        } else {
            //直接从本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (!TextUtils.isEmpty(data)) {

//如果为空，请求网络
                getDataFromNet(path, args, index, validTime);
            } else {
                //拿到了数据返回数据
                setResultData(data);
            }
        }
    }

    /**
     * @param path      请求地址
     * @param index     页码索引
     * @param validTime 有效时间
     */

    public void postData(String path, HashMap<String, String> argsMap, int index, int validTime) {
//先判断有效时间
        if (validTime == 0) {
            //直接获取最新数据
            postDataFromNet(path, argsMap, index, validTime);
        } else {
            //直接从本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (!TextUtils.isEmpty(data)) {

//如果为空，请求网络
                postDataFromNet(path, argsMap, index, validTime);
            } else {
                //拿到了数据返回数据
                setResultData(data);
            }
        }
    }

    private void postDataFromNet(String path, HashMap<String, String> argsMap, int index, int validTime) {
        RequestParams requestParams = new RequestParams(path);
        Set<Map.Entry<String, String>> entries = argsMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());


        }

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

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


    public abstract void setResultData(String data);

    private String getDataFromLocal(String path, int index, int validTime) {

        //读取文件信息
//读取时间
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            long time = Long.parseLong(s);
            if (System.currentTimeMillis() < time) {

                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                bufferedReader.close();
                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 从网络获取数据
     *
     * @param path
     * @param args
     * @param index
     * @param validTime
     */
    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {
        RequestParams requestParams = new RequestParams(path + "?" + args);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
//如果请求成功，将数据返回
                setResultData(s);
                //缓存到本地
                writeDataToLocal(path, args, index, validTime, s);


            }

            @Override
            public void onError(Throwable throwable, boolean b) {
//请求失败告诉用户
                setResultError(ShowingPage.StateType.STATE_LOAD_ERROR);


            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    protected abstract void setResultError(ShowingPage.StateType stateLoadError);

    private void writeDataToLocal(String path, String args, int index, int validTime, String data) {
        //每一条请求都生成2一个文件额
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);

            //xieliu写流信息
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            //从网络上请求的数据
            bufferedWriter.write(System.currentTimeMillis() + validTime + "\r\n");
            bufferedWriter.write(data);
            bufferedWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
