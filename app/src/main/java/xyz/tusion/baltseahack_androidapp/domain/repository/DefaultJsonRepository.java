package xyz.tusion.baltseahack_androidapp.domain.repository;

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
import xyz.tusion.baltseahack_androidapp.domain.utils.PreferenceUtils;
import xyz.tusion.baltseahack_androidapp.domain.utils.RxUtils;

public class DefaultJsonRepository implements JsonRepository {

    private int pageNumber = 1;

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
