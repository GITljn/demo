package com.ljn.demo.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

// 一般都是在service加缓存
@Service
public class AlphaService {
    // cache类似于map，此处key为String，value为Integer
    private LoadingCache<String, Integer> alphaSumCache;

    @PostConstruct
    public void init() {
        alphaSumCache = Caffeine.newBuilder()
                // 缓存的大小
                .maximumSize(15)
                // 过期时间
                .expireAfterWrite(3600, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public @Nullable Integer load(@NonNull String key) throws Exception {
                        if (StringUtils.isBlank(key)) {
                            throw new IllegalArgumentException("参数不能为空或者空字符串");
                        }
                        // 操作二级缓存或者数据库，返回值会自动写入本地缓存
                        return 0;
                    }
                });
    }

    public Integer queryAlphaSum() {
        // 若缓存中查不到，回去本地缓存中找
        return alphaSumCache.get("key");
    }
}
