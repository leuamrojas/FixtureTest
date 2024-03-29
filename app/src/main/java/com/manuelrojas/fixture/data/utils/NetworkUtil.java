package com.manuelrojas.fixture.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public class NetworkUtil {

    private Context context;

    @Inject
    public NetworkUtil(Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Returns true if the Throwable is an instance of RetrofitError with an
     * http status code equals to the given one.
     */
//    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
//        return throwable instanceof HttpException
//                && ((HttpException) throwable).code() == statusCode;
//    }
//
//    public static boolean isNetworkConnected() {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//    }

}