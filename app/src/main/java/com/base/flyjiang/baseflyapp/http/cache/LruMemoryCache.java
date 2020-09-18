package com.base.flyjiang.baseflyapp.http.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.base.flyjiang.baseflyapp.http.cache.utils.LogUtils;
import com.base.flyjiang.baseflyapp.http.cache.utils.MemorySizeOf;
import com.base.flyjiang.baseflyapp.http.cache.utils.Occupy;
import java.util.HashMap;

/**
 * Created by Chu on 2016/9/10.
 */
public class LruMemoryCache {
    private LruCache<String, Object> mCache;
    private HashMap<String, Integer> memorySizeMap;//储存初次加入缓存的size，规避对象在内存中大小变化造成的测量出错
    private HashMap<String, Long> timestampMap;
    private Occupy occupy;

    public LruMemoryCache(final int cacheSize) {
        memorySizeMap = new HashMap<>();
        timestampMap = new HashMap<>();
        byte to = 0;
        byte t4 = 4;
        occupy = new Occupy(to, to, t4);
        mCache = new LruCache<String, Object>(cacheSize) {
            @Override
            protected int sizeOf(String key, Object value) {
                Integer integer = memorySizeMap.get(key);
                if (integer == null) {
                    integer = countSize(value);
                    memorySizeMap.put(key, integer);
                }
                return integer;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Object oldValue, Object newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                memorySizeMap.remove(key);
                timestampMap.remove(key);
            }
        };
    }

    public <T> CacheHolder<T> load(String key) {
        T value = (T) mCache.get(key);
        if (value != null) {
            return new CacheHolder<>(value, timestampMap.get(key));
        }
        return null;
    }

    public <T> boolean save(String key, T value) {
        if (null != value) {

            mCache.put(key, value);
            timestampMap.put(key, System.currentTimeMillis());
        }
        return true;
    }

    public boolean containsKey(String key) {
        return mCache.get(key) != null;
    }

    /**
     * 删除缓存
     */
    public boolean remove(String key) {
        Object remove = mCache.remove(key);
        if (remove != null) {
            memorySizeMap.remove(key);
            timestampMap.remove(key);
            return true;
        }
        return false;
    }

    public void clear() {
        mCache.evictAll();
    }

    protected int countSize(Object value) {
        if (value == null) {
            return 0;
        }

        //  更优良的内存大小算法
        int size;
        if (value instanceof Bitmap) {
            LogUtils.debug("Bitmap");
            size = MemorySizeOf.sizeOf((Bitmap) value);
        } else {
            size = occupy.occupyof(value);
        }
        LogUtils.debug("size=" + size + " value=" + value);
        return size > 0 ? size : 1;
    }

}
