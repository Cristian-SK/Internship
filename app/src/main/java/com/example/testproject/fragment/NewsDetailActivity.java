package com.example.testproject.fragment;


import static com.example.testproject.fragment.Dashboard_Fragment.EXTRA_CON;
import static com.example.testproject.fragment.Dashboard_Fragment.EXTRA_DESC;
import static com.example.testproject.fragment.Dashboard_Fragment.EXTRA_PUBL;
import static com.example.testproject.fragment.Dashboard_Fragment.EXTRA_TITLE;
import static com.example.testproject.fragment.Dashboard_Fragment.EXTRA_URL;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Intent i = getIntent();
        String title = i.getStringExtra(EXTRA_TITLE);
        String description = i.getStringExtra(EXTRA_DESC);
        String content = i.getStringExtra(EXTRA_CON);
        String publ = i.getStringExtra(EXTRA_PUBL);
        String image = i.getStringExtra(EXTRA_URL);

        //Identifies the proper Views
        TextView txtViewTitle = findViewById(R.id.NewsTitleDetail);
        TextView txtViewSub = findViewById(R.id.subTitleDetail);
        TextView txtPubl = findViewById(R.id.publishedDetail);
        TextView txtCont = findViewById(R.id.ContentDetail);
        ImageView imgView = findViewById(R.id.ImageDetail);

        //Sets Information to the proper Views
        txtViewTitle.setText(title);
        txtViewSub.setText(description);
        txtPubl.setText(publ);
        txtCont.setText(content);
        Picasso.get().load(image).fit().centerInside().into(imgView);








    }
}