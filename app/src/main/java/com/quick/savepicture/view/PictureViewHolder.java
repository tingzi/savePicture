package com.quick.savepicture.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.quick.savepicture.databinding.PictureItemBinding;

public class PictureViewHolder extends RecyclerView.ViewHolder {

    public PictureItemBinding binding;

    public PictureViewHolder(View itemView) {
        super(itemView);
        binding = PictureItemBinding.bind(itemView);
    }
}
