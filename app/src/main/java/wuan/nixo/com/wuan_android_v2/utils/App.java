package wuan.nixo.com.wuan_android_v2.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zhanghongyu on 2018/6/14.
 */

public class App extends MultiDexApplication {
    public static Context mContext;
    public static int mWidth;
    public static int mheight;
    public static String SHARE_NAME = "p2p";
    public static Handler mHandler;
    public static String TEL="12345678";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mWidth = displayMetrics.widthPixels;
        mheight = displayMetrics.heightPixels;
        okHttpInit();
    }

    /**
     * okHttp初始化
     */
    private void okHttpInit() {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("car"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)//cookie保活
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }


    /**
     * 是否登录
     */
    public boolean isLogin() {
        boolean isLogin = Preferences.getBoolean("isLogin");
        return isLogin;
    }

    /**
     * 设置是否登录
     */
    public void login(Boolean isLogin) {
        Preferences.saveBoolean("isLogin", isLogin);
    }

    //获取版本名
    public static String getVersionName() {
        return getPackageInfo(mContext).versionName;
    }

    //获取版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    //通过PackageInfo得到的想要启动的应用的包名
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pInfo = null;

        try {
            //通过PackageManager可以得到PackageInfo
            PackageManager pManager = context.getPackageManager();
            pInfo = pManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pInfo;
    }
}
