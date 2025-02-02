package com.dashboarder.photogallery.api

import androidx.paging.PagedList
import com.dashboarder.photogallery.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}