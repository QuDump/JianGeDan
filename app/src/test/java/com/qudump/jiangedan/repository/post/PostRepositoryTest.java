package com.qudump.jiangedan.repository.post;

import com.qudump.jiangedan.repository.post.datasource.PostDataStore;
import com.qudump.jiangedan.repository.post.datasource.PostDataStoreFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

/**
 * Created by dili on 2016/8/4.
 */
public class PostRepositoryTest {

    @Mock
    PostDataStoreFactory mockPostDataStoreFactory;
    @Mock
    PostDataStore mockPostDataStore;

    private PostRepository postRepository;
    private static final int FAKE_PAGE_ID = 12;
    private static final long FAKE_POST_ID = 1234;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postRepository = new PostRepository(mockPostDataStoreFactory);

        given(mockPostDataStoreFactory.create(anyInt())).willReturn(mockPostDataStore);
        given(mockPostDataStoreFactory.createCloudDataStore()).willReturn(mockPostDataStore);
    }

    @Test
    public void testGetPostList() throws Exception {
        postRepository.posts(FAKE_PAGE_ID);
        verify(mockPostDataStoreFactory).createCloudDataStore();
        verify(mockPostDataStore).postList(FAKE_PAGE_ID);
    }

    @Test
    public void testGetPostDetail() throws Exception {
        postRepository.post(FAKE_POST_ID);

        verify(mockPostDataStoreFactory).create(FAKE_POST_ID);
        verify(mockPostDataStore).postDetail(FAKE_POST_ID);
    }
}