package com.example.testproject.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>{
    private final Context mContext;
    private final ArrayList<WeatherItem> mWeatherList;

    public WeatherAdapter(Context  context, ArrayList<WeatherItem> weatherList){
        mContext = context;
        mWeatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(v);
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTempTV, mCondTV, mCityTV, mHumdtTV, mFeelTV, mPrecipTV, mTimeTV, mSunriseTV, mSunsetTV ;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mCityTV = itemView.findViewById(R.id.city_TV);
            mTempTV = itemView.findViewById(R.id.temperature_TV);
            mCondTV = itemView.findViewById(R.id.Cond_TV);
            mHumdtTV = itemView.findViewById(R.id.Humdt_TV);
            mFeelTV = itemView.findViewById(R.id.FeelTV);
            // mTimeTV = itemView.findViewById(R.id.TimeTV);
            mPrecipTV = itemView.findViewById(R.id.PrecipTV);
            // mSunriseTV = itemView.findViewById(R.id.SunriseTV);
            // mSunsetTV = itemView.findViewById(R.id.SunsetTV);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherItem currentItem = mWeatherList.get(position);

//        String imageUrl = currentItem.getmImageUrl();
        String city = currentItem.getmCity();
        String temp = currentItem.getmTemp();
        String feelsLike = currentItem.getmFeel();
        String humdt = currentItem.getmHumdt();
        String precip = currentItem.getmPrecip();
        String timeZone = currentItem.getmTime();
        String cond = currentItem.getmCond();
//        String sunrise = currentItem.getmSunrise();
//        String sunset = currentItem.getmSunset();

      /*  if(currentItem.getmCond() == "Clear"){
            Picasso.get().load().fit().centerInside().into(R.id.image_view);
        }*/

        holder.mCityTV.setText(city);
        holder.mTempTV.setText(temp);
        holder.mHumdtTV.setText(humdt);
        holder.mFeelTV.setText(feelsLike);
        holder.mCondTV.setText(cond);
//        holder.mTimeTV.setText(timeZone);
        holder.mPrecipTV.setText(precip);
//        holder.mSunriseTV.setText(sunrise);
//        holder.mSunsetTV.setText(sunset);

//        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }



}
