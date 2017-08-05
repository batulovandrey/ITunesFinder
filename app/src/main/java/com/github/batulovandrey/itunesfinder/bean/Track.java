package com.github.batulovandrey.itunesfinder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.Objects;

/**
 * @author batul0ve on 01.08.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track implements Parcelable {
    public static final ClassCreator CREATOR = new ClassCreator();

    @NonNull
    private String mArtistName;

    @NonNull
    private String mTrackName;

    @NonNull
    private String mArtistViewUrl;

    @NonNull
    private String mTrackPreviewUrl;

    @NonNull
    private String mCoverUrl;

    private double mTrackPrice;

    public Track() {
        // empty Constructor needed by Jackson
    }

    protected Track(Parcel in) {
        mArtistName = in.readString();
        mTrackName = in.readString();
        mArtistViewUrl = in.readString();
        mTrackPreviewUrl = in.readString();
        mCoverUrl = in.readString();
        mTrackPrice = in.readDouble();
    }


    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mArtistName);
        dest.writeString(mTrackName);
        dest.writeString(mArtistViewUrl);
        dest.writeString(mTrackPreviewUrl);
        dest.writeString(mCoverUrl);
        dest.writeDouble(mTrackPrice);
    }

    @NonNull
    @JsonGetter("artistName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getArtistName() {
        return mArtistName;
    }

    @NonNull
    @JsonGetter("trackName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTrackName() {
        return mTrackName;
    }

    @NonNull
    @JsonGetter("artistViewUrl")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getArtistViewUrl() {
        return mArtistViewUrl;
    }

    @NonNull
    @JsonGetter("previewUrl")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTrackPreviewUrl() {
        return mTrackPreviewUrl;
    }

    @NonNull
    @JsonGetter("artworkUrl100")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCoverUrl() {
        return mCoverUrl;
    }

    @JsonGetter("trackPrice")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double getTrackPrice() {
        return mTrackPrice;
    }

    @JsonSetter("artistName")
    public void setArtistName(@NonNull String artistName) {
        mArtistName = artistName;
    }

    @JsonSetter("trackName")
    public void setTrackName(@NonNull String trackName) {
        mTrackName = trackName;
    }

    @JsonSetter("artistViewUrl")
    public void setArtistViewUrl(@NonNull String artistViewUrl) {
        mArtistViewUrl = artistViewUrl;
    }

    @JsonSetter("previewUrl")
    public void setTrackPreviewUrl(@NonNull String trackPreviewUrl) {
        mTrackPreviewUrl = trackPreviewUrl;
    }

    @JsonSetter("artworkUrl100")
    public void setCoverUrl(@NonNull String coverUrl) {
        mCoverUrl = coverUrl;
    }

    @JsonSetter("trackPrice")
    public void setTrackPrice(double trackPrice) {
        mTrackPrice = trackPrice;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Double.compare(track.mTrackPrice, mTrackPrice) == 0 &&
                Objects.equal(mArtistName, track.mArtistName) &&
                Objects.equal(mTrackName, track.mTrackName) &&
                Objects.equal(mArtistViewUrl, track.mArtistViewUrl) &&
                Objects.equal(mTrackPreviewUrl, track.mTrackPreviewUrl) &&
                Objects.equal(mCoverUrl, track.mCoverUrl);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mArtistName, mTrackName, mArtistViewUrl, mTrackPreviewUrl, mCoverUrl, mTrackPrice);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mArtistName", mArtistName)
                .add("mTrackName", mTrackName)
                .add("mArtistViewUrl", mArtistViewUrl)
                .add("mTrackPreviewUrl", mTrackPreviewUrl)
                .add("mCoverUrl", mCoverUrl)
                .add("mTrackPrice", mTrackPrice)
                .toString();
    }

    public static final class ClassCreator implements Creator<Track> {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    }

}