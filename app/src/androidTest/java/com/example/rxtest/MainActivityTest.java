package com.example.rxtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class MainActivityTest
{

    @Test
    public void onCreate()
    {
        HashMap hashMap = new HashMap(10);
        hashMap.put(null, null);
        hashMap.put("qqq", null);
        hashMap.put("ccv", null);
        hashMap.put(null, 3);
        hashMap.put(null, 4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add(null);
        HashSet hashSet = new HashSet();
        hashSet.add("abc");
        hashSet.add("qwer");
        hashSet.add("qwer");
    }
}