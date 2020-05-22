package com.example.mydaggerproject.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydaggerproject.api.MovieApiResponse;
import com.example.mydaggerproject.api.MovieApiService;
import com.example.mydaggerproject.repo.MovieRepository;
import com.example.mydaggerproject.room.MovieDao;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    /* We are using LiveData to update the UI with the data changes.
     */
    private MutableLiveData<Response<List<MovieApiResponse>>> moviesLiveData = new MutableLiveData<>();

    @Inject
    public MovieListViewModel(MovieApiService apiService, MovieDao movieDao) {
        movieRepository = new MovieRepository(apiService, movieDao);
    }


    public MutableLiveData<Response<List<MovieApiResponse>>> getMoviesLiveData() {
        return moviesLiveData;
    }
}