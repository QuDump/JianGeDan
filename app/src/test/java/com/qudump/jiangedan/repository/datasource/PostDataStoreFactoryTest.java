package com.qudump.jiangedan.repository.datasource;

import com.qudump.jiangedan.cache.PostCache;
import com.qudump.jiangedan.net.service.post.PostApiService;
import com.qudump.jiangedan.repository.post.datasource.PostDataStore;
import com.qudump.jiangedan.repository.post.datasource.PostDataStoreFactory;
import com.qudump.jiangedan.repository.post.datasource.cache.DiskPostDataStore;
import com.qudump.jiangedan.repository.post.datasource.net.CloudPostDataStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dili on 2016/8/4.
 */
public class PostDataStoreFactoryTest {

    private static long FAKE_POST_ID = 123;
    private PostDataStoreFactory postDataStoreFactory;

    @Mock
    PostCache mockPostCache;

    @Mock
    PostApiService mockRetrofit;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postDataStoreFactory = new PostDataStoreFactory(mockPostCache);
    }

    @Test
    public void testCreateDiskDataStore() throws Exception {
        when(mockPostCache.isCached(FAKE_POST_ID)).thenReturn(true);
        when(mockPostCache.isExpired()).thenReturn(false);

        PostDataStore postDataStore = postDataStoreFactory.create(FAKE_POST_ID);
        assertThat(postDataStore,is(notNullValue()));
        assertThat(postDataStore,is(instanceOf(DiskPostDataStore.class)));

        verify(mockPostCache).isCached(FAKE_POST_ID);
        verify(mockPostCache).isExpired();
    }

    @Test
    public void testCreateCloudDataStore() throws Exception {
        when(mockPostCache.isCached(FAKE_POST_ID)).thenReturn(true);
        when(mockPostCache.isExpired()).thenReturn(false);

        PostDataStore postDataStore = postDataStoreFactory.create(FAKE_POST_ID);
        assertThat(postDataStore,is(notNullValue()));
        assertThat(postDataStore,is(instanceOf(CloudPostDataStore.class)));

        verify(mockPostCache).isExpired();
    }
}