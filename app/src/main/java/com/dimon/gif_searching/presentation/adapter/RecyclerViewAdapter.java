package com.dimon.gif_searching.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dimon.gif_searching.R;
import com.dimon.gif_searching.data.content.Gif;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by dimon on 19.09.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Gif> mGifsList;
    private Context mContext;
    private OnGifClickListener mListener;

    public RecyclerViewAdapter(@NotNull Context context, List<Gif> gifsList) {
        this.mGifsList = gifsList;
        this.mContext = context;
        mListener = (OnGifClickListener) context;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Gif gif = mGifsList.get(position);
        final String gifBigSizeImageUrl = gif.getBigSizeGifUrl();
        Glide.with(mContext)
                .asGif()
                .load(gif.getUrl())
                .into(holder.imageGif);
    }

    @Override
    public int getItemCount() {
        return mGifsList.size();
    }
}
