package com.mohamedmabrouk.estarta_task.source.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("image_ids")
    val imageIds: List<String?>?,
    @SerializedName("image_urls")
    val imageUrls: List<String?>?,
    @SerializedName("image_urls_thumbnails")
    val imageUrlsThumbnails: List<String?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("uid")
    val uid: String?
)