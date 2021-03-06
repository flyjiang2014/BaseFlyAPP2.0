package com.base.flyjiang.baseflyapp.http.cache;

/**
 * 缓存目标
 * 作者: Chu on 2016/9/9
 */
public enum CacheTarget {
    Memory,
    Disk,
    MemoryAndDisk;

    public boolean supportMemory() {
        return this==Memory || this== MemoryAndDisk;
    }

    public boolean supportDisk() {
        return this==Disk || this== MemoryAndDisk;
    }

}
