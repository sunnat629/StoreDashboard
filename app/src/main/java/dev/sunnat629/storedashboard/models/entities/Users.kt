package dev.sunnat629.storedashboard.models.entities

import com.google.gson.annotations.SerializedName

data class Users(

    @SerializedName("name")
    val name: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("contact_person")
    val contactPerson: Contacts,

    @SerializedName("vendor_in_charge")
    val vendorInCharge: Contacts
)