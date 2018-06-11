package com.main.eat24;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.main.eat24.Common.Common;
import com.main.eat24.Database.Database;
import com.main.eat24.Model.Order;

import java.util.ArrayList;

/**
 * Created by User on 1/1/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    private Database Db;
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images ) {
        mImageNames = imageNames;
        mImages = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        context=parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImages.get(position))
//                .into(holder.image);

        holder.foodname.setText(mImageNames.get(position));
        holder.costp.setText(mImages.get(position));

        Db = new Database(context);


        holder.addto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //Toast.makeText(context,Common.currentId, Toast.LENGTH_SHORT).show();
                if(Db.checkEmpty()){
                    Db.addToCart(new Order(
                            Common.currentId,
                            holder.foodname.getText().toString(),
                            holder.numberButton.getNumber().toString(),
                            holder.costp.getText().toString(),
                            null));
                    Common.activeCart = Common.currentId;
                }
                else {
                    if (Db.checkData()) {

                        Db.addToCart(new Order(
                                Common.activeCart,
                                holder.foodname.getText().toString(),
                                holder.numberButton.getNumber().toString(),
                                holder.costp.getText().toString(),
                                null));
                    } else {

//

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage("You cannot order from two restaurants. Clear Cart?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        new Database(context).cleanToCart();
                                        dialog.cancel();
                                    }
                                });

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(context, home.class);
                                        v.getContext().startActivity(intent);
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });




//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
//
//                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext, GalleryActivity.class);
//                intent.putExtra("image_url", mImages.get(position));
//                intent.putExtra("image_name", mImageNames.get(position));
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        //CircleImageView image;
        Button addto;
        TextView foodname;
        TextView costp;
        ConstraintLayout parentLayout;
        ElegantNumberButton numberButton;

        public ViewHolder(View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.image);
            foodname = itemView.findViewById(R.id.foodname);
            costp = itemView.findViewById(R.id.costprice);
            addto = itemView.findViewById(R.id.add);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            numberButton = itemView.findViewById(R.id.number_button);
        }

    }
}















