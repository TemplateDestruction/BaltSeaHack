package xyz.tusion.baltseahack_androidapp.domain.repository;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;
import xyz.tusion.baltseahack_androidapp.presentation.base.binding.Bind;

public interface JsonRepository {

    /*@NonNull
    Observable<List<ResultJava>> getJavaVideos();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);*/

    Observable<ArrayList<Club>> getClubs();

    Observable<Club> getSingleClubById(String id);
}
