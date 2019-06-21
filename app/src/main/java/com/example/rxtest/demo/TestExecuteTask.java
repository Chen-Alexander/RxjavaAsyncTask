package com.example.rxtest.demo;

import android.content.Context;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.internal.operators.flowable.FlowableBuffer;
import okhttp3.Interceptor;

public class TestExecuteTask<D, P> extends BaseRxTask<D, P>
{
    public TestExecuteTask(Context context)
    {
        super(context);
    }

    public TestExecuteTask(Context context, boolean needDialog)
    {
        super(context, needDialog);
    }

    public TestExecuteTask(Context context, boolean needDialog, P... p)
    {
        super(context, needDialog, p);
    }

    @Override
    protected Flowable<D> doInBackground()
    {
        Flowable flowable = Flowable.create(new FlowableOnSubscribe<D>()
        {
            @Override
            public void subscribe(FlowableEmitter<D> emitter) throws Exception
            {
                for (int i = 0; i < 100; i++)
                {
                    Thread.sleep(100);
                    if(i == 99)
                    {
                        flowIsEmpty = false;
                    }
                    emitter.onNext((D) (new Integer(i)));
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
        return flowable;
    }

    @Override
    protected Object buildParams(P... p)
    {
        return null;
    }
}
