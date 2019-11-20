package com.base.flyjiang.baseflyapp.http;

import com.base.flyjiang.baseflyapp.http.api.Apis;
import com.base.flyjiang.baseflyapp.http.response.HttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by flyjiang on 2019/8/2.
 */
public class RetrofitHelper implements HttpHelper {

    private Apis apiService;

    @Inject
    public RetrofitHelper(Apis ApiService) {
       this.apiService = ApiService;
    }

    @Override
    public Flowable<HttpResponse<Object>> fetchIndexData() {
        return null;
    }
}
