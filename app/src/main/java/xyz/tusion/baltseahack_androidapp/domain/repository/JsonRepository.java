package xyz.tusion.baltseahack_androidapp.domain.repository;


import java.util.List;

import io.reactivex.Observable;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;

public interface JsonRepository {

    /*@NonNull
    Observable<List<ResultJava>> getJavaVideos();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);*/

    Observable<List<Club>> getClubs();

}
