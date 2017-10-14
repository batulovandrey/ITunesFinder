package com.github.batulovandrey.itunesfinder.bean

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * @author Andrey Batulov on 14/10/2017
 */

@Parcelize
data class TrackResponse(@JsonProperty("resultCount") val trackCount: Int,
                         @JsonProperty("results") val tracks: List<Track>) : Parcelable