package com.serbladev.myplayer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaItem(val id: Int, val title: String, val url: String, val type: Type): Parcelable {
    enum class Type { PHOTO, VIDEO}



}