package com.github.saphyra.cache;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class CacheImpl extends AbstractCache<String, String> {
    private final Supplier<Optional<String>> source;

    @Override
    public Optional<String> load(String key) {
        return source.get();
    }
}
