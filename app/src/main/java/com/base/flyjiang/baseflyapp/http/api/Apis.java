package com.base.flyjiang.baseflyapp.http.api;

import com.base.flyjiang.baseflyapp.http.response.HttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by flyjiang on 2019/8/2.
 */

public interface Apis {

//    @GET("system/viewSystemOptions.do")  //获取系统参数
//    Flowable<HttpResponse<SystemOptionsBean>> getSystemOptions();

    @GET("user/indexShow.do")  //获取主页信息       无参数get
    Flowable<HttpResponse<Object>> getIndexData();

    @POST("user/indexShow.do")  //获取主页信息    无参数post
    Flowable<HttpResponse<Object>> getIndexData02();

    @GET("user/login.do")  //手机登录    有参数get
    Flowable<HttpResponse<Object>> doLogin(
            @Query("mobile") String mobile,
            @Query("password") String password);

    @GET("user/login.do")  //手机登录    get请求 map
    Flowable<HttpResponse<Object>> doLogin01(@QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("user/login.do")  //手机登录  有参数post
    Flowable<HttpResponse<Object>> doLogin02(
            @Field("mobile") String mobile,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("user/login.do")  //手机登录   post请求 map
    Flowable<HttpResponse<Object>> doLogin03(@FieldMap Map<String, Object> map);

    @Multipart
    @POST("MyUploadServer/servlet/UpLoadFileServlet")
    Flowable<HttpResponse<Object>> postUpLoadFile(@Part() MultipartBody.Part requestBody);

    @Multipart
    @POST("MyUploadServer/servlet/MyUploadServlet")
    Flowable<HttpResponse<Object>> uploadFilesByPart(@Part() List<MultipartBody.Part> parts);

}
