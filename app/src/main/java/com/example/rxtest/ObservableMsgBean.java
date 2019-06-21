package com.example.rxtest;

import android.os.Parcel;
import android.os.Parcelable;

public class ObservableMsgBean extends Object implements Parcelable
{
    public static final int START = 0;
    public static final int NETREQUESTSUCCESS = 1;

    private int id;
    private String msg;

    public ObservableMsgBean()
    {
    }

    public ObservableMsgBean(int id)
    {
        this.id = id;
    }

    public ObservableMsgBean(int id, String msg)
    {
        this.id = id;
        this.msg = msg;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.id);
        dest.writeString(this.msg);
    }

    protected ObservableMsgBean(Parcel in)
    {
        this.id = in.readInt();
        this.msg = in.readString();
    }

    public static final Creator<ObservableMsgBean> CREATOR = new Creator<ObservableMsgBean>()
    {
        @Override
        public ObservableMsgBean createFromParcel(Parcel source)
        {
            return new ObservableMsgBean(source);
        }

        @Override
        public ObservableMsgBean[] newArray(int size)
        {
            return new ObservableMsgBean[size];
        }
    };
}
