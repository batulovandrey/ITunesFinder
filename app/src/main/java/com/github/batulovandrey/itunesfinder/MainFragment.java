package com.github.batulovandrey.itunesfinder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.batulovandrey.itunesfinder.adapter.TrackAdapter;
import com.github.batulovandrey.itunesfinder.adapter.TrackClickListener;
import com.github.batulovandrey.itunesfinder.bean.Track;
import com.github.batulovandrey.itunesfinder.bean.TrackResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Fragment to show list of items
 *
 * @author Andrey Batulov on 01/08/2017
 */
public class MainFragment extends Fragment implements TrackClickListener {

    private static final String EXTRA_TRACKS_RESPONSE = "extra_tracks_response";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private OnItemClickListener mListener;
    private TrackResponse mTrackResponse;
    private TrackAdapter mAdapter;

    public static MainFragment newInstance(TrackResponse response) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_TRACKS_RESPONSE, response);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnItemClickListener) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTrackResponse = getArguments().getParcelable(EXTRA_TRACKS_RESPONSE);
            mAdapter = new TrackAdapter(mTrackResponse, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(int position) {
        if (mListener != null) {
            mListener.onItemClick(mTrackResponse.getTracks().get(position));
        }
    }

    interface OnItemClickListener {

        void onItemClick(Track track);
    }
}