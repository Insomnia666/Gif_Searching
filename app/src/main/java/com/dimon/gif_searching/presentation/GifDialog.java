package com.dimon.gif_searching.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dimon.gif_searching.R;

import org.jetbrains.annotations.NotNull;

/**
 * Created by dimon on 19.09.2017.
 */

public class GifDialog extends DialogFragment {
    public String url;

    public static GifDialog newInstance(String url) {
        GifDialog gifDialog = new GifDialog();
        Bundle args = new Bundle();
        args.putString("url", url);
        gifDialog.setArguments(args);
        return gifDialog;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        url = getArguments().getString("url");
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewGif);
        Glide.with(getActivity())
                .asGif()
                .load(url)
                .into(imageView);
        return builder.create();
    }
}
