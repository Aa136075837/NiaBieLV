package com.example.bo.niabielv.http;


import com.example.bo.niabielv.utils.L;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * RequestMethodUtils
 *
 * @author zsp
 * @date 2016.8.12
 */
public class RequestUtils {
    private static final String SIGN_CODE = "signCode";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 生成随机数
     */
    private static String getRandomNumber() {
        Random random = new Random(100000000);
        return String.valueOf(random.nextInt());
    }

    /**
     * 拼装入参中4个基本的入参值
     *
     * @return 请求的基本参数
     */
    private static LinkedHashMap<String, Object> getAssembleBaseInputParameters() {
        // 只有用了LinkedHashMap，才可以保证Map键值对，按顺序输入，同样的顺序输出
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap<>();
//        paramsMap.put("function", api);
//        paramsMap.put("mcId", getSystemVersionName());
//        paramsMap.put("version", getCurrentVersionName());
//        paramsMap.put("random", getRandomNumber());
        return paramsMap;
    }

    public static RequestBody getParams() {
        return getParams( null);
    }

    /**
     * 拼装并加密参数
     *
     * @return 请求参数
     */
    public static RequestBody getParams( LinkedHashMap<String, Object> map) {
        LinkedHashMap<String, Object> paramsMap = RequestUtils.getAssembleBaseInputParameters();
        if (map != null) {
            paramsMap.putAll(map);
        }
//        paramsMap.put(SIGN_CODE, sign(paramsMap));
        JSONObject jsonObject = new JSONObject(paramsMap);
        L.i("request", jsonObject.toString());
        return RequestBody.create(JSON, jsonObject.toString());
    }

    /**
     * 返回Key
     */
    public static String getKey(String methods, LinkedHashMap<String, Object> map) {
        return methods + new JSONObject(map).toString();
    }



    /**
     * 拼接所有参数，并进行MD5加密
     */
    private static String getSignCode(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        // 拼接基本参数
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if (!"function".equals(key) && !"signCode".equals(key)) {
                sb.append(map.get(key));
            }
        }
        // 对拼接好的string数据进行MD5
        return AESCryptoUtilities.MD5(sb.toString());
    }

    /**
     * ras加密
     *
     * @param map 参数
     * @return 键值对拼接加密
     */
    private static String sign(LinkedHashMap<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        Set<String> keySet = map.keySet();
        List<String> keys = new ArrayList<>();
        keys.addAll(keySet);
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (!"function".equals(key) && !"signCode".equals(key)) {
                sb.append(key)
                        .append("=")
                        .append(map.get(key)).append(i == keySet.size() - 1 ? "" : "&");
            }
        }
        L.i(sb.toString());
//        return TraderRSAUtil.sign(sb.toString());
        return "";
    }

    private static String getSystemVersionName() {
        return "Android " + android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取当前客户端版本信息
     */
//    private static String getCurrentVersionName() {
//        String curVersionName = "";
//        try {
//            PackageInfo info = App.getInstance().getApplicationContext().getPackageManager().getPackageInfo(App.getInstance().getApplicationContext().getPackageName(), 0);
//            curVersionName = info.versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace(System.err);
//        }
//        return curVersionName;
//    }
}
