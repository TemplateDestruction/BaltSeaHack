package xyz.tusion.baltseahack_androidapp.domain.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.tusion.baltseahack_androidapp.BuildConfig;

public final class ServerApiFactory {

    private static OkHttpClient sClient;

    private static volatile JsonService sService;

    private ServerApiFactory() {
    }

    @NonNull
    public static JsonService getJsonService() {
        JsonService service = sService;
        if (service == null) {
            synchronized (ServerApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = buildRetrofit().create(JsonService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sClient = null;
        sClient = getClient();
        sService = buildRetrofit().create(JsonService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ServerApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }

}
