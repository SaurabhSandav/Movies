package com.redridgeapps.movies.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    public static Resources provideResources(Application app) {
        return app.getResources();
    }

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    public static StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(StethoInterceptor stethoInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(stethoInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(
            OkHttpClient okHttpClient,
            MoshiConverterFactory moshiCon,
            RxJava2CallAdapterFactory rxCallAdapter
    ) {
        return new Retrofit.Builder()
                .baseUrl(TMDbService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(moshiCon)
                .addCallAdapterFactory(rxCallAdapter)
                .build();
    }

    @Provides
    @Singleton
    public static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public static MoshiConverterFactory provideMoshiConverterFactory() {
        return MoshiConverterFactory.create();
    }

    @Provides
    @Singleton
    public static TMDbService provideTMDbService(Retrofit retrofit) {
        return retrofit.create(TMDbService.class);
    }

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, AppDatabase.NAME).build();
    }
}
