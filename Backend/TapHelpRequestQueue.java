package com.scu.myapplication;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class TapHelpRequestQueue {
    private static TapHelpRequestQueue requestQueueInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private TapHelpRequestQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized TapHelpRequestQueue getInstance(Context context) {
        if (requestQueueInstance == null) {
            requestQueueInstance = new TapHelpRequestQueue(context);
        }
        return requestQueueInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}