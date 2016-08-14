package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.net.retrofit.commentsjsonconverter.CommentJsonConverterFactory;
import com.qudump.jiangedan.net.retrofit.fastjsonconverter.FastJsonConverterFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Response;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public class CommentApiServiceImpl implements CommentApiService {

    @Inject
    public CommentApiServiceImpl() {
    }

    @Override
    public Observable<List<CommentNumberRespBean>> commentNumbers(String params) {
        List<CommentNumberRespBean> commentNumbers = new ArrayList<>();
        try {

            retrofit2.Response<List<CommentNumberRespBean>> resp = new Retrofit
                    .Builder()
                    .addConverterFactory(CommentJsonConverterFactory.create(params))
                    .baseUrl("http://jandan.duoshuo.com")
                    .build()
                    .create(CommentService.class)
                    .comments(params)
                    .execute();
            commentNumbers.addAll(resp.body());

        } catch (IOException e){
            return Observable.error( new Throwable("an error occurred when connecting the network"));
        }

        return Observable.just(commentNumbers);
    }

}
