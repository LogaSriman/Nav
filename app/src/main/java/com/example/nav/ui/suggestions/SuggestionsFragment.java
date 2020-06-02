package com.example.nav.ui.suggestions;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nav.R;
import com.example.nav.util.VideoAdapter;
import com.example.nav.util.YouTubeVideoViewer;

import java.util.Vector;

public class SuggestionsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SuggestionsFragment";
    private SuggestionsViewModel suggestionsViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    Vector<YouTubeVideoViewer> youtubeVideos = new Vector<YouTubeVideoViewer>();

    public SuggestionsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SuggestionsFragment newInstance(String param1, String param2) {
        SuggestionsFragment fragment = new SuggestionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_suggestions, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSuggestions);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));

        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gGa-gVKcvIA\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7gMQCQRgjO0\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/9Ay4u7OYOhA\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OF9-1L9AlwU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3jgl73S50tc\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1xRX1MuoImw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qw6H3Jov70c\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YZpH2DAL-1A\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YouTubeVideoViewer("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hRwkLgOc4wo\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
        //Button view1 = (Button) view.findViewById(R.id.suggestions_Button);
       // view1.setOnClickListener(this);
        return view;
    }

/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.suggestions_Button:
                //showVideo();
                break;
        }
    }
   private void showVideo() {
        Toast.makeText(getActivity(), "View clicked", Toast.LENGTH_SHORT).show();
        final VideoView videoView = getView().findViewById(R.id.videoView_suggestions);

        String videoUrl ="http://techslides.com/demos/sample-videos/small.mp4";
        //String videoUrl ="https://www.webmd.com/vitamins-and-supplements/video/vtamin-d-and-your-health";
        //String videoUrl = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(getContext());
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String videoUrl
            Uri uri = Uri.parse(videoUrl);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(uri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setDataAndType(uri, "video/mp4");
            startActivity(intent);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                //videoView.requestFocus();
               // videoView.start();
            }
        });
    }*/
}
