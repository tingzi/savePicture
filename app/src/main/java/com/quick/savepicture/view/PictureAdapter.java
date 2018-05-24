package com.quick.savepicture.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quick.savepicture.R;
import com.quick.savepicture.bean.Picture;

import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter {

    private List<Picture> pictures;
    private final LayoutInflater layoutInflater;

    public PictureAdapter(Context context) {
        this.pictures = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PictureViewHolder(layoutInflater.inflate(R.layout.picture_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PictureViewHolder pictureViewHolder = (PictureViewHolder) holder;
        pictureViewHolder.binding.setPath(pictures.get(position).path);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public void loadData(List<Picture> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }
}
