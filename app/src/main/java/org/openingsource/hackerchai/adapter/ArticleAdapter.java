package org.openingsource.hackerchai.adapter;



import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.openingsource.hackerchai.R;
import org.openingsource.hackerchai.ui.PostsDetailActivity;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Marco Gomiero on 12/02/2015.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<Article> articles;

    private int rowLayout;
    private Context mContext;

    public ArticleAdapter(ArrayList<Article> list, int rowLayout, Context context) {
        this.articles = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public void clearData() {
        if (articles != null)
            articles.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        final Article currentArticle = articles.get(position);

        /*
        Locale.setDefault(Locale.getDefault());
        Date date = currentArticle.getPubDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        final String pubDateString = sdf.format(date);
        */

        viewHolder.title.setText(currentArticle.getTitle());

        Picasso.get()
                .load(currentArticle.getImage())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.image);

        //viewHolder.pubDate.setText(pubDateString);

        String description = "";
        if (currentArticle.getDescription() != null) {
           description = currentArticle.getDescription();
           description = description.substring(3,description.length());
        }

        viewHolder.desc.setText(description);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PostsDetailActivity.class);
                intent.putExtra("Link",currentArticle.getLink());
                intent.putExtra("Title",currentArticle.getTitle());
                mContext.startActivity(intent);

            }
        });

        viewHolder.more.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view) {
                Intent intent=new Intent(mContext,PostsDetailActivity.class);
                intent.putExtra("Link",currentArticle.getLink());
                intent.putExtra("Title",currentArticle.getTitle());
                mContext.startActivity(intent);
            }
                                           }

        );

        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, currentArticle.getTitle()+" "+currentArticle.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(Intent.createChooser(intent, currentArticle.getTitle()));
            }
                                            }

        );
    }

    @Override
    public int getItemCount() {

        return articles == null ? 0 : articles.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        //TextView pubDate;
        ImageView image;
        TextView desc;
        Button share;
        Button more;

        public ViewHolder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            //pubDate = itemView.findViewById(R.id.post_date);
            image = itemView.findViewById(R.id.post_photo);
            desc = itemView.findViewById(R.id.post_desc);
            share = itemView.findViewById(R.id.btn_share);
            more = itemView.findViewById(R.id.btn_more);
        }

    }
}