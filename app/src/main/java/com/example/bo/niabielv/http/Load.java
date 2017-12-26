package com.example.bo.niabielv.http;


import android.support.annotation.NonNull;

import com.example.bo.niabielv.BuildConfig;
import com.example.bo.niabielv.constant.AppConstant;
import com.example.bo.niabielv.http.api.ApiService;
import com.example.bo.niabielv.http.api.BetaApi;
import com.example.bo.niabielv.utils.L;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zsp
 * @date 2016/8/10 0010
 * @description
 */
public class Load {
    private volatile static Retrofit sInstance;
    private volatile static Retrofit.Builder sBuilder;


    /**
     * 创建网络请求
     */
    public static ApiService createApi() {
        if (sInstance == null) {
            synchronized (Load.class) {
                if (sInstance == null) {
                    createRetrofit();
                }
            }
        }
        return sInstance.create(ApiService.class);
    }

    public static void recreate() {
        createRetrofit();
    }

    /**
     * 创建网络请求
     */
    public static <T> T createApi(Class<T> aClass) {
        if (sInstance == null) {
            synchronized (Load.class) {
                if (sInstance == null) {
                    createRetrofit();
                }
            }
        }
        return sInstance.create(aClass);
    }

    private static void createRetrofit() {
        OkHttpClient.Builder client = new OkHttpClient.Builder().connectTimeout(AppConstant.CONFIG_NETWORK_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(AppConstant.CONFIG_NETWORK_TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts()).hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        client.addInterceptor(new HttpLoggingInterceptor(message -> L.e("https:" + message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit.Builder retrofit = getBuilder();
        sBuilder = retrofit;
        retrofit.client(client.build());
        sInstance = retrofit.build();
    }

    @NonNull
    private static Retrofit.Builder getBuilder() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static Retrofit.Builder getRetrofit() {
        if (sBuilder == null) {
            sBuilder = getBuilder();
        }
        return sBuilder;
    }

    /**
     * 创建网络请求
     * 测试用
     * 返回JSON
     */
   /* public static ApiService testApi() {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(SysConstants.CONFIG_NETWORK_TIME_OUT, TimeUnit.SECONDS).readTimeout(SysConstants.CONFIG_NETWORK_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor(L::d).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        Retrofit.Builder retrofit = new Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(getLoadHead());
        retrofit.client(client);
        return retrofit.build().create(ApiService.class);
    }*/
    private static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * @param cacheKey     缓存key
     * @param expireTime   过期时间 0 表示有缓存就读，没有就从网络获取
     * @param fromNetwork  从网络获取的Observable
     * @param forceRefresh 是否强制刷新
     */
   /* public static <T> Observable<T> load(final String cacheKey, long expireTime, Observable<T> fromNetwork, boolean forceRefresh) {
        if (!NetworkUtils.isNetworkConnected()) {
            expireTime = 0;
        }
        final long finalExpireTime = expireTime;
        Observable<T> fromCache = Observable.create((ObservableOnSubscribe<T>) e -> {
            T cache = CacheManager.readObject(cacheKey, finalExpireTime);
            if (cache != null) {
                e.onNext(cache);
            } else {
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());


        *//**
     * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
     *//*
        fromNetwork = fromNetwork.map(result -> {
            CacheManager.saveObject((Serializable) result, cacheKey);
            return result;
        });
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCache, fromNetwork).take(1);
        }
    }*/

    /**
     * 有网络就从网络读取，没有时才去缓存中拿
     *
     * @param cacheKey    缓存key
     * @param fromNetwork 从网络获取的Observable
     */
   /* public static <T> Observable<T> load(final String cacheKey, Observable<T> fromNetwork) {
        boolean isLoadCache = false;
        if (NetworkUtils.isNetworkConnected()) {
            isLoadCache = true;
        }

        */

    /**
     * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
     *//*
        fromNetwork = fromNetwork.map(result -> {
            CacheManager.saveObject((Serializable) result, cacheKey);
            return result;
        });

        if (isLoadCache) {
            return fromNetwork;
        } else {
            Observable<T> fromCache = Observable.create((ObservableOnSubscribe<T>) e -> {
                T cache = CacheManager.readObject(cacheKey, SysConstants.DEFAULT_INT);
                if (cache != null) {
                    e.onNext(cache);
                } else {
                    e.onComplete();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            return Observable.concat(fromCache, fromNetwork).take(1);
        }
    }*/
    public static String getLoadUrl() {
        if (BuildConfig.DEBUG) {
            return BetaApi.getInstance().getBetaUrl();
        } else {
            return BuildConfig.API_URL;
        }
    }

    public static String getLoadHead() {
        if (BuildConfig.DEBUG) {
            return BetaApi.getInstance().getBetaUrl();
        } else {
            return BuildConfig.API_URL;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

}
