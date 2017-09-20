package com.dimon.gif_searching.presentation.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dimon.gif_searching.R;
import com.dimon.gif_searching.data.api.giphy.GiphyApiFactory;
import com.dimon.gif_searching.data.content.Gif;
import com.dimon.gif_searching.data.giphy.Data;
import com.dimon.gif_searching.presentation.GifDialog;
import com.dimon.gif_searching.presentation.adapter.OnGifClickListener;
import com.dimon.gif_searching.presentation.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, OnGifClickListener {

    private RecyclerView recyclerViewTrending;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Gif> gifsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
        queryGifs();
        buildRecyclerView();
    }

    private void initItems() {
        gifsList = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbarTitle(getString(R.string.toolbar_title));
        recyclerViewTrending = (RecyclerView) findViewById(R.id.recyclerViewTrending);
        recyclerViewAdapter = new RecyclerViewAdapter(this, gifsList);
    }

    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void buildRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewTrending.setLayoutManager(layoutManager);
        recyclerViewTrending.setAdapter(recyclerViewAdapter);
    }

    private void queryGifs() {
        GiphyApiFactory.getInstance().getTrending().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Integer countOfGifs = Integer.parseInt(response.body().getPagination().getCount().toString());
                for (int i = 0; i < countOfGifs; i++) {
                    String url = response.body().getData().get(i).getImages().getFixedHeightSmall().getUrl();
                    String originalUrl = response.body().getData().get(i).getImages().getFixedHeight().getUrl();
                    gifsList.add(new Gif(url, originalUrl));
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("myLogs", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_search, menu);
        MenuItem action_search = menu.findItem(R.id.buttonSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(action_search);
        searchView.setQueryHint(getString(R.string.search_view_hint));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(this, FoundContentActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showBigSizeGif(String originalUrl) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        GifDialog gifDialog = GifDialog.newInstance(originalUrl);
        gifDialog.show(ft, "dialog");
    }
}
