package com.example.suranjan.gazum.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suranjan.gazum.R;


public class VideoListFragment extends Fragment {

    private static final String SearchQuery = "param1";

    private String searchString;
    private ViewModel mViewModel;
    private RecyclerView videoListView;

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void playVideo(String videoId);
    }

    public VideoListFragment() {
        // Required empty public constructor
    }

    public static VideoListFragment newInstance(String searchQuery) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        args.putString(SearchQuery, searchQuery);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            searchString = getArguments().getString(SearchQuery);
        }
        return inflater.inflate(R.layout.fragment_video_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //Video List recycler view
//        videoListView = getView().findViewById(R.id.view_main_video_list);
//        videoListView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        videoListView.setHasFixedSize(true);

//        final VideoAdapter adpapter = new VideoAdapter();
//        videoListView.setAdapter(adpapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
