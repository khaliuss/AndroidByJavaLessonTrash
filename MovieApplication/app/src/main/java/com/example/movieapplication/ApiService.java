package com.example.movieapplication;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String TOKEN ="X-API-KEY: 5RBED7A-V444WWE-QKBP08F-T8BA14K";

    @Headers(TOKEN)
    @GET("movie?field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=40")
    Single<MovieResponse> loadMovies (@Query("page")int page);



    @GET("movie/{id}?token=5RBED7A-V444WWE-QKBP08F-T8BA14K")
    Single<TrailerResponse> loadTrailers (@Path("id") int id);


    @GET("review?token=5RBED7A-V444WWE-QKBP08F-T8BA14K&page=1&limit=10&selectFields=")
    Single<ReviewResponse> loadReviews(@Query("movieId") int id);


}
