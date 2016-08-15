package com.qudump.jiangedan.repository.joke;

import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.net.bean.CommentNumberBean;
import com.qudump.jiangedan.repository.joke.datasource.JokeDataStore;
import com.qudump.jiangedan.repository.joke.datasource.JokeDataStoreFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by dili on 2016/8/3.
 */
public class JokeRepository {
    private JokeDataStoreFactory jokeDataStoreFactory;
    private List<Joke> mJokes;

    @Inject
    public JokeRepository(JokeDataStoreFactory jokeDataStoreFactory) {
        this.jokeDataStoreFactory = jokeDataStoreFactory;
    }

    public Observable<List<Joke>> jokes(int page){
        JokeDataStore jokeDataStore = jokeDataStoreFactory.createCloudDataStore();
        return jokeDataStore
                .jokes(page)
                .doOnNext(jokes->mJokes = jokes)
                .flatMap(jokes->queryCommentCounts(jokeDataStore,getCommentParams(jokes)))
                .flatMap(commentNumberRespBeen->Observable.just(appendCommentCountToPicModel(mJokes,commentNumberRespBeen)));
    }


    private String getCommentParams(List<Joke> jokes) {
        StringBuilder params = new StringBuilder();
        for(Joke joke:jokes) {
            String item = "comment-"+joke.getCommentId()+",";
            params.append(item);
        }

        return params.toString();
    }

    private Observable<List<CommentNumberBean>> queryCommentCounts(JokeDataStore dataStore, String params) {
        return dataStore.commentNumbers(params);
    }

    private List<Joke> appendCommentCountToPicModel(List<Joke> jokes, List<CommentNumberBean> commentBeans) {
        for(Joke joke:jokes) {
            for(CommentNumberBean bean:commentBeans){
                if(joke.getCommentId() == bean.getCommentId()) {
                    joke.setComments(bean.getComments());
                    continue;
                }
            }
        }
        return jokes;
    }

}
