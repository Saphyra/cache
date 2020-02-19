package com.github.saphyra.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractCache<K, T> {
    @Setter
    protected Cache<K, Optional<T>> cache = CacheBuilder.newBuilder().build();

    protected abstract Optional<T> load(K key);

    protected Optional<T> get(K key) {
        try {
            return cache.get(key, () -> load(key));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void invalidate(K key) {
        cache.invalidate(key);
    }

    public void clear() {
        cache.invalidateAll();
    }
}
