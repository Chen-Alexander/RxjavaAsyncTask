package com.example.rxtest.demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.lang.ref.WeakReference;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseRxTask<D, P>
{
    public static final String TAG = "BaseRxTask";
    protected P[] p;
    protected WeakReference<Context> contextWeakReference;
    private boolean needDialog = false;
    private ProgressDialog progressDialog;
    private Subscription subscription;
    protected boolean flowIsEmpty = false;

    public BaseRxTask(Context context)
    {
        this.contextWeakReference = new WeakReference<>(context);
        this.needDialog = true;
    }

    public BaseRxTask(Context context, boolean needDialog)
    {
        this.contextWeakReference = new WeakReference<>(context);
        this.needDialog = needDialog;
    }

    public BaseRxTask(Context context, boolean needDialog, P... p)
    {
        this.contextWeakReference = new WeakReference<>(context);
        this.needDialog = needDialog;
        this.p = p;
    }

    protected Flowable<D> doInBackgroundObservable()
    {
        return doInBackground();
    }

    public Flowable<D> exectue()
    {
        return getDialogView();
    }

    private Flowable<D> getDialogView()
    {
        Flowable<D> flowable = doInBackgroundObservable();
        flowable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<D>()
                {
                    @Override
                    public void onSubscribe(Subscription s)
                    {
                        if(needDialog)
                        {
                            progressDialog = ProgressDialog.show(contextWeakReference.get(), "Loading...", "正在加载", true, false);
                        }
                        subscription = s;
                        subscription.request(1);
                    }
                    @Override
                    public void onNext(D d)
                    {
                        Log.e(TAG, "" + d);
                        if(!flowIsEmpty)
                        {
                            subscription.request(1);
                        }
                    }
                    @Override
                    public void onError(Throwable t)
                    {
                        finish();
                    }
                    @Override
                    public void onComplete()
                    {
                        finish();
                    }
                });
        return flowable;
    }

    protected abstract Flowable<D> doInBackground();

    protected abstract Object buildParams(P... p);

    private void finish()
    {
        contextWeakReference.clear();
        if(progressDialog != null)
        {
            progressDialog.dismiss();
            progressDialog = null;
        }
        if(subscription != null)
        {
            subscription.cancel();
            subscription = null;
        }
    }
}
