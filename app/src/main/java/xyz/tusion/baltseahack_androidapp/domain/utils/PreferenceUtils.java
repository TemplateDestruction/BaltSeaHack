package xyz.tusion.baltseahack_androidapp.domain.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import xyz.tusion.baltseahack_androidapp.App;

public final class PreferenceUtils {
    //Auth
    private static final String PASSWORD_KEY = "user_password";
    private static final String TOKEN_KEY = "id_token";
    private static final String USER_NAME_KEY = "user_name";
    private static final String WALKTHROUGH_PASSED_KEY = "walkthrough_passed";
    private static final String TOKEN_DATE = "token_date";
    private static final String REST_DAYS = "rest_days";
    private static final String LOG_STATE = "log_state";
    private static final String EMAIL = "email";
    private static final String CONFIRMATION_CODE = "confirmation_code";
    private static final String AGE = "age";

    private static SharedPreferences preferences;

    static {
        preferences = App.Companion.getSContext().getSharedPreferences("xyz.tusion.nrboom", Context.MODE_PRIVATE);
    }

    private PreferenceUtils() {
    }

    public static String getToken() {
        return preferences.getString(TOKEN_KEY, "");
//        return Hawk.get(TOKEN_KEY, "");
    }

    public static void saveUserName(@NonNull String userName) {
        preferences
                .edit()
                .putString(USER_NAME_KEY, userName)
                .apply(); // commit
//        Hawk.put(USER_NAME_KEY, userName);
    }

    public static String getUserName() {
        return preferences.getString(USER_NAME_KEY, "");
    }


    public static void saveEmail(@NonNull String email) {
        preferences
                .edit()
                .putString(EMAIL, email)
                .apply(); // commit
    }

    public static void saveAge(@NonNull Integer age) {
        preferences
                .edit()
                .putInt(AGE, age)
                .apply(); // commit
    }

    public static Integer getAGE() {
        return preferences.getInt(AGE, 0);
    }

    public static String getEmail() {
        return preferences.getString(EMAIL, "");
    }

    public static void saveDateToken(@NonNull int tokenDate) {
        preferences
                .edit()
                .putInt(TOKEN_DATE, tokenDate)
                .apply();
    }

    public static int getTokenDate() {
        return preferences.getInt(TOKEN_DATE, 0);
    }

    public static void setExpireTime(int days) {
        preferences
                .edit()
                .putInt(REST_DAYS, days)
                .apply();
    }

    public static int getExpireTime() {
        return preferences.getInt(REST_DAYS, 0);
    }

    // TODO: 12.05.2019 getUserName

    public static void saveWalkthroughPassed() {
        preferences
                .edit()
                .putBoolean(WALKTHROUGH_PASSED_KEY, true)
                .apply();
    }

    public static boolean isWalkthroughPassed() {
        return preferences.getBoolean(WALKTHROUGH_PASSED_KEY, false);
    }


    public static void saveLoginState() {
        preferences
                .edit()
                .putBoolean(LOG_STATE, false)
                .apply();
    }

    public static void saveLogoutState() {
        preferences
                .edit()
                .putBoolean(LOG_STATE, true)
                .apply();
    }

    public static boolean isLogout() {
        return preferences.getBoolean(LOG_STATE, true);
    }
}
