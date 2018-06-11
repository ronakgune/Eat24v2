package com.main.eat24;

/**
 * Created by Ronak on 6/9/18.
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.main.eat24.Model.Order;

import java.util.ArrayList;

public class Menu_Activity extends AppCompatActivity {



    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    FloatingActionButton cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        Log.d(TAG, "onCreate: started.");

        cart = findViewById(R.id.cartShortcut);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(Menu_Activity.this, Cart.class);
                startActivity(cart);
            }
        });
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("10");
        mNames.add("Boneless Wings");

        mImageUrls.add("14");
        mNames.add("Pasta");

        mImageUrls.add("13");
        mNames.add("Macaroni and Cheese");

        mImageUrls.add("17");
        mNames.add("Pasta Primavera");


        mImageUrls.add("14");
        mNames.add("Bourbon Bacon Pulled Pork");

        mImageUrls.add("19");
        mNames.add("Caribbean Chicken with Pineapple-Cilantro Rice");


        mImageUrls.add("12");
        mNames.add("Spicy Avocado Snack");

        mImageUrls.add("17");
        mNames.add("Vegan Sheet Pan Dinners");

        mImageUrls.add("1");
        mNames.add("Spicy Tahini Sauce with Kale, Sea Vegetables, and Soba Noodles");

        mImageUrls.add("15");
        mNames.add("Pan Seared Salmon");

        mImageUrls.add("15");
        mNames.add("Mushroom Chicken Piccata");

        mImageUrls.add("17");
        mNames.add("Pork Chops with Dill Pickle Marinade");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

