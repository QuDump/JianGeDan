package com.qudump.jiangedan.net.retrofit.commentsjsonconverter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by dili on 2016/8/12.
 */
public class CommentJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset;
    private Retrofit retrofit;

    public CommentJsonResponseBodyConverter() {
    }

    public CommentJsonResponseBodyConverter(Type type, Charset charset, Retrofit retrofit) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return JSON.parseObject(value.string(), type);
        } finally {
            value.close();
        }
    }
}
