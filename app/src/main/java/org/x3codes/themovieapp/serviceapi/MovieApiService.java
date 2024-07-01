package org.x3codes.themovieapp.serviceapi;

import org.x3codes.themovieapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
}
