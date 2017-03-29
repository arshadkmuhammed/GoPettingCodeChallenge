package com.arshad.free.gopettingcodechallenge.network;

import android.content.Context;

import com.arshad.free.gopettingcodechallenge.utils.Constants;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arshad on 29/3/17.
 */
public class ServiceFactory {

    public static ServiceClass getInstance(final Context context) {

        ServiceClass service;

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServiceClass.class);

        return service;
    }
}
