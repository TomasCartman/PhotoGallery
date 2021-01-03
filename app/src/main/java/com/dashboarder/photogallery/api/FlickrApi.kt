package com.dashboarder.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    @GET("services/rest?method=flickr.interestingness.getList" +
        "&api_key=d1f3d5870144b98ad956a2754114d8d4" + "&format=json" +
        "&nojsoncallback=1" + "&extras=url_s")
    fun fetchPhotos(): Call<FlickrResponse>
}