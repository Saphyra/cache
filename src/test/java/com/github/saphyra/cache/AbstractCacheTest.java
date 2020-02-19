package com.github.saphyra.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbstractCacheTest {
    private static final String KEY = "key";
    private static final String VALUE = "value";
    @Mock
    private Supplier<Optional<String>> source;

    @InjectMocks
    private CacheImpl underTest;

    @Test
    public void testGetShouldCallSource() {
        //GIVEN
        when(source.get()).thenReturn(Optional.of(VALUE));
        //WHEN
        Optional<String> result = underTest.get(KEY);
        underTest.get(KEY);
        //THEN
        verify(source, times(1)).get();
        assertTrue(result.isPresent());
        assertEquals(VALUE, result.get());
    }

    @Test
    public void testGetShouldReturnEmptyOptionalWhenSourceReturnsNull() {
        //GIVEN
        when(source.get()).thenReturn(Optional.empty());
        //WHEN
        Optional<String> result = underTest.get(KEY);
        //THEN
        verify(source).get();
        assertFalse(result.isPresent());
    }
}