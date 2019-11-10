package xyz.tusion.baltseahack_androidapp.domain.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;
import xyz.tusion.baltseahack_androidapp.domain.model.Counting;
import xyz.tusion.baltseahack_androidapp.domain.model.Event;

public interface JsonService {
    @POST("register")
    Completable regJava(@Body JsonObject params);

    @GET("club/all")
    Observable<ArrayList<Club>> getClubs();

    @GET("club/{id}")
    Observable<Club> getClub(@Path("id") String id);

    @GET("event/{eventId}/subscribe/{userId}")
    Observable<Completable> subscribeToMeeting(@Path("eventId") String eventid, @Path("userId") String userId);

    @GET("event/{eventId}/meet/{userId}")
    Observable<Completable> meetMeeting(@Path("eventId") String eventid, @Path("userId") String userId);

    @GET("event/all")
    Observable<List<Event>> getAllEvents();

    @GET("event/{eventId}/count")
    Observable<Event> getCountByEventId();



}
