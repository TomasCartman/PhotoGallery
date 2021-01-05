package com.dashboarder.photogallery.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FlickrApi {

    @GET("services/rest?method=flickr.interestingness.getList" +
        "&api_key=d1f3d5870144b98ad956a2754114d8d4" + "&format=json" +
        "&nojsoncallback=1" + "&extras=url_s")
    fun fetchPhotos(@Query("per_page") perPage: Int, @Query("page") pageNum: Int): Call<FlickrResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}