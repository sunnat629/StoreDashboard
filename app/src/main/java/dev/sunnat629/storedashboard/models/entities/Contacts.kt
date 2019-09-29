package dev.sunnat629.storedashboard.models.entities

import com.google.gson.annotations.SerializedName

data class Contacts (

    @SerializedName("primary")
    val primary: ContactsDetails,

    @SerializedName("secondary")
    val secondary: ContactsDetails
)

data class ContactsDetails (

    @SerializedName("name")
    val name: String,

    @SerializedName("mobile")
    val mobileNumber: String?,

    @SerializedName("email")
    val email: String?
)