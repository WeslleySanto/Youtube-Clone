package br.com.weslleyesanto.youtubeclone.api;

import br.com.weslleyesanto.youtubeclone.model.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {


    /*
    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyBKnKHbxahet5WgPDAInt_PJgbAK23-K6k
    &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg
     */
    @GET("search")
    Call<Resultado> recuperarVideos(
         @Query("part") String part,
         @Query("order") String order,
         @Query("maxResults") String maxResults,
         @Query("key") String key,
         @Query("channelId") String channelId
    );
}
