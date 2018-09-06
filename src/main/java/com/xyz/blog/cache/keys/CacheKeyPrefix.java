package com.xyz.blog.cache.keys;


public interface CacheKeyPrefix {
    public String getKey(String key);

    public Integer getSeconds();
}
