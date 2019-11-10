package xyz.tusion.baltseahack_androidapp.domain.repository;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Path;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;
import xyz.tusion.baltseahack_androidapp.domain.model.Event;
import xyz.tusion.baltseahack_androidapp.presentation.base.binding.Bind;

public interface JsonRepository {

    /*@NonNull
    Observable<List<ResultJava>> getJavaVideos();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);*/

    Observable<ArrayList<Club>> getClubs();

    Observable<Club> getSingleClubById(String id);

    Observable<List<Event>> getAllEvents();

    Observable<Completable> meetMeeting(String eventid, String userId);

    Observable<Completable> subscribeToMeeting(String eventid, String userId);

}
