package dev.sunnat629.storedashboard.models.entities

import com.google.gson.annotations.SerializedName

data class Books(

    @SerializedName("id")
    val id: Int,

    @SerializedName("book_name")
    val bookName: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("in_stock")
    val inStock: Int,

    @SerializedName("virtual_stock")
    val virtualStock: String,

    @SerializedName("request_pending")
    val requestPending: Int,

    @SerializedName("cover_image")
    val coverImage: String
)