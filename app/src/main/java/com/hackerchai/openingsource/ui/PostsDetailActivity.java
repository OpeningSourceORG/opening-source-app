package com.hackerchai.openingsource.ui;



import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;

import com.hackerchai.openingsource.R;




public class PostsDetailActivity extends AppCompatActivity {
    private String link;
    private String title;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_detail);

        Intent intent=getIntent();

        link=intent.getStringExtra("Link");
        title=intent.getStringExtra("Title");


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(link));
        finish();





    }
}