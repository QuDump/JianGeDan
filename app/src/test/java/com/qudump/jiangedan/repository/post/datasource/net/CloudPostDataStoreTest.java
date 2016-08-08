package com.qudump.jiangedan.repository.post.datasource.net;

import com.qudump.jiangedan.net.bean.mapper.PostBeanDataMapper;
import com.qudump.jiangedan.net.service.post.PostApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by dili on 2016/8/4.
 */
public class CloudPostDataStoreTest {

    @Mock
    PostApiService postApiService;
    @Mock
    PostCache postCache;
    @Mock
    PostBeanDataMapper mapper;

    private CloudPostDataStore cloudPostDataStore;
    private static long FAKE_POST_ID = 12345;
    private static int FAKE_PAGE_ID = 12;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cloudPostDataStore = new CloudPostDataStore(postApiService,postCache,mapper);
    }

    @Test
    public void testPostList() throws Exception {
        cloudPostDataStore.postList(FAKE_PAGE_ID);
        verify(postApiService).postList(FAKE_PAGE_ID);
    }

    @Test
    public void testPostDetail() throws Exception {
        cloudPostDataStore.postDetail(FAKE_POST_ID);
        verify(postApiService).postDetailById(FAKE_POST_ID);
    }
}