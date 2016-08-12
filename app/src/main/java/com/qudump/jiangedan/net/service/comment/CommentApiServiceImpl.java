package com.qudump.jiangedan.net.service.comment;

import com.qudump.jiangedan.net.bean.CommentNumberRespBean;

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

            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl("http://jandan.duoshuo.com")
                    .build();
            String threadName = Thread.currentThread().getName();

            Response response = retrofit
                    .create(CommentService.class)
                    .comments(params)
                    .execute()
                    .raw();


            String jsonStr = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonStr).getJSONObject("response");
            String[] commentIDs = response.request().url().queryParameter("threads").split("\\,");

            for(String Id:commentIDs) {
                if(!jsonObject.isNull(Id)) {
                    CommentNumberRespBean bean = new CommentNumberRespBean();
                    bean.setComments(jsonObject.getJSONObject(Id).getInt(CommentNumberRespBean.COMMENTS));
                    bean.setThread_id(jsonObject.getJSONObject(Id).getString(CommentNumberRespBean.THREAD_ID));
                    bean.setThread_key(jsonObject.getJSONObject(Id).getString(CommentNumberRespBean.THREAD_KEY));
                    commentNumbers.add(bean);
                } else {
                    commentNumbers.add(new CommentNumberRespBean(0,"0", "0"));
                }
            }


        } catch (IOException e){
            return Observable.error( new Throwable("an error occurred when connecting the network"));
        } catch (JSONException e) {
            return Observable.error( new Throwable("convert error"));
        }

        return Observable.just(commentNumbers);
    }

}
