package com.qudump.jiangedan.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.CommentListActivityModule;
import com.qudump.jiangedan.model.Comment;
import com.qudump.jiangedan.presenter.CommentListContract;
import com.qudump.jiangedan.presenter.CommentListPresenter;
import com.qudump.jiangedan.ui.adapter.CommentAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/11.
 */
public class CommentListActivity extends AppCompatActivity implements CommentListContract.View {

    @Bind(R.id.rv_content)
    IRecyclerView recyclerView;

    @Inject
    CommentListPresenter presenter;

    private long id;
    private CommentAdapter mAdapter;
    private boolean isPost;
    public static final String EXT_ID_KEY = "ext.id";
    public static final String EXT_IS_POST_KEY = "ext.isPost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        ButterKnife.bind(this);
        ((BaseApplication)getApplication()).buildCommentComponent().plus(new CommentListActivityModule()).inject(this);
        init();
    }

    void init(){
        if(null != getIntent()) {
            id = getIntent().getLongExtra(EXT_ID_KEY,0L);
            isPost = getIntent().getBooleanExtra(EXT_IS_POST_KEY,false);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommentAdapter(this);
        recyclerView.setIAdapter(mAdapter);
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isPost) {
                    presenter.loadPostComments(id);
                } else {
                    presenter.loadComments(id);
                }
            }
        });

        presenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isPost) {
            presenter.loadPostComments(id);
        } else {
            presenter.loadComments(id);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void renderView(List<Comment> commentList) {
        mAdapter.setData(commentList);
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG);
    }
}
