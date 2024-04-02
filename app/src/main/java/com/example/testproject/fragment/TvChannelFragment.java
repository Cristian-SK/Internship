package com.example.testproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.ChannelAdapter;
import com.example.testproject.ChannelItem;
import com.example.testproject.MainActivity;
import com.example.testproject.R;
import com.example.testproject.TvActivity;
import com.example.testproject.UserInteractionListener;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TvChannelFragment  extends Fragment implements UserInteractionListener {
    public static final String EXTRA_LINK = "url";
    View view;
    private RecyclerView mRecyclerView;
    private TextView txtChannelName;
    private TextView txtDescription;
    private ImageView imgChannel;
    private ImageView companyLogo;
    private EditText searchChannel;
    private TextView txtChannelCategory;
    private ChannelAdapter mChannelAdapter;
    private ArrayList<ChannelItem> mChannelList;
    private RequestQueue mRequestQueue;


    ExoPlayer player;
    public static final long DISCONNECT_TIMEOUT = 2000; // 5 min = 5 * 60 * 1000 ms

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tv, container, false);


        ((MainActivity) getActivity()).setUserInteractionListener(this);

        player = new ExoPlayer.Builder(getActivity()).build();
        StyledPlayerView playerView = view.findViewById(R.id.playerView);
        playerView.setVisibility(View.INVISIBLE);

//        searchChannel = view.findViewById(R.id.searchChannel);
        imgChannel = view.findViewById(R.id.channelImage);
        txtChannelName = view.findViewById(R.id.channelName);
        txtDescription = view.findViewById(R.id.channelDescription);
        txtChannelCategory = view.findViewById(R.id.channelCategory);
        companyLogo = view.findViewById(R.id.companyLogo);

//        Picasso.get().load("http://172.16.0.180/iptv-api/images/channel/iqtvicon.png").fit().centerInside().into(companyLogo);

        mRecyclerView = view.findViewById(R.id.recycler_View);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        mRecyclerView.requestFocus();

        mChannelList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());

        parseJson();



        return view;

    }


    private void parseJson(){
        String url = "http://172.16.0.180/iptv-api/channel/getAllChannels.php";
        StyledPlayerView playerView = view.findViewById(R.id.playerView);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject channel = jsonArray.getJSONObject(i);

                                String channelName = channel.getString("name");
                                String imageUrl = ("http://172.16.0.180/iptv-api/images/channel/")+(channel.getString("imageFilename"));
                                String description = channel.getString("description");
                                String link = channel.getString("url");

                                boolean Active = channel.getBoolean("isActive");

                                JSONArray categoriesArray = new JSONArray(channel.getString("categories"));
                                List<String> categoriesList = new ArrayList<>();
                                for (int j = 0; j < categoriesArray.length(); j++) {
                                    JSONObject categoryObject = categoriesArray.getJSONObject(j);
                                    categoriesList.add(categoryObject.getString("name"));
                                }

//                                Log.e("Categories", categoriesList.toString());

                                if (Active == true)
                                {
                                    mChannelList.add(new ChannelItem(imageUrl,channelName,description,link, categoriesList));
                                }




                            }

                            mChannelAdapter= new ChannelAdapter(getActivity(), mChannelList, new ChannelAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    if (player != null && player.isPlaying()) {
                                        player.stop();
                                        player.release();
                                        player = null;
                                    }

                                                    Intent detailIntent = new Intent(getActivity(), TvActivity.class);
                                                    ChannelItem clickedItem = mChannelList.get(position);

                                                    detailIntent.putExtra(EXTRA_LINK, clickedItem.getLink());

                                                    startActivity(detailIntent);

                                }
                            },
                                    new ChannelAdapter.OnItemViewSelectedListener() {

                                        @Override
                                        public void onItemView(int position) {
                                            ChannelItem clickedItem = mChannelList.get(position);

                                            txtChannelName.setText(clickedItem.getChannelName());
                                            txtDescription.setText(clickedItem.getDescription());
                                            txtChannelCategory.setText(clickedItem.getCategory());
                                            Picasso.get().load(clickedItem.getImageUrl()).fit().centerInside().into(imgChannel);

                                            if(player != null) {
                                                playerView.setPlayer(player);
                                                MediaItem mediaItem = MediaItem.fromUri(clickedItem.getLink());
                                                player.setMediaItem(mediaItem);
                                                player.prepare();
                                            } else {
                                                Toast.makeText(getActivity(),"player went offline",Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                    });
                            mRecyclerView.setAdapter(mChannelAdapter);
//                            mChannelAdapter.setOnItemClickListener(this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);


    }



    private static Handler disconnectHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // todo
            return true;
        }
    });

    private Runnable disconnectCallback = new Runnable() {
        @Override
        public void run() {
            // Perform any required operation on disconnect
            if(player != null){
                StyledPlayerView playerView = view.findViewById(R.id.playerView);
                playerView.setVisibility(View.VISIBLE);
//                playerView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//                playerView.bringToFront();
                player.setPlayWhenReady(true);
            } else {

                Toast.makeText(getActivity(),"Player went Null",Toast.LENGTH_SHORT).show();

            }

        }
    };

    public void resetDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT);
        StyledPlayerView playerView = view.findViewById(R.id.playerView);
        playerView.setVisibility(View.INVISIBLE);

    }

    public void stopDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
    }

    @Override
    public void onUserInteraction(){
    if(isAdded()){
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
            player = new ExoPlayer.Builder(getActivity()).build();
        }
        resetDisconnectTimer();
    }

    }

    @Override
    public void onResume() {
        super.onResume();
        resetDisconnectTimer();
        player.stop();
        player.release();
        player = new ExoPlayer.Builder(getActivity()).build();

    }



    @Override
    public void onStop() {
        super.onStop();
        stopDisconnectTimer();
        player.stop();
        player.release();
        releasePlayer();
//        Toast.makeText(getActivity(),"onSTOP", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause(){
        super.onPause();
        player.stop();
        player.release();
        releasePlayer();
//        Toast.makeText(getActivity(),"onPause", Toast.LENGTH_SHORT).show();
    }

    private void releasePlayer(){
        player.release();
    }


}
