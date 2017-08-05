package com.github.batulovandrey.itunesfinder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by batul0ve on 01.08.2017.
 */

public class TrackResponse implements Parcelable {
    public static final ClassCreator CREATOR = new ClassCreator();

    private int mTrackCount;

    @NonNull
    private List<Track> mTracks;

    public TrackResponse() {
        // empty constructor needed by Jackson
    }

    protected TrackResponse(Parcel in) {
        mTrackCount = in.readInt();
        mTracks = new ArrayList<>();
        in.readList(mTracks, null);
    }


    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTrackCount);
        dest.writeList(mTracks);
    }

    @JsonGetter("resultCount")
    public int getTrackCount() {
        return mTrackCount;
    }

    @JsonSetter("resultCount")
    public void setTrackCount(int trackCount) {
        mTrackCount = trackCount;
    }

    @NonNull
    @JsonGetter("results")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Track> getTracks() {
        return mTracks;
    }

    @JsonSetter("results")
    public void setTracks(@NonNull List<Track> tracks) {
        mTracks = tracks;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackResponse that = (TrackResponse) o;
        return mTrackCount == that.mTrackCount &&
                Objects.equal(mTracks, that.mTracks);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mTrackCount, mTracks);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "TrackResponse{" +
                "mTrackCount=" + mTrackCount +
                ", mTracks=" + mTracks +
                '}';
    }

    private static final class ClassCreator implements Creator<TrackResponse> {
        @Override
        public TrackResponse createFromParcel(Parcel in) {
            return new TrackResponse(in);
        }

        @Override
        public TrackResponse[] newArray(int size) {
            return new TrackResponse[size];
        }
    }
}