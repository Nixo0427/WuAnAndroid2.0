package com.example.administrator.wuanandroid.Util.NetUtil.Response;



import com.example.administrator.wuanandroid.Util.NetUtil.Exception.ApiException;
import com.example.administrator.wuanandroid.Util.NetUtil.Exception.CoustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;



public class ResponseTransformer {

    public static <T> ObservableTransformer<Response<T>,T> handleResult(){
        return upstream -> upstream
                .onErrorResumeNext( new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }

    /**
     * 非服务器产生的异常，比如本地无网络请求  Json数据解析错误等。
     * @param <T>
     */

    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends Response<T>>> {

        @Override
        public ObservableSource<? extends Response<T>> apply(Throwable throwable) throws Exception {
            return Observable.error(CoustomException.handleException(throwable));
        }
    }


    /**
     * 服务器返回的数据解析，封装为Bean；
     * 正常服务器返回手和服务器可能返回的Exception；
     */

    private static class ResponseFunction<T> implements Function<Response<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Response<T> tResponse) throws Exception {
            int code = tResponse.getInfoCode();
            String message = tResponse.getInfoText();
            int niceCode = 200;
            if (code == niceCode) {
                return Observable.just(tResponse.getData());
            } else {
                return Observable.error(new ApiException(code, message));
            }
        }
    }


}
