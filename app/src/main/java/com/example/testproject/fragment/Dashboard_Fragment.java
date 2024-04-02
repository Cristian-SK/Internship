package com.example.testproject.fragment;

import static android.content.Context.LOCALE_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.R;
import com.example.testproject.api.NewsAdapter;
import com.example.testproject.api.NewsItem;
import com.example.testproject.databinding.NewsItemsBinding;
import com.example.testproject.viewmodel.WeatherAdapter;
import com.example.testproject.viewmodel.WeatherItem;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Dashboard_Fragment extends Fragment{

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC= "description";
    public static final String EXTRA_CON= "content";
    public static final String EXTRA_URL = "image";
    public static final String EXTRA_PUBL = "publishedAt";
    View view;


    //  Weather App
    private RecyclerView mRecyclerView;
    private WeatherAdapter mWeatherAdapter;
    private ArrayList<WeatherItem> mWeatherList;
    private RequestQueue mRequestQueue;
    //  Weather App

    //News

    private RecyclerView mRecyclerNews;
    private NewsAdapter mNewsAdapter;
    private ArrayList<NewsItem> mNewsList;
    private RequestQueue mRequestNews;

    //News

    private String lurl = "http://172.16.0.180/iptv-api/channel/getAllChannels.php";





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //Start
        Button langbutton = view.findViewById(R.id.langbutton);
        Button btnes = view.findViewById(R.id.btnes);
        Button btnin = view.findViewById(R.id.btnin);
        RelativeLayout rlwelcome = view.findViewById(R.id.rlwelcome);
        TextView Bienvenido = view.findViewById(R.id.Bienvenido);
        TextView BienvenidoName = view.findViewById(R.id.BienvenidoName);
        TextView Bienvenidotxt = view.findViewById(R.id.Bienvenidotxt);
        ImageView Bienvenidologo = view.findViewById(R.id.Bienvenidologo);

        //Check the lang state
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
        boolean isLanguageSelected = sharedPreferences.getBoolean("isLanguageSelected", false);

        //If a language has been selected
        if (isLanguageSelected) {
            rlwelcome.setVisibility(View.GONE);
        }

        langbutton.requestFocus();

        langbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make the TextViews, Button, and ImageView invisible
                Bienvenido.setVisibility(View.GONE);
                BienvenidoName.setVisibility(View.GONE);
                Bienvenidotxt.setVisibility(View.GONE);
                langbutton.setVisibility(View.GONE);
                Bienvenidologo.setVisibility(View.GONE);

                // Make the buttons visible
                btnes.setVisibility(View.VISIBLE);
                btnin.setVisibility(View.VISIBLE);
            }
        });

        // Set the same click listener for all three buttons
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Save the language selection
                saveLanguageSelectionState();

                // Make the original RelativeLayout invisible when a button is clicked
                rlwelcome.setVisibility(View.GONE);
            }
        };

        btnes.setOnClickListener(buttonClickListener);
        btnin.setOnClickListener(buttonClickListener);

        btnes.requestFocus();
        //end


        //logo
        ImageView logoView = (ImageView) view.findViewById(R.id.Bienvenidologo);
        Picasso.get().load(R.drawable.hardrockhotel).fit().centerInside().into(logoView);



        //Background
        ImageView backgroundHome = (ImageView) view.findViewById(R.id.backgroundhome);
        Picasso.get().load(R.drawable.puertoricosanjuan).fit().into(backgroundHome);




        //Weather
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWeatherList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());

        parseJSON();
        //Weather


        Reloj();

        //News
        mRecyclerNews = view.findViewById(R.id.News);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsList = new ArrayList<>();
        mRequestNews = Volley.newRequestQueue(getActivity());
        mRequestNews = Volley.newRequestQueue(getActivity());
        parseNews();
        //News

        mRecyclerNews.requestFocus();

        //Location



        return view;
    }



private void saveLanguageSelectionState() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean("isLanguageSelected", true);
        myEdit.apply();
    }



    private void parseNews() {

        String url = "https://gnews.io/api/v4/search?q=example&lang=en&country=us&max=10&apikey=906c2ae2f0e6e84ae78d11b1bbaddafd";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, lurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
//                            JSONArray jsonArray = response.getJSONArray("articles");
                            JSONArray jsonArray = response.getJSONArray("data");

                            for(int i = 0; i<jsonArray.length();i++){
                                JSONObject articles = jsonArray.getJSONObject(i);

//                                String title = articles.getString("title");
//                                String desc = articles.getString("description");
//                                String image = articles.getString("image");
//                                String content = articles.getString("content");
//                                String publ = articles.getString("publishedAt");

                                mNewsList.add(new NewsItem("title", "desc", "image", "content", "publ"));

                            }
                            mNewsAdapter = new NewsAdapter(getActivity(), mNewsList, new NewsAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    Intent i = new Intent(getActivity(), NewsDetailActivity.class);
                                    NewsItem clickedNews = mNewsList.get(position);
                                    i.putExtra(EXTRA_TITLE, clickedNews.getmTitle());
                                    i.putExtra(EXTRA_DESC, clickedNews.getmDesc());
                                    i.putExtra(EXTRA_CON, clickedNews.getmContent());
                                    i.putExtra(EXTRA_PUBL, clickedNews.getmPubl());
                                    i.putExtra(EXTRA_URL, clickedNews.getmImage());

                                    if(i.putExtra(EXTRA_URL, clickedNews.getmImage()) == null){

                                    }

                                    startActivity(i);
                                }
                            });

                            mRecyclerNews.setAdapter(mNewsAdapter);



                        }catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestNews.add(request);

    }


    private void parseJSON() {

        String url = "https://api.weatherbit.io/v2.0/current?lat=18.0777&lon=-66" +
                ".96&key=a15132d7eff4416dad163a2ed237d444&include=minutely&units=I";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, lurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray arrayLoc = response.getJSONArray("data");

                    for (int i = 0; i < arrayLoc.length(); i++) {
                        JSONObject jsonData = arrayLoc.getJSONObject(i);
//
//                        String City = jsonData.getString("city_name");
//                        String Time = jsonData.getString("timezone");
//                        String Precip = jsonData.getString("precip") + ("%");
//                        String Cond = jsonData.getJSONObject("weather").getString("description");
//                        String Temp = jsonData.getString("temp") + ("°F");
//                        String Humdt = jsonData.getString("rh") + ("%");
//                        String Feel = jsonData.getString("app_temp")+ ("°F");
//                        String Sunset = jsonData.getString("sunset") + ("pm");
//                        String Sunrise = jsonData.getString("sunrise") + ("am");

                        mWeatherList.add(new WeatherItem("San German" ," ",  "Rain",  "18mm",  "88 F°",
                                "69%",  "97°",  " ",  " "));

                    }


                    mWeatherAdapter = new WeatherAdapter(getActivity(), mWeatherList);

                    mRecyclerView.setAdapter(mWeatherAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
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



    private void Reloj(){
        TextClock textClock, textampm, textdate;
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        textClock = view.findViewById(R.id.textclockid);
        textampm = view.findViewById(R.id.textampmid);
        textdate = view.findViewById(R.id.textdateid);
        Typeface myfont = ResourcesCompat.getFont(getActivity(), R.font.nanum_gothic);
        textClock.setTypeface(myfont);
        textampm.setTypeface(myfont);
        textdate.setTypeface(myfont);

    }



}