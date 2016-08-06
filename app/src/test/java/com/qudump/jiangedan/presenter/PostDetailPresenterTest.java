package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetPostDetail;
import com.qudump.jiangedan.model.Post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by qidong on 2016/8/6.
 */
public class PostDetailPresenterTest {
    @Mock
    GetPostDetail mockGetPostDetail;
    @Mock
    PostDetailContract.View mockView;
    @Mock
    Post post;
    @Captor
    ArgumentCaptor<PostDetailPresenter.PostDetailSubscriber> argumentCaptor;

    private PostDetailPresenter presenter;
    private static final long FAKE_POST_ID = 11234;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PostDetailPresenter(mockGetPostDetail);
        presenter.setView(mockView);
        when(post.getId()).thenReturn(FAKE_POST_ID);
        when(mockGetPostDetail.setPostId(FAKE_POST_ID)).thenReturn(mockGetPostDetail);

    }

    @Test
    public void testLoadPost() throws Exception {

        presenter.loadPost(post);
        verify(mockGetPostDetail).setPostId(FAKE_POST_ID);
        verify(mockGetPostDetail).execute(argumentCaptor.capture());
        argumentCaptor.getValue().onNext(post);
        verify(mockView).renderView(post);
    }
}