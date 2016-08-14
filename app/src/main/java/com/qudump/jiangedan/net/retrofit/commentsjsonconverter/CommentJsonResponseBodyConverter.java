package com.qudump.jiangedan.net.retrofit.commentsjsonconverter;

import com.alibaba.fastjson.JSON;
import com.qudump.jiangedan.net.bean.CommentNumberRespBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by dili on 2016/8/12.
 */
public class CommentJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset;
    private String params;

    public CommentJsonResponseBodyConverter() {
    }

    public CommentJsonResponseBodyConverter(Type type, Charset charset, String params) {
        this.type = type;
        this.charset = charset;
        this.params = params;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        List<CommentNumberRespBean> commentNumbers = new ArrayList<>();
        try {
            String jsonStr = value.string();
            JSONObject jsonObject = new JSONObject(jsonStr).getJSONObject("response");
            String[] commentIDs = params.split("\\,");

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
            return (T)commentNumbers;
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            value.close();
            return null;
        }
    }
}
