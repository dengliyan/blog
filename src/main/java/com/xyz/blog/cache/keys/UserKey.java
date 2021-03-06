package com.xyz.blog.cache.keys;

public class UserKey extends CacheKeyBase {

    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(String prefix, int seconds) {
        super(prefix, seconds);
    }

    @Override
    public String getKey(String key) {
        return super.getKey(key);
    }

    @Override
    public Integer getSeconds() {
        return super.getSeconds();
    }

    public static UserKey TOKEN=new UserKey("user_token",1*24*3600);
}
