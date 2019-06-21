package com.example.rxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rxtest.demo.TestExecuteTask;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Subscription subscription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Flowable flowable = Flowable.create((FlowableOnSubscribe) emitter ->
//        {
//            try
//            {
//                emitter.onNext(new ObservableMsgBean(ObservableMsgBean.START));
//                Response response = okHttpClient.newCall(new Request.Builder()
//                        .url("http://v.baidu.com/commonapi/movie2level/")
//                        .build()).execute();
//                if(response.isSuccessful())
//                {
//                    emitter.onNext(new ObservableMsgBean(ObservableMsgBean.NETREQUESTSUCCESS, response.body().string()));
//                }
//                else
//                {
//                    emitter.onThrow(new Exception(response.code()+":"+response.message()));
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//                emitter.onThrow(e);
//            }
//            emitter.onComplete();
//        }, BackpressureStrategy.BUFFER);
//        flowable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new FlowableSubscriber()
//                {
//                    @Override
//                    public void onSubscribe(Subscription s)
//                    {
//                        Log.e(TAG, "onSubscribe");
//                        subscription = s;
//                        subscription.request(1);
//                    }
//
//                    @Override
//                    public void onNext(Object o)
//                    {
//                        switch (((ObservableMsgBean) o).getId())
//                        {
//                            case ObservableMsgBean.START:
//                                Log.e(TAG, "OnStart");
//                                break;
//                            case ObservableMsgBean.NETREQUESTSUCCESS:
//                                Log.e(TAG, ((ObservableMsgBean)o).getMsg());
//                                break;
//                            default:
//                                break;
//                        }
//                        subscription.request(1);
//                    }
//
//                    @Override
//                    public void onThrow(Throwable t)
//                    {
//                        t.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete()
//                    {
//                        Log.e(TAG, "onComplete");
//                    }
//                });
//        flowable.unsubscribeOn(Schedulers.io());
//        SubscriberTask subscriberTask = new SubscriberTask<String, Integer, ObservableMsgBean>(false)
//        {
//            @Override
//            public void onPre()
//            {
//                super.onPre();
//            }
//
//            @Override
//            public Flowable doInBack(String[] params)
//            {
//                Log.e(TAG, "doInBack");
//                Flowable flowable = Flowable.create((FlowableOnSubscribe) emitter ->
//                {
//                    try
//                    {
//                        Response response = okHttpClient.newCall(new Request.Builder()
//                                .url(params[0])
//                                .build()).execute();
//                        if(response.isSuccessful())
//                        {
//                            emitter.onNext(new ObservableMsgBean(ObservableMsgBean.NETREQUESTSUCCESS, response.body().string()));
//                        }
//                        else
//                        {
//                            emitter.onError(new Exception(response.code()+":"+response.message()));
//                        }
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                        emitter.onError(e);
//                    }
//                    emitter.onComplete();
//                }, BackpressureStrategy.BUFFER);
//                return flowable;
//            }
//
//            @Override
//            public void onProgress(Integer integer)
//            {
//                Log.e(TAG, "onProgress:" + integer);
//            }
//
//            @Override
//            public void onThrow(Throwable throwable)
//            {
//                super.onThrow(throwable);
//            }
//
//            @Override
//            public void onPost(ObservableMsgBean observableMsgBean)
//            {
//                Log.e(TAG, observableMsgBean.getMsg());
//            }
//        };
//        subscriberTask.execute(new String[]{"http://v.aidu.com/commonapi/movie2level/"});
        TestExecuteTask testExecuteTask = new TestExecuteTask(this);
        testExecuteTask.exectue();
    }
}
