package com.manuelrojas.geomusic.presentation.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.manuelrojas.geomusic.data.repository.FixtureDataRepository;
import com.manuelrojas.geomusic.data.utils.TLSSocketFactory;
import com.manuelrojas.geomusic.domain.FixtureRepository;
import com.manuelrojas.geomusic.domain.executor.PostExecutionThread;
import com.manuelrojas.geomusic.domain.executor.ThreadExecutor;
import com.manuelrojas.geomusic.presentation.MainApplication;
import com.manuelrojas.geomusic.presentation.UIThread;
import com.manuelrojas.geomusic.data.executor.JobExecutor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class ApplicationModule {
    MainApplication mMainApplication;
    private static final String DAGGER_PREFS = "dagger-prefs";

    public ApplicationModule(MainApplication mainApplication) {
        mMainApplication = mainApplication;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return mMainApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return mMainApplication.getSharedPreferences(DAGGER_PREFS, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //Change SSL(Default) to TLS for devices running android API < 20
        TLSSocketFactory tlsSocketFactory = null;
        try {
            tlsSocketFactory = new TLSSocketFactory();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        client.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.getTrustManager());
        client.cache(cache);
        return  client.build();
    }

    @Provides
    @Singleton
    Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
        return picasso;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    FixtureRepository provideFixtureRepository(FixtureDataRepository fixtureDataRepository) {
        return fixtureDataRepository;
    }

}
