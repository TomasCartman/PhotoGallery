package com.dashboarder.photogallery.api

class ApiService(private val flickrApi: FlickrApi) {

    fun fetchPhotosSync( page: Int, perPage: Int,
                         onPrepared: () -> Unit,
                         onSuccess: (FlickrResponse?) -> Unit,
                         onError: (String) -> Unit) {

        val request = flickrApi.fetchPhotos(perPage, page)
        onPrepared()
        ApiHelper().syncRequest(request, onSuccess, onError)
    }

    fun fetchPhotosAsync( page: Int, perPage: Int,
                          onPrepared: () -> Unit,
                          onSuccess: (FlickrResponse?) -> Unit,
                          onError: (String) -> Unit) {

        val request = flickrApi.fetchPhotos(perPage, page)
        onPrepared()
        ApiHelper().asyncRequest(request, onSuccess, onError)
    }
}