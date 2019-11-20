package com.base.flyjiang.baseflyapp.http.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *Created by flyjiang on 2019/8/2.
 */

public class RetrofitUtil {

    /**
     * 使用addHeader()不会覆盖之前设置的header,若使用header()则会覆盖之前的header
     * @return
     */
    public static Interceptor getRequestHeader() {
        Interceptor headerInterceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                builder.header("appid", "1");
                builder.header("timestamp", System.currentTimeMillis() + "");
                Request.Builder requestBuilder =builder.method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }

        };

        return headerInterceptor;
    }

}
