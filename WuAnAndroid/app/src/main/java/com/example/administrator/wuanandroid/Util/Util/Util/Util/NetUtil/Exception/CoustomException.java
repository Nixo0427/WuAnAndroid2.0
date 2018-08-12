package com.example.administrator.wuanandroid.Util.Util.Util.Util.NetUtil.Exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class CoustomException {

    /**
     * 未知的错误
     */
    public static final int UNKNOW_ERROR = 1000;

    /**
     * 解析时错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;


    public static ApiException handleException(Throwable e){
        ApiException ex;
        if(e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException){
            //解析错误
            ex = new ApiException(PARSE_ERROR,e.getMessage());
            return ex;
        }else if(e instanceof ConnectException){
            //网络错误
            ex = new ApiException(NETWORK_ERROR,e.getMessage());
            return ex;
        }else if(e instanceof UnknownHostException || e instanceof SocketTimeoutException){
            //链接错误
            ex = new ApiException(NETWORK_ERROR,e.getMessage());
            return ex;
        }else {
            //未知错误
            ex = new ApiException(UNKNOW_ERROR,e.getMessage());
            return ex;
        }
    }


}