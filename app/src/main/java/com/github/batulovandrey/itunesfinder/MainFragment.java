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
 * @author batul0ve 01/08/2017
 */
public class MainFragment extends Fragment implements TrackClickListener {
    public static final String TAG = MainFragment.class.getSimpleName();
    private static final String EXTRA_TRACKS_RESPONSE = "extra_tracks_response";

    private OnItemClickListener mListener;
    private TrackResponse mTrackResponse;
    private TrackAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static MainFragment newInstance(TrackResponse response) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TRACKS_RESPONSE, response);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTrackResponse = (TrackResponse) getArguments().getSerializable(EXTRA_TRACKS_RESPONSE);
        }
        mAdapter = new TrackAdapter(mTrackResponse, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    public interface OnItemClickListener {
        void onItemClick(Track track);
    }
}
