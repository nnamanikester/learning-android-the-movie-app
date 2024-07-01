package org.x3codes.themovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.x3codes.themovieapp.model.Movie;
import org.x3codes.themovieapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel  {

    private final MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return repository.getMutableLiveData();
    }

}
