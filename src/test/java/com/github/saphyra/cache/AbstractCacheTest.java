package com.github.saphyra.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractCacheTest {
    private static final String KEY = "key";
    private static final String VALUE = "value";
    @Mock
    private Supplier<String> source;

    @InjectMocks
    private CacheImpl underTest;

    @Test
    public void testGetShouldCallSource(){
        //GIVEN
        when(source.get()).thenReturn(VALUE);
        //WHEN
        Optional<String> result = underTest.get(KEY);
        //THEN
        verify(source).get();
        assertTrue(result.isPresent());
        assertEquals(VALUE, result.get());
    }

    @Test
    public void testGetShouldReturnEmptyOptionalWhenSourceReturnsNull(){
        //GIVEN
        when(source.get()).thenReturn(null);
        //WHEN
        Optional<String> result = underTest.get(KEY);
        //THEN
        verify(source).get();
        assertFalse(result.isPresent());
    }
}