package com.main.eat24.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.eat24.Interface.ItemClickListener;
import com.main.eat24.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.food_name)
    public TextView food_name;

    @BindView(R.id.food_image)
    public ImageView food_image;

    private ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
