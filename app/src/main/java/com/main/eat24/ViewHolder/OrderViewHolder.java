package com.main.eat24.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.main.eat24.Interface.ItemClickListener;
import com.main.eat24.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    @BindView(R.id.order_id)
    public TextView txtOrderId;

    @BindView(R.id.order_phone)
    public TextView txtOrderPhone;

    @BindView(R.id.order_address)
    public TextView txtOrderAddress;

    @BindView(R.id.order_status)
    public TextView txtOrderStatus;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
