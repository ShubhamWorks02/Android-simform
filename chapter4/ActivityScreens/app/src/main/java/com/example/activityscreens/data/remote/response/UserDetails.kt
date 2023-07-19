package com.example.activityscreens.data.remote.response

//data class UserDetails(
//    val id: Int,
//    val email: String,
//    @SerializedName("first_name")
//    val firstName: String,
//    @SerializedName("last_name")
//    val lastName: String,
//    val avatar: String,
//)

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class UserDetails(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDetails> {
        override fun createFromParcel(parcel: Parcel): UserDetails {
            return UserDetails(parcel)
        }

        override fun newArray(size: Int): Array<UserDetails?> {
            return arrayOfNulls(size)
        }
    }
}
