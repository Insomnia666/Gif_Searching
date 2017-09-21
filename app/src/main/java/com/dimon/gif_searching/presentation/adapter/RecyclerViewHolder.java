package com.dimon.gif_searching.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dimon.gif_searching.R;

/**
 * Created by dimon on 19.09.2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView imageGif;
    FrameLayout containerImageGif;
    ProgressBar progressBar;

    public RecyclerViewHolder (View itemView) {
        super(itemView);
        imageGif = (ImageView) itemView.findViewById(R.id.imageGif);
        containerImageGif = (FrameLayout) itemView.findViewById(R.id.containerImageGif);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progress);

    }
}
