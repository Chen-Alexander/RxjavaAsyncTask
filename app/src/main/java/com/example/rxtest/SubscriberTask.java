package com.example.rxtest;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class SubscriberTask<Params, Progress, Result>
{
    public static final String TAG = "SubscriberTask";
    private boolean needDialog = true;
    private Subscription subscription = null;

    public void onPre()
    {
        Log.e(TAG, "onPre");
        if(needDialog)
        {
            Log.e(TAG, "弹出进度框");
        }
    }
    public abstract Flowable doInBack(Params[] params);
    public abstract void onProgress(Progress progress);
    public void onThrow(Throwable throwable)
    {
        Log.e(TAG, "onThrow:" + throwable.getMessage());
    }
    public abstract void onPost(Result result);

    public SubscriberTask(boolean needDialog)
    {
        this.needDialog = needDialog;
    }

    public final void execute(Params[] params)
    {
        onPre();
        Flowable flowable = doInBack(params);
        flowable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber()
                {
                    @Override
                    public void onSubscribe(Subscription s)
                    {
                        subscription = s;
                        s.request(1);
                    }
                    @Override
                    public void onNext(Object o)
                    {
                        Result result = (Result) o;
                        onPost(result);
                    }
                    @Override
                    public void onError(Throwable t)
                    {
                        onThrow(t);
                        onComplete();
                    }
                    @Override
                    public void onComplete()
                    {
                        Log.e(TAG, "onComplete");
                        if(needDialog)
                        {
                            Log.e(TAG, "隐藏并销毁进度框");
                        }
                    }
                });
    }
}
