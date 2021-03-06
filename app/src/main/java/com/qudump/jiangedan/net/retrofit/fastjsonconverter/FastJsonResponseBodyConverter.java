package com.qudump.jiangedan.net.retrofit.fastjsonconverter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by dili on 2016/8/3.
 */
public class FastJsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset;

    public FastJsonResponseBodyConverter() {
    }

    public FastJsonResponseBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        try {
            String input = value.string();
            return JSON.parseObject(input, type);
        } finally {
            value.close();
        }
    }
}
