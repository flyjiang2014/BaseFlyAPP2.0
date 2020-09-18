package com.base.flyjiang.baseflyapp.http.cache.stategy;


import com.base.flyjiang.baseflyapp.http.cache.RxCache;
import com.base.flyjiang.baseflyapp.http.cache.data.CacheResult;
import org.reactivestreams.Publisher;
import java.lang.reflect.Type;
import io.reactivex.Flowable;


/**
 * author : zchu
 * date   : 2017/10/11
 * desc   :
 */
public interface IFlowableStrategy {

    <T> Publisher<CacheResult<T>> flow(RxCache rxCache, String key, Flowable<T> source, Type type);
}
