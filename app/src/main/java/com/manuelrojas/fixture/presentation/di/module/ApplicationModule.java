package com.manuelrojas.fixture.presentation.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.manuelrojas.fixture.data.executor.JobExecutor;
import com.manuelrojas.fixture.data.repository.FixtureDataRepository;
import com.manuelrojas.fixture.data.repository.datasource.ILocalFixtureDataStore;
import com.manuelrojas.fixture.data.repository.datasource.LocalFixtureDataStore;
import com.manuelrojas.fixture.data.repository.datasource.db.FixtureDao;
import com.manuelrojas.fixture.data.repository.datasource.sync.IRemoteFixtureDataStore;
import com.manuelrojas.fixture.data.repository.datasource.sync.RemoteFixtureDataStore;
import com.manuelrojas.fixture.data.repository.network.RetrofitClient;
import com.manuelrojas.fixture.data.utils.TLSSocketFactory;
import com.manuelrojas.fixture.domain.FixtureRepository;
import com.manuelrojas.fixture.domain.executor.PostExecutionThread;
import com.manuelrojas.fixture.domain.executor.ThreadExecutor;
import com.manuelrojas.fixture.presentation.MainApplication;
import com.manuelrojas.fixture.presentation.UIThread;
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
    private static final int CACHE_SIZE = 10 * 1024 * 1024;

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
        Cache cache = new Cache(context.getCacheDir(), CACHE_SIZE);
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

    @Singleton
    @Provides
    ILocalFixtureDataStore provideLocalFixtureDataStore(FixtureDao fixtureDao) {
        return new LocalFixtureDataStore(fixtureDao);
    }

    @Singleton
    @Provides
    IRemoteFixtureDataStore provideRemoteFixtureDataStore(RetrofitClient client) {
        return new RemoteFixtureDataStore(client);
    }

}
