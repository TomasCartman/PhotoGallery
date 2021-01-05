package com.dashboarder.photogallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import com.dashboarder.photogallery.api.FlickrApi
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {
    val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    @WorkerThread
    fun fetchPhoto(url: String): Bitmap? {
        val response: Response<ResponseBody> = flickrApi.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        Log.i(TAG, "Decoded bitmap=$bitmap from Response=$response")
        return bitmap
    }
}