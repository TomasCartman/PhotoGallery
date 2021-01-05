package com.dashboarder.photogallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class GalleryItemDataSourceFactory() : DataSource.Factory<Int, GalleryItem>() {
    val sourceLiveData = MutableLiveData<GalleryItemDataSource>()

    override fun create(): DataSource<Int, GalleryItem> {
        val latestSource = GalleryItemDataSource()
        Log.d("LatestSource", "$latestSource")
        sourceLiveData.postValue(latestSource)
        Log.d("SourceLiveData", "${sourceLiveData}")
        return latestSource
    }
}