//package com.main.eat24.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.main.eat24.Model.menuModel;
//import com.main.eat24.R;
//import com.main.eat24.Model.menuModel;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//
//public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.menuHolder> {
//
//    private ArrayList<menuModel> mData;
//    private Activity mACtivity;
//    private Context context;
//
//    public MenuAdapter(ArrayList<menuModel> data, Activity activity) {
//        this.mData = data;
//        this.mACtivity = activity;
//    }
//
//    @Override
//    public menuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
//        return new menuHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(menuHolder holder, int position) {
//        menuModel menu = mData.get(position);
//
//
//        holder.setCost(menu.getCost());
//        holder.setFoodName(menu.getFoodName());
//
////        holder.setName(menu.getName());
////        holder.setAddress(menu.getAddress());
////        holder.setCost("Average cost for 2 " +  " " + restaurant.getCurrency()+  " " + restaurant.getCost() +  " ");
////        holder.setRating(menu.getRating());
//
////        Glide.with(mACtivity)
////                .load(restaurant.getRating())
////                .into(holder.restaurantImageView);
//
//        //Picasso.get().load(restaurant.getImageUrl()).into(holder.restaurantImageView);
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mData == null)
//            return 0;
//        return mData.size();
//    }
//
//    public class menuHolder extends RecyclerView.ViewHolder {
//
//        TextView foodname;
//        TextView costp;
//
//        public menuHolder(View itemView) {
//            super(itemView);
//
//            //restaurantImageView = (ImageView) itemView.findViewById(R.id.imageview_restaurant);
//
//
//            foodname = (TextView) itemView.findViewById(R.id.foodname);
//            costp = (TextView) itemView.findViewById(R.id.cost);
//
//        }
//
//
//        public void setFoodName(String name) {
//            foodname.setText(name);
//        }
//
////        public void setAddress(String address) {
////            cost.setText(address);
////        }
////
////        public void setRating(String rating) {
////            restaurantRatingTextView.setText(rating);
////        }
////
//        public void setCost(String cost) {
//            costp.setText(cost);
//        }
////
////        public void setDistance(String distance) {
////            distanceTextView.setText(distance);
////        }
////    }
//
//    }
//}
