package wuan.nixo.com.wuan_android_v2.utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * 本地存储
 * Created by zhanghongyu on 2018/6/14.
 */
public class Preferences {
    private static final String KEY_USER_ACCOUNT = "account";
    private static final String KEY_USER_TOKEN = "token";
    private static final String KEY_USER_ID = "userId";
    static private Preferences instance = null;
    static private String FIRST_OPEN = "first_open";
    static private String SHARE_NAME = App.SHARE_NAME;

    public static void saveUserAccount(String account) {
        saveAccount(KEY_USER_ACCOUNT, account);
    }

    public static String getUserAccount() {
        return getString(KEY_USER_ACCOUNT);
    }

    public static void saveUserToken(String token) {
        saveString(KEY_USER_TOKEN, token);
    }

    public static String getUserToken() {
        return getString(KEY_USER_TOKEN);
    }

    static public Preferences getInstance() {
        return instance;
    }

    public static String getUserId() {
        return getString(KEY_USER_ID);
    }

    public static void saveUserId(String userId) {
        saveAccount(KEY_USER_ID, userId);
    }

    static public boolean getFirstOpen() {
        return getSharedPreferencesFirst().getBoolean(FIRST_OPEN, false);
    }

    static public void saveFirstOpen() {
        SharedPreferences.Editor editor = getSharedPreferencesFirst().edit();
        editor.putBoolean(FIRST_OPEN, true);
        editor.commit();
    }


    public static void saveAccount(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveLong(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(String key) {
        return getSharedPreferences().getLong(key, 0);
    }

    public static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    public static void saveBoolean(String key, boolean state) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, state);
        editor.commit();
    }

    public static void saveFloat(String key, float state) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putFloat(key, state);
        editor.commit();
    }

    public static float getFloat(String key) {
        return getSharedPreferences().getFloat(key, 0f);
    }

    public static boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    public static int getInt(String key) {
        return getSharedPreferences().getInt(key, -1);
    }


    static SharedPreferences getSharedPreferences() {
        return App.mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    }
    static SharedPreferences getSharedPreferencesFirst() {
        return App.mContext.getSharedPreferences("p2p", Context.MODE_PRIVATE);
    }
    /**
     * 删除全部数据
     *
     */
    public static void deleShareData() {
        SharedPreferences sp = getSharedPreferences();
        //全部清理
        sp.edit().clear().commit();
    }
}
