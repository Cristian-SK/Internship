package com.example.testproject.api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.R;
import com.example.testproject.fragment.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context mContext;

    private ArrayList<NewsItem> mNewsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }


    public NewsAdapter(Context context , ArrayList<NewsItem> newsList,
                       OnItemClickListener mOnItemClickListener){
        mContext = context;
        mNewsList = newsList;
        mListener = mOnItemClickListener;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_items, parent, false);
        return new NewsViewHolder(v);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle, mDesc, mContent, mUrl, mPubl;
        public ImageView mImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.newsTitle);
            mDesc = itemView.findViewById(R.id.subHeadTV);
            mImage = itemView.findViewById(R.id.newsIV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mListener.onItemClick(position);
                }
            });

        }

    }
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        NewsItem currentNews = mNewsList.get(position);

        String title = currentNews.getmTitle();
        String desc = currentNews.getmDesc();
        String image = currentNews.getmImage();



        //Holders
        holder.mTitle.setText(title);
        holder.mDesc.setText(desc);
     /*   holder.mContent.setText(content);
        holder.mUrl.setText(url);*/
//        Picasso.get().load(image).fit().centerInside().into(holder.mImage);
        Picasso.get().load(R.drawable.iqtv).fit().centerInside().into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
