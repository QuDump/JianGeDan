package com.qudump.jiangedan.repository.post.cache;

import com.qudump.jiangedan.repository.post.datasource.cache.DiskPostDataStore;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by dili on 2016/8/4.
 */
public class DiskPostDataStoreTest {

    @Mock
    PostCache mockPostCache;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DiskPostDataStore diskPostDataStore;
    private static final long FAKE_POST_ID = 1556;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        diskPostDataStore = new DiskPostDataStore(mockPostCache);
    }

    @Test
    public void testPostDetail() throws Exception {
        diskPostDataStore.postDetail(FAKE_POST_ID);
        Mockito.verify(mockPostCache).get(FAKE_POST_ID);
    }

    @Test
    public void testPostList() throws Exception {
        expectedException.expect(UnsupportedOperationException.class);
        diskPostDataStore.postList(Mockito.anyInt());
    }
}