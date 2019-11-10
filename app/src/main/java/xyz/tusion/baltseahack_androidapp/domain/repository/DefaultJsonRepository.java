package xyz.tusion.baltseahack_androidapp.domain.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import xyz.tusion.baltseahack_androidapp.domain.api.ServerApiFactory;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;

public class DefaultJsonRepository implements JsonRepository {

    private int pageNumber = 1;

    @Override
    public Observable<List<Club>> getClubs() {
        return ServerApiFactory
                .getJsonService()
                .getClubs()
//                .flatMap(new Function<List<Club>, ObservableSource<List<Club>>>() {
//                    @Override
//                    public ObservableSource<List<Club>> apply(List<Club> clubs) throws Exception {
//                        Log.e("DICH", "apply: ");
//                        for (Club club : clubs) {
//                            Log.e("map data: ", club.getName());
//                        }
//                        return Observable.just(clubs);
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*@NonNull
    @Override
    public Observable<List<ResultJava>> getJavaVideos() {

        return ServerApiFactory.getJsonService()
                .videoJava()
                .flatMap(videoJava -> Observable.just(videoJava.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }*/


    /*@NonNull
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
        return ServerApiFactory.getJsonService()
                .authJava(AuthorizationUtils.createAuthorizationParam(login, password))
                .flatMap(authorization -> {
                    PreferenceUtils.saveToken(authorization.getToken());
                    PreferenceUtils.saveUserName(login);
                    PreferenceUtils.saveLoginState();
                    PreferenceUtils.saveDateToken(Calendar.getInstance().getTime().getDate());
                    PreferenceUtils.setExpireTime(25);
                    ServerApiFactory.recreate();
                    return Observable.just(authorization);
                })
                .compose(RxUtils.async());
    }*/

}
