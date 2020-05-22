package com.example.mydaggerproject.dagger;

import android.app.Application;

import com.example.mydaggerproject.api.MovieApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Singleton
    @Provides
    Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    Cache cache(Application application) {
        File file = new File(application.getCacheDir(), "cache");
        long cache_size = 1024 * 1024 * 10;
        return new Cache(file, cache_size);
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    OkHttpClient Client(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(30, TimeUnit.MILLISECONDS)
                .writeTimeout(30, TimeUnit.MILLISECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    MovieApiService movieApiService(Retrofit retrofit) {
        return retrofit.create(MovieApiService.class);
    }
}
