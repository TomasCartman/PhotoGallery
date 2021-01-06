package com.dashboarder.photogallery

import androidx.lifecycle.*
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
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()

    init {
        mutableSearchTerm.value = "planet"
        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) {
            searchTerm -> flickrFetchr.searchPhotos(searchTerm)
        }
    }

    fun fetchPhotos(query: String = "") {
        mutableSearchTerm.value = query
    }

    fun refresh(){
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }
}