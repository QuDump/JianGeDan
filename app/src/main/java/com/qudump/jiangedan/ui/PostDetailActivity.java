package com.qudump.jiangedan.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.injection.module.PostDetailActivityModule;
import com.qudump.jiangedan.model.Post;
import com.qudump.jiangedan.presenter.PostDetailContract;
import com.qudump.jiangedan.presenter.PostDetailPresenter;
import com.qudump.jiangedan.utils.String2TimeUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dili on 2016/8/3.
 */
public class PostDetailActivity extends AppCompatActivity implements PostDetailContract.View {
    @Bind(R.id.wv_content)
    WebView webView;

    public static final String EXT_POST_KEY = "ext.post";

    private Post mPost;

    @Inject
    PostDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);
        webView.getSettings().setJavaScriptEnabled(true);
        mPost = (Post)getIntent().getSerializableExtra(EXT_POST_KEY);

        ((BaseApplication)getApplication()).buildPostComponent().plus(new PostDetailActivityModule()).inject(this);

        presenter.setView(this);
        presenter.loadPost(mPost);
    }

    @Override
    public void renderView(Post post) {
        webView.loadData(getHtml(post), "text/html", "utf-8");
    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public void setPresenter(PostDetailContract.Presenter presenter) {

    }

    private static String getHtml(Post post) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px;\">");
        sb.append("<div id=\"pagewrapper\">");
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
        sb.append("<div id=\"maincontent\">");
        sb.append("<div class=\"post\">");
        sb.append("<div class=\"posthit\">");
        sb.append("<div class=\"postinfo\">");
        sb.append("<h2 class=\"thetitle\">");
        sb.append("<a>");
        sb.append(post.getTitle());
        sb.append("</a>");
        sb.append("</h2>");
        sb.append(post.getAuthor().getName() + " @ " + String2TimeUtil
                .dateString2GoodExperienceFormat(post.getDate()));
        sb.append("</div>");
        sb.append("<div class=\"entry\">");
        sb.append(post.getContent());
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
