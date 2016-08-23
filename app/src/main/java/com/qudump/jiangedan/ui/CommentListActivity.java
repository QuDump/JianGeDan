package com.qudump.jiangedan.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab_write)
    FloatingActionButton fabWrite;

    @Inject
    CommentListPresenter presenter;

    private long id;
    private String threadId;
    private CommentAdapter mAdapter;
    private boolean isPost;
    public static final String EXT_ID_KEY = "ext.id";
    public static final String EXT_IS_POST_KEY = "ext.isPost";
    private boolean hideToolbar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        ButterKnife.bind(this);
        ((BaseApplication)getApplication()).buildCommentComponent().plus(new CommentListActivityModule()).inject(this);
        init();
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(dy > 20) {
                hideToolbar = true;
            } else if(dy < -5) {
                hideToolbar = false;
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(hideToolbar){
                CommentListActivity.this.getSupportActionBar().hide();
            } else {
                CommentListActivity.this.getSupportActionBar().show();
            }
        }
    };

    void init(){
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(listener->{
            finish();
        });

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
        recyclerView.setOnScrollListener(scrollListener);

        presenter.setView(this);
        fabWrite.setOnClickListener(listener->{
            AlertDialog.Builder builder = new AlertDialog.Builder(CommentListActivity.this);
            builder.setTitle("回复评论");
            View layout = getLayoutInflater().inflate(R.layout.dialog_write_comment,null);
            builder.setView(layout);

            EditText etName = (EditText)layout.findViewById(R.id.et_name);
            EditText etEmail = (EditText)layout.findViewById(R.id.et_email);
            EditText etContent = (EditText)layout.findViewById(R.id.et_content);
            layout.findViewById(R.id.tv_reply_to).setVisibility(View.GONE);
            builder.setPositiveButton("发布",(dialog,which)->{
                Comment comment = new Comment();
                comment.setAuthorName(etName.getText().toString());
                comment.setAuthorEmail(etEmail.getText().toString());
                comment.setContent(etContent.getText().toString());
                comment.setThreadId(threadId);
                presenter.writeComment(comment);
            });
            builder.create().show();
        });
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
        threadId = commentList.get(0).getThreadId();
        recyclerView.setRefreshing(false);
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG);
    }
}
