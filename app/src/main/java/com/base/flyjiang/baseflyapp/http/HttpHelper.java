package com.base.flyjiang.baseflyapp.http;


import com.base.flyjiang.baseflyapp.http.response.HttpResponse;

import io.reactivex.Flowable;

/**
 *Created by flyjiang on 2019/8/2.
 */

public interface HttpHelper {

    /*MainActivity*/
    Flowable<HttpResponse<Object>> fetchIndexData();

}
