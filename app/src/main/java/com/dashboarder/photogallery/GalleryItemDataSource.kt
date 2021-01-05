package com.dashboarder.photogallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dashboarder.photogallery.api.ApiService
import com.dashboarder.photogallery.api.NetworkState
import com.dashboarder.photogallery.api.PhotoResponse
import java.util.concurrent.Executor

private const val TAG = "GalleryItemDataSource"

class GalleryItemDataSource() : PageKeyedDataSource<Int, GalleryItem>() {
    val flickrFetchr = FlickrFetchr()
    var retry: (() -> Any)? = null
    private val api = flickrFetchr.flickrApi
    private val network = MutableLiveData<NetworkState>()
    private val initial = MutableLiveData<NetworkState>()
    private val apiService = ApiService(api)
    private val retryExecutor = Executor {  }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GalleryItem>) {
        val currentPage = 1
        val nextPage = currentPage + 1

        apiService.fetchPhotosSync( page = currentPage, perPage = params.requestedLoadSize,
            onPrepared = {
                postInitialState(NetworkState.LOADING)
            },
            onSuccess = {
                val photoResponse: PhotoResponse? = it?.photos
                var items = photoResponse?.galleryItems ?: emptyList()
                Log.d("LoadInitial", "$items")
                retry = null
                postInitialState(NetworkState.LOADED)
                callback.onResult(items, null, nextPage)
            },
            onError = {
                retry = {loadInitial(params, callback)}
                postInitialState(NetworkState.error(it))
                Log.d("Error", "$it")
            })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GalleryItem>
    ) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        apiService.fetchPhotosAsync(page = currentPage, perPage = params.requestedLoadSize,
            onPrepared = {
                postAfterState(NetworkState.LOADING)
            },
            onSuccess = {
                val photoResponse: PhotoResponse? = it?.photos
                var items = photoResponse?.galleryItems ?: emptyList()
                //val items = it?.galleryItems ?: emptyList()
                Log.d("LoadInitial", "$items")
                retry = null
                callback.onResult(items, nextPage)
                postAfterState(NetworkState.LOADED)
            },
            onError = {
                retry = { loadAfter(params, callback) }
                postAfterState(NetworkState.error(it))
            })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GalleryItem>
    ) {
        //ignore this
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let { retry ->
            retryExecutor.execute { retry() }
        }
    }

    private fun postInitialState(state: NetworkState) {
        network.postValue(state)
        initial.postValue(state)
    }

    private fun postAfterState(state: NetworkState) {
        network.postValue(state)
    }
}