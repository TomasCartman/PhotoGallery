package com.dashboarder.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dashboarder.photogallery.api.FlickrApi
import com.dashboarder.photogallery.api.FlickrResponse
import com.dashboarder.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {
    val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    /*
    fun fetchPhotos(page: Int, pageSize: Int): MutableList<GalleryItem> {
        val responseLiveData: MutableLiveData<PagedList<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<FlickrResponse> = flickrApi.fetchPhotos(page, pageSize)
        var otherThing: MutableList<GalleryItem> = MutableList()

        flickrRequest.enqueue(object : Callback<FlickrResponse> {
            override fun onResponse(call: Call<FlickrResponse>, response: Response<FlickrResponse>) {
                Log.d(TAG, "Response received")
                val flickrResponse: FlickrResponse? = response.body()
                val photoResponse: PhotoResponse? = flickrResponse?.photos
                var galleryItems: MutableList<GalleryItem> = photoResponse?.galleryItems ?: mutableListOf()

                //responseLiveData.value = galleryItems
                otherThing = galleryItems
            }

            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
        })

        return otherThing
    }

     */
}