package com.dashboarder.photogallery.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest?method=flickr.interestingness.getList" +
        "&api_key=d1f3d5870144b98ad956a2754114d8d4" + "&format=json" +
        "&nojsoncallback=1" + "&extras=url_s")
    fun fetchPhotos(@Query("per_page") perPage: Int, @Query("page") pageNum: Int): Call<FlickrResponse>

    /*
    companion object {
        fun newInstance(): FlickrApi {
            val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.flickr.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(FlickrApi::class.java)
        }
    }
     */
}