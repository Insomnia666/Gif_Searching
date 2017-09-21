package com.dimon.gif_searching.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dimon.gif_searching.R;
import com.dimon.gif_searching.data.content.Gif;
import com.dimon.gif_searching.data.giphy.Data;
import com.dimon.gif_searching.domain.TagGifListUseCase;
import com.dimon.gif_searching.presentation.GifDialog;
import com.dimon.gif_searching.presentation.adapter.OnGifClickListener;
import com.dimon.gif_searching.presentation.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by dimon on 20.09.2017.
 */

public class FoundContentActivity extends AppCompatActivity implements OnGifClickListener {

    private RecyclerView recyclerViewFoundContent;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Gif> gifsList;
    private String query;
    private TagGifListUseCase tagGifListUseCase = new TagGifListUseCase();
    private Data queryResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_content);
        initItems();
        queryGifs(query);
        setToolbarTitle(query);
        buildRecyclerView();
        addButtonHome();
    }

    private void initItems() {
        gifsList = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getIntent() != null) {
            query = getIntent().getStringExtra("query");
        } else {
            query = "Error";
        }
        recyclerViewFoundContent = (RecyclerView) findViewById(R.id.recyclerViewFoundContent);
        recyclerViewAdapter = new RecyclerViewAdapter(this, gifsList);
    }

    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void addButtonHome() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void buildRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewFoundContent.setLayoutManager(layoutManager);
        recyclerViewFoundContent.setAdapter(recyclerViewAdapter);
    }

    private void queryGifs(String title) {
        tagGifListUseCase.execute(title, new DisposableObserver<List<Gif>>() {
            @Override
            public void onNext(List<Gif> gifs) {
                gifsList.addAll(gifs);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("AAAAAAAAAAAAAA", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        tagGifListUseCase.dispose();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chosing_rating, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showBigSizeGif(String originalUrl) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        GifDialog newFragment = GifDialog.newInstance(originalUrl);
        newFragment.show(ft, "dialog");
    }
}
