package com.example.bo.niabielv.http.api;

import com.example.bo.niabielv.bean.AccountBean;
import com.example.bo.niabielv.bean.PartsDetailsBean;
import com.example.bo.niabielv.bean.UploadBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/11/17/017.
 */

public interface ApiService {
//    /**
//     * 返回基类 用于提交之类的请求
//     */
//    @POST(C.END_URL)
//    Observable<ApiModel> request(@Body RequestBody body);
//

    /**
     * 返回JSON——测试用
     */
//    @POST("debit-web/sms/sendRegisterVCodeWithoutImageVCode")
//    Call<OptionInfo> test(@Body RequestBody body);

    /**
     * 上传账单
     */
    @POST("/WebAccount/UploadAccountServlet")
    Observable<UploadBean> uploadAccount(@Body RequestBody body);

    /**
     * 刷新账单列表
     */
    @GET("/WebAccount/SearchServlet")
    Observable<AccountBean> searchAccount();

    @POST("/WebAccount/CalculaServlet")
    Observable<List<PartsDetailsBean>> userDetails();

    @POST("/WebAccount/DeleteAccountServlet")
    Observable<UploadBean> deleteItem(@Body RequestBody body);

}
