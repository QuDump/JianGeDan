package com.qudump.jiangedan.net.retrofit.commentsjsonconverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by dili on 2016/8/12.
 */
public class CommentJsonConverterFactory extends Converter.Factory{
    private Charset charset;
    private static final Charset UTF_8  = Charset.forName("UTF-8");
    private String params;

    public static CommentJsonConverterFactory create() {

        return create(UTF_8,"");
    }

    public static CommentJsonConverterFactory create(String params) {

        return create(UTF_8,params);
    }

    public static CommentJsonConverterFactory create(Charset charset) {
        return new CommentJsonConverterFactory(charset,"");
    }

    public static CommentJsonConverterFactory create(Charset charset,String params) {
        return new CommentJsonConverterFactory(charset,params);
    }

    public CommentJsonConverterFactory(Charset charset,String params) {
        this.params = params;
        this.charset = charset;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return new CommentJsonRequestBodyConverter<>(type, charset);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new CommentJsonResponseBodyConverter<>(type, charset,params);
    }
}
