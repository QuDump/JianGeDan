package com.qudump.jiangedan.presenter;

import com.qudump.jiangedan.interactor.GetOtherComments;
import com.qudump.jiangedan.interactor.GetPostComments;
import com.qudump.jiangedan.interactor.PostComment;
import com.qudump.jiangedan.model.Comment;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dili on 2016/8/11.
 */
public class CommentListPresenter implements CommentListContract.Presenter {

    private GetPostComments getPostComments;
    private GetOtherComments getOtherComments;
    private PostComment postComment;
    private CommentListContract.View view;
    private long postId;

    @Inject
    public CommentListPresenter(GetPostComments getPostComments, GetOtherComments getOtherComments, PostComment postComment) {
        this.getPostComments = getPostComments;
        this.getOtherComments = getOtherComments;
        this.postComment = postComment;
    }

    @Override
    public void loadComments(long id) {
        postId = id;
        getOtherComments.setId(id).execute(new GetCommentsSubscriber());
    }

    @Override
    public void loadPostComments(long id) {
        postId = id;
        getPostComments.setId(id).execute(new GetCommentsSubscriber());
    }

    @Override
    public void writeComment(Comment comment) {
        postComment.setComment(comment).execute(new PostCommentSubscriber());
    }

    @Override
    public void setView(CommentListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        getPostComments.unsubscribe();
    }

    public class GetCommentsSubscriber extends Subscriber<List<Comment>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Comment> commentList) {
            view.renderView(commentList);
            if(null == commentList || 0 == commentList.size()) {
                view.showErrMsg("暂时还没有评论哦");
            }
        }
    }

    public class PostCommentSubscriber extends Subscriber<Boolean> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            view.showErrMsg("network error !");
        }

        @Override
        public void onNext(Boolean aBoolean) {
            loadComments(postId);
        }
    }
}
