package com.base.flyjiang.baseflyapp.http.cache.stategy;

import com.base.flyjiang.baseflyapp.http.cache.RxCache;
import com.base.flyjiang.baseflyapp.http.cache.data.CacheResult;
import java.lang.reflect.Type;
import io.reactivex.Observable;


/**
 * author : zchu
 * date   : 2017/10/11
 * desc   :
 */
public interface IObservableStrategy {

    <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key, Observable<T> source, Type type);
}
