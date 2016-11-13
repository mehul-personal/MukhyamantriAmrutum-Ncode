package com.ncode.mukhyamantriamrutum;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class ApplicationData extends Application {
    public static final String searchURL = "http://www.magujarat.com/AndroidMobileWebAPI_UAT/Operations.asmx/";
    public static final String serviceURL = "http://192.95.6.213/";
    public static final String TAG = ApplicationData.class
            .getSimpleName();
    private static ApplicationData mInstance;
    private RequestQueue mRequestQueue;
//	private Twitter twitter;

    public static synchronized ApplicationData getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mInstance = this;
    }

    /*
        public Twitter getTwitter() {
            return twitter;
        }

        public void setTwitter(Twitter twitter) {
            this.twitter = twitter;
        }

        private OAuthProvider provider;
        private CommonsHttpOAuthConsumer consumer;

        public void setProvider(OAuthProvider provider) {
            this.provider = provider;
        }

        public OAuthProvider getProvider() {
            return provider;
        }

        public void setConsumer(CommonsHttpOAuthConsumer consumer) {
            this.consumer = consumer;
        }

        public CommonsHttpOAuthConsumer getConsumer() {
            return consumer;
        }

        public boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
