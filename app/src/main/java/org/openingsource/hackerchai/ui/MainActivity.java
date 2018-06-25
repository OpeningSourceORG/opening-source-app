package org.openingsource.hackerchai.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.openingsource.hackerchai.R;
import org.openingsource.hackerchai.adapter.ArticleAdapter;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private ArticleAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar progressBar;
    private String urlString;
    private BottomNavigationView bottomNavigationView;
    private static final String qq_key = "0rRzWHPh7xCjk2rWdYUSm6G41rz8DQ6M";

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressBar);

        if(getResources().getConfiguration().locale.getCountry().equals("TW")|getResources().getConfiguration().locale.getCountry().equals("HK"))
        {
            urlString = "https://openingsource.org/daily/feed/zh-tw";
        }
        else
        {
            urlString = "https://openingsource.org/daily/feed/";
        }


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_daily:
                        if(getResources().getConfiguration().locale.getCountry().equals("TW")|getResources().getConfiguration().locale.getCountry().equals("HK")) {
                            urlString = "https://openingsource.org/daily/feed/zh-tw";
                        }
                        else
                        {
                            urlString = "https://openingsource.org/daily/feed/";
                        }

                        loadFeed();
                        return true;
                    case R.id.tab_all:
                        if(getResources().getConfiguration().locale.getCountry().equals("TW")|getResources().getConfiguration().locale.getCountry().equals("HK")) {
                            urlString = "https://openingsource.org/feed/zh-tw";
                        }
                        else
                        {
                            urlString = "https://openingsource.org/feed/";
                        }

                        loadFeed();
                        return true;
                }
                return false;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);


        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout = findViewById(R.id.container);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.canChildScrollUp();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                adapter.clearData();
                adapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(true);
                loadFeed();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (dy < 0) {
                    bottomNavigationView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        if (!isNetworkAvailable()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_message)
                    .setTitle(R.string.alert_title)
                    .setCancelable(false)
                    .setPositiveButton(R.string.alert_positive,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    finish();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();

        } else if (isNetworkAvailable()) {
            loadFeed();
        }
    }


    public void loadFeed() {

        if (!mSwipeRefreshLayout.isRefreshing())
            progressBar.setVisibility(View.VISIBLE);

        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {
            //what to do when the parsing is done
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                adapter = new ArticleAdapter(list, R.layout.card_view, MainActivity.this);
                mRecyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);

            }

            //what to do in case of error
            @Override
            public void onError() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this, R.string.alert_error,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onResume() {

        super.onResume();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (adapter != null)
            adapter.clearData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_page)
        {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_about)
        {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_developers)
        {
            Intent i = new Intent(MainActivity.this, DeveloperActivity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_telegram)
        {
            String url = "http://t.me/OpeningSourceOrg";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else if (id == R.id.nav_qq)
        {
            joinQQGroup(qq_key);

        }
        else if (id == R.id.nav_socila_weibo)
        {

            String url = "https://weibo.com/openingsource";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else if (id == R.id.nav_socila_twitter)
        {
            String url = "https://twitter.com/openingsource";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else if (id == R.id.nav_socila_facebook)
        {

            String url = "https://facebook.com/openingsource";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        else if (id == R.id.nav_socila_google)
        {
            String url = "https://google.com/+OpeningSource";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
    }

}


