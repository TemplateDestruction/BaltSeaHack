package xyz.tusion.baltseahack_androidapp.domain.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;

public interface JsonService {
    @POST("register")
    Completable regJava(@Body JsonObject params);

    @GET("club/all")
    Observable<List<Club>> getClubs();
}
