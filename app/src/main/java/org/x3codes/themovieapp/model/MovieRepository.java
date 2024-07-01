package org.x3codes.themovieapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.x3codes.themovieapp.R;
import org.x3codes.themovieapp.serviceapi.MovieApiService;
import org.x3codes.themovieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private final MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>( );
    private final Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<Result> call = movieApiService
                .getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        // Perform network request in the background thread
        // and handle the response on the main (UI) thread.
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                Result result = response.body();
                if (result != null && result.getResults() != null) {
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
