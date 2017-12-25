package com.example.bo.niabielv.http.api;


import com.example.bo.niabielv.BuildConfig;
import com.example.bo.niabielv.app.NiaBieApplication;

/**
 * @author chenzhaojun
 * @date 2016/12/28 0028
 * @description
 */
public class BetaApi {
    private static BetaApi ourInstance = new BetaApi();
    private static final String KEY_BETA_URL = "beta_url";
    private String url;

    public static BetaApi getInstance() {
        return ourInstance;
    }

    private BetaApi() {

    }

    public String getBetaUrl() {
        if (url == null) {
//            url = SpCache.init(NiaBieApplication.getInstance()).getString(KEY_BETA_URL, BuildConfig.API_URL);
        }
        return url;
    }


    public void setBetaUrl(String url) {
        this.url = url;
//        SpCache.putString(KEY_BETA_URL, url);
    }
}
