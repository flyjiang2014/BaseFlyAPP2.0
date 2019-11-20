package com.base.flyjiang.baseflyapp.http;

import com.base.flyjiang.baseflyapp.http.response.HttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by flyjiang on 2019/8/2.
 */

public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;

    @Inject
    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }



    public Flowable<HttpResponse<Object>> fetchIndexData() {
        return mHttpHelper.fetchIndexData();
    }
}
