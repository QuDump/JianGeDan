package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.CommentNumberRespBean;
import com.qudump.jiangedan.net.retrofit.fastjsonconverter.FastJsonConverterFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
        return new Retrofit
                .Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://jandan.duoshuo.com")
                .build()
                .create(CommentService.class)
                .comments(params)
                .flatMap(resp->{
                    if(resp.getCode() != 0) {
                        return Observable.error(new Throwable());
                    } else {
                        try {
                            String jsonStr = resp.getResponse();
                            JSONObject jsonObject = new JSONObject(jsonStr);
                            String[] commentIDs = params.split("\\,");

                            for(String Id:commentIDs) {
                                if(!jsonObject.isNull(Id)) {
                                    CommentNumberRespBean bean = new CommentNumberRespBean();
                                    if(jsonObject.getJSONObject(Id).has(CommentNumberRespBean.COMMENTS)) {
                                        bean.setComments(jsonObject.getJSONObject(Id).getInt(CommentNumberRespBean.COMMENTS));
                                    }
                                    if(jsonObject.getJSONObject(Id).has(CommentNumberRespBean.THREAD_ID)) {
                                        bean.setThread_id(jsonObject.getJSONObject(Id).getString(CommentNumberRespBean.THREAD_ID));
                                    }
                                    if(jsonObject.getJSONObject(Id).has(CommentNumberRespBean.THREAD_KEY)) {
                                        bean.setThread_key(jsonObject.getJSONObject(Id).getString(CommentNumberRespBean.THREAD_KEY));
                                    }
                                    commentNumbers.add(bean);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return Observable.just(commentNumbers);
                    }
        });
    }

}
