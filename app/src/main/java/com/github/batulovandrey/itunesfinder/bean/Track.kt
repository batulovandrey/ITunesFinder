package com.github.batulovandrey.itunesfinder.bean

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * @author Andrey Batulov on 14/10/2017
 */

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Track(@JsonProperty("artistName") val artistName: String?,
                 @JsonProperty("trackName") val trackName: String?,
                 @JsonProperty("artistViewUrl") val artistViewUrl: String?,
                 @JsonProperty("previewUrl") val trackPreviewUrl: String?,
                 @JsonProperty("artworkUrl100") val coverUrl: String?,
                 @JsonProperty("trackPrice") val trackPrice: Double?) : Parcelable