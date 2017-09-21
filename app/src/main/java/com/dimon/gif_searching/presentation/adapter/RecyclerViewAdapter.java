package com.dimon.gif_searching.presentation.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Gif gif = mGifsList.get(position);
        final String gifBigSizeImageUrl = gif.getBigSizeGifUrl();
        Glide.with(mContext)
                .asGif()
                .load(gif.getUrl())
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        holder.imageGif.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(holder.imageGif);
    }

    @Override
    public int getItemCount() {
        return mGifsList.size();
    }
}
