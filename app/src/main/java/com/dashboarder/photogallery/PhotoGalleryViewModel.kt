package com.dashboarder.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData

class PhotoGalleryViewModel : ViewModel() {
    private val myPagingConfig = Config(
        pageSize = 100,
        prefetchDistance = 500,
        enablePlaceholders = true
    )

    private val dataSourceFactory = GalleryItemDataSourceFactory()
    val galleryItemList = dataSourceFactory.toLiveData(myPagingConfig)

    fun refresh(){
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }
}