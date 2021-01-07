package com.dashboarder.photogallery

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData

class PhotoGalleryViewModel(private val app: Application) : AndroidViewModel(app) {
    private val myPagingConfig = Config(
        pageSize = 100,
        prefetchDistance = 500,
        enablePlaceholders = true
    )

    private val dataSourceFactory = GalleryItemDataSourceFactory()
    val galleryItemList = dataSourceFactory.toLiveData(myPagingConfig)
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    val searchTerm: String
        get() = mutableSearchTerm.value ?: ""
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()

    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)
        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) {
            searchTerm -> if(searchTerm.isBlank()) {
                flickrFetchr.searchPhotos("planets")
            } else {
                flickrFetchr.searchPhotos(searchTerm)
            }
        }
    }

    fun fetchPhotos(query: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }

    fun refresh(){
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }
}