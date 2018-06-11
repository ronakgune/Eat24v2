//package com.main.eat24.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.main.eat24.Cart;
//import com.main.eat24.GalleryActivity;
////import com.main.eat24.Menu;
//import com.main.eat24.Menu_Activity;
//import com.main.eat24.R;
//import com.main.eat24.Model.Restaurant;
//import com.main.eat24.home;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//
//public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {
//
//    private ArrayList<Restaurant> rData;
//    public Activity mACtivity;
//    public Context mContext;
//    public RestaurantAdapter(ArrayList<Restaurant> data, Activity activity) {
//        this.rData = data;
//        this.mACtivity = activity;
//    }
//
//    @Override
//    public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant, parent, false);
//        return new RestaurantHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(RestaurantHolder holder, int position) {
//        Restaurant restaurant = rData.get(position);
//
//        holder.setName(restaurant.getName());
//        holder.setAddress(restaurant.getAddress());
//        holder.setCost("Average cost " +  " " + restaurant.getCurrency()+  " " + restaurant.getCost() +  " ");
//        holder.setRating(restaurant.getRating());
//
//
//        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent (v.getContext(),Menu_Activity.class);
//                v.getContext().startActivity(intent);
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if (rData == null)
//            return 0;
//        return rData.size();
//    }
//
//    public class RestaurantHolder extends RecyclerView.ViewHolder {
//
//        ImageView restaurantImageView;
//        TextView restaurantNameTextView;
//        TextView restaurantAddressTextView;
//        TextView restaurantRatingTextView;
//        TextView costTextView;
//        TextView distanceTextView;
//        public LinearLayout linearlayout;
//
//        public RestaurantHolder(View itemView) {
//            super(itemView);
//
//            restaurantImageView = (ImageView) itemView.findViewById(R.id.imageview_restaurant);
//
//
//
//            restaurantNameTextView = (TextView) itemView.findViewById(R.id.textview_restaurant_name);
//            restaurantAddressTextView = (TextView) itemView.findViewById(R.id.restaurant_address_textview);
//            restaurantRatingTextView = (TextView) itemView.findViewById(R.id.rating);
//            costTextView = (TextView) itemView.findViewById(R.id.cost_for_two_textview);
//            distanceTextView = (TextView) itemView.findViewById(R.id.restaurant_distance_textview);
//            linearlayout = itemView.findViewById(R.id.linearlayout);
//        }
//
//
//
//        public void setName(String name) {
//            restaurantNameTextView.setText(name);
//        }
//
//        public void setAddress(String address) {
//            restaurantAddressTextView.setText(address);
//        }
//
//        public void setRating(String rating) {
//            restaurantRatingTextView.setText(rating);
//        }
//
//        public void setCost(String cost) {
//            costTextView.setText(cost);
//        }
//
//        public void setDistance(String distance) {
//            distanceTextView.setText(distance);
//        }
//    }
//
//}









// ------------------------------ CHANGE -------------------------




package com.main.eat24.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.main.eat24.Common.Common;
import com.main.eat24.Menu_Activity;
import com.main.eat24.R;
import com.main.eat24.Model.Restaurant;

import java.util.ArrayList;

/**
 * Created by brad on 2017/02/11.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {

    private ArrayList<Restaurant> mData;
    private Activity mACtivity;

    public RestaurantAdapter(ArrayList<Restaurant> data, Activity activity) {
        this.mData = data;
        this.mACtivity = activity;
    }

    @Override
    public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant, parent, false);
        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantHolder holder, int position) {
        final Restaurant restaurant = mData.get(position);

        holder.setName(restaurant.getName());
        holder.setAddress(restaurant.getAddress());
        holder.setCost("Average cost for 2 " +  " " + restaurant.getCurrency()+  " " + restaurant.getCost() +  " ");
        holder.setRating(restaurant.getRating());

        Glide.with(mACtivity)
                .load(restaurant.getImageUrl())
                .into(holder.restaurantImageView);

        holder.linearlayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Common.currentId = restaurant.getId();
                Common.currRestaurant = restaurant.getName();
                Intent intent=new Intent(v.getContext(),Menu_Activity.class);

                v.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public class RestaurantHolder extends RecyclerView.ViewHolder {

        ImageView restaurantImageView;
        TextView restaurantNameTextView;
        TextView restaurantAddressTextView;
        TextView restaurantRatingTextView;
        TextView costTextView;
        TextView distanceTextView;
        public LinearLayout linearlayout;

        public RestaurantHolder(View itemView) {
            super(itemView);

            restaurantImageView = (ImageView) itemView.findViewById(R.id.imageview_restaurant);
            restaurantNameTextView = (TextView) itemView.findViewById(R.id.textview_restaurant_name);
            restaurantAddressTextView = (TextView) itemView.findViewById(R.id.restaurant_address_textview);
            restaurantRatingTextView = (TextView) itemView.findViewById(R.id.rating);
            costTextView = (TextView) itemView.findViewById(R.id.cost_for_two_textview);
            distanceTextView = (TextView) itemView.findViewById(R.id.restaurant_distance_textview);
            linearlayout = itemView.findViewById(R.id.linearlayout);
        }

        public void setName(String name) {
            restaurantNameTextView.setText(name);
        }

        public void setAddress(String address) {
            restaurantAddressTextView.setText(address);
        }

        public void setRating(String rating) {
            restaurantRatingTextView.setText(rating);
        }

        //public void setRestaurantImageView { restaurantImageView.set}

        public void setCost(String cost) {
            costTextView.setText(cost);
        }

        public void setDistance(String distance) {
            distanceTextView.setText(distance);
        }
    }
}