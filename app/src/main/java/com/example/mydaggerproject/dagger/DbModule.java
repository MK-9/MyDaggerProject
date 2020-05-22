package com.example.mydaggerproject.dagger;

import android.app.Application;

import androidx.room.Room;

import com.example.mydaggerproject.room.AppDatabase;
import com.example.mydaggerproject.room.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;

@Module
public class DbModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "Entertainment.db")
                .allowMainThreadQueries().build();
    }


    /*
     * We need the MovieDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */

    @Provides
    @Singleton
    MovieDao provideMovieDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.movieDao();
    }
}
