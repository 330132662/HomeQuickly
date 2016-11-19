package com.example.taobaoo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * 用于请求聚合的新闻头条
 * Created by Jeff on 2016/8/17.
 */
public interface NewsApi {
    @GET("/toutiao/index")
    Call<NewsBean> reqNews(@Query("type") String type, @Query("key") String key);
}
