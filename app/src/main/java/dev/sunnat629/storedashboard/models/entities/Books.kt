package dev.sunnat629.storedashboard.models.entities

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(bookName)
        parcel.writeInt(price)
        parcel.writeInt(inStock)
        parcel.writeString(virtualStock)
        parcel.writeInt(requestPending)
        parcel.writeString(coverImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Books> {
        override fun createFromParcel(parcel: Parcel): Books {
            return Books(parcel)
        }

        override fun newArray(size: Int): Array<Books?> {
            return arrayOfNulls(size)
        }
    }
}