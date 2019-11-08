package xyz.tusion.baltseahack_androidapp.domain.api;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import xyz.tusion.baltseahack_androidapp.domain.utils.PreferenceUtils;

final class ApiKeyInterceptor implements Interceptor {


    private ApiKeyInterceptor() {
    }

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = PreferenceUtils.getToken();
        if (TextUtils.isEmpty(token)) {
            Log.d("REQUEST SERVER", "TOKEN IS EMPTY");
            return chain.proceed(chain.request());
        }
        Log.d("REQUEST SERVER", "TOKEN IS NOT EMPTY");
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", String.format("%s %s", "Bearer", token))
                .build();
        return chain.proceed(request);

    }
}
