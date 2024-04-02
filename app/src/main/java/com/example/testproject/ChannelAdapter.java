package com.example.testproject;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {
    private Context mContext;
    private ArrayList<ChannelItem> mChannelList;
    private OnItemClickListener mListener;

    private OnItemViewSelectedListener mListener2;



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemViewSelectedListener {
        void onItemView(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;
    }

    public ChannelAdapter(Context context, ArrayList<ChannelItem> channelList, OnItemClickListener mOnClickListener, OnItemViewSelectedListener mOnViewSelector) {
        mContext = context;
        mChannelList = channelList;
        mListener = mOnClickListener;
        mListener2 = mOnViewSelector;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.channel_item, parent, false);
        return new ChannelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        ChannelItem currentItem = mChannelList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String channelName = currentItem.getChannelName();
        String description = currentItem.getDescription();
        String link = currentItem.getLink();

//        holder.mTextViewChannelName.setText(channelName);
//        holder.mTextViewDescription.setText(description);
//        holder.mTextViewLink.setText(link);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mChannelList.size();
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewChannelName;
        public TextView mTextViewDescription;
        public TextView mTextViewLink;
        public CardView mChannel;


        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
//            mTextViewChannelName = itemView.findViewById(R.id.text_view_channel);
//            mTextViewDescription = itemView.findViewById(R.id.text_view_description);
//            mTextViewLink = itemView.findViewById(R.id.text_view_link);
            mChannel = itemView.findViewById(R.id.channelView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    mListener.onItemClick(position);
//                            mListener2.onItemViewSelector(position);

                }
            });

            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (hasFocus) {
                        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.scale_in_tv);
                        v.startAnimation(anim);
                        anim.setFillAfter(true);
                        int position = getAdapterPosition();
                        mListener2.onItemView(position);
//                        Toast.makeText(mContext,"IM BIG",Toast.LENGTH_SHORT).show();
                    } else {
                        // run scale animation and make it smaller
                        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.scale_out_tv);
                        v.startAnimation(anim);
                        anim.setFillAfter(true);
                    }
//
//                    mListener.onItemClick(position);

                }
            });




        }
    }


    public void filteredList(ArrayList<ChannelItem> filteredList) {
        mChannelList = filteredList;
        notifyDataSetChanged();
    }


}
