package com.github.batulovandrey.itunesfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.batulovandrey.itunesfinder.R;
import com.github.batulovandrey.itunesfinder.bean.Track;
import com.github.batulovandrey.itunesfinder.bean.TrackResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by batul0ve on 02.08.2017.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {
    private TrackResponse mTrackResponse;
    private TrackClickListener mClickListener;

    public TrackAdapter(TrackResponse trackResponse, TrackClickListener clickListener) {
        mTrackResponse = trackResponse;
        mClickListener = clickListener;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new TrackViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = mTrackResponse.getTracks().get(position);
        holder.mArtistNameTextView.setText(track.getArtistName());
        holder.mTrackNameTextView.setText(track.getTrackName());
        Picasso.with(holder.itemView.getContext())
                .load(track.getCoverUrl())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
                .into(holder.mCoverImageView);
    }

    @Override
    public int getItemCount() {
        return mTrackResponse.getTrackCount();
    }
}
