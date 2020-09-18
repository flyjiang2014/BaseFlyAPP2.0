package com.base.flyjiang.baseflyapp.http.cache.stategy;

import com.base.flyjiang.baseflyapp.http.cache.CacheTarget;
import com.base.flyjiang.baseflyapp.http.cache.RxCache;
import com.base.flyjiang.baseflyapp.http.cache.RxCacheHelper;
import com.base.flyjiang.baseflyapp.http.cache.data.CacheResult;
import org.reactivestreams.Publisher;
import java.lang.reflect.Type;
import java.util.Arrays;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * 先缓存，后网络
 * 作者: 赵成柱 on 2016/9/12 0012.
 */
public final class CacheAndRemoteStrategy implements IStrategy {
    private boolean isSync;

    public CacheAndRemoteStrategy() {
        isSync = false;
    }

    public CacheAndRemoteStrategy(boolean isSync) {
        this.isSync = isSync;
    }

    @Override
    public <T> Observable<CacheResult<T>> execute(RxCache rxCache, String key, Observable<T> source, Type type) {
        Observable<CacheResult<T>> cache = RxCacheHelper.loadCache(rxCache, key, type, true);
        Observable<CacheResult<T>> remote;
        if (isSync) {
            remote = RxCacheHelper.loadRemoteSync(rxCache, key, source, CacheTarget.MemoryAndDisk, false);
        } else {
            remote = RxCacheHelper.loadRemote(rxCache, key, source, CacheTarget.MemoryAndDisk, false);
        }
        return Observable.concatDelayError(Arrays.asList(cache, remote))
                .filter(new Predicate<CacheResult<T>>() {
                    @Override
                    public boolean test(@NonNull CacheResult<T> result) throws Exception {
                        return result.getData() != null;
                    }
                });
    }

    @Override
    public <T> Publisher<CacheResult<T>> flow(RxCache rxCache, String key, Flowable<T> source, Type type) {
        Flowable<CacheResult<T>> cache = RxCacheHelper.loadCacheFlowable(rxCache, key, type, true);
        Flowable<CacheResult<T>> remote;
        if (isSync) {
            remote = RxCacheHelper.loadRemoteSyncFlowable(rxCache, key, source, CacheTarget.MemoryAndDisk, false);
        } else {
            remote = RxCacheHelper.loadRemoteFlowable(rxCache, key, source, CacheTarget.MemoryAndDisk, false);
        }
        return Flowable.concatDelayError(Arrays.asList(cache, remote))
                .filter(new Predicate<CacheResult<T>>() {
                    @Override
                    public boolean test(@NonNull CacheResult<T> result) throws Exception {
                        return result.getData() != null;
                    }
                });
    }
}
