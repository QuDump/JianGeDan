package com.qudump.jiangedan.net.bean.mapper;

import com.qudump.jiangedan.model.Joke;
import com.qudump.jiangedan.net.bean.JokeBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dili on 2016/8/8.
 */
public class JokeBeanMapper {

    @Inject
    public JokeBeanMapper() {
    }

    public Joke transform(JokeBean jokeBean) {
        Joke joke = null;
        if(null != jokeBean) {
            joke = new Joke();

            joke.setId(jokeBean.getComment_post_ID());
            joke.setCommentId(jokeBean.getComment_ID());
            joke.setContent(jokeBean.getComment_content());
            joke.setDate(jokeBean.getComment_date());
            joke.setLikeCounts(jokeBean.getVote_positive());
            joke.setDislikeCounts(jokeBean.getVote_negative());
            joke.setAuthorName(jokeBean.getComment_author());
            joke.setAuthorEmail(jokeBean.getComment_author_email());

        }

        return joke;
    }

    public List<Joke> transform(List<JokeBean> jokeBeans) {
        List<Joke> jokes = new ArrayList<>();
        if(null != jokeBeans) {
            for(JokeBean bean:jokeBeans) {
                Joke joke = transform(bean);
                if(null != joke) {
                    jokes.add(joke);
                }
            }
        }

        return jokes;
    }
}
