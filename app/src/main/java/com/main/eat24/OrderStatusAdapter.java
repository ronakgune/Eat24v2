package com.main.eat24;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.main.eat24.Model.Order;
import com.main.eat24.Model.OrderHistory;

import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Created by Ronak on 6/11/18.
 */

class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.OrderStatusViewHolder>{

    private ArrayList<OrderHistory> orderHistories;
    private FirebaseUser currentUser;
    private DatabaseReference mUserDb;
    private Context context;

    public OrderStatusAdapter(ArrayList<OrderHistory> orderHistories,Context applicationContext) {
        this.context=applicationContext;
        this.orderHistories=orderHistories;

    }

    @NonNull
    @Override
    public OrderStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.order_layout,parent,false);
        OrderStatusViewHolder orderStatusViewHolder= new OrderStatusViewHolder(view);
        return orderStatusViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderStatusViewHolder holder, int position) {

        OrderHistory orderHistory=orderHistories.get(position);
        holder.setOrderAddress(orderHistory.getDelAddress());
        holder.setOrderId(orderHistory.getOrderId());
        holder.setOrderPrice(orderHistory.getOrderPrice());
        holder.setRestuarant(orderHistory.getRestName());

    }

    @Override
    public int getItemCount() {
        int arr=0;
        try{
            if(orderHistories.size()==0){
                return 0;
            }else{
                arr=orderHistories.size();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    public class OrderStatusViewHolder extends RecyclerView.ViewHolder{
        View view;

        public OrderStatusViewHolder(View itemView){
            super(itemView);
            this.view=itemView;
        }


        public void setRestuarant(String restName){
            TextView textView= view.findViewById(R.id.order_restname);
            textView.setText(restName);
        }

        public void setOrderPrice(String orderPrice){
            TextView textView=view.findViewById(R.id.order_price);
            textView.setText(orderPrice);
        }
        public void setOrderId(String orderId){
            TextView textView=view.findViewById(R.id.order_id);
            textView.setText(orderId);
        }
        public void setOrderAddress(String address){
            TextView textView=view.findViewById(R.id.order_address);
            textView.setText(address);
        }
    }
}
