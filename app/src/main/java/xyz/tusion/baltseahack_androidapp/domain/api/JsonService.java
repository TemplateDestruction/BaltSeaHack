package xyz.tusion.baltseahack_androidapp.domain.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface JsonService {
    @POST("register")
    Completable regJava(@Body JsonObject params);

    @GET("http://suggestqueries.google.com/complete/search?client=youtube&ds=yt&client=firefox")
    Observable<JsonElement> getSuggestedQueries(@Query("q") String channelTemplate);
}
