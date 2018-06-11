package com.main.eat24;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import com.main.eat24.Model.OrderHistory;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderStatus extends AppCompatActivity {

    @BindView(R.id.listOrders)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;


    FirebaseDatabase database;
    DatabaseReference requests;
    ArrayList<OrderHistory> orderHistories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Order");

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
        orderHistories=  new ArrayList<>();

    }

    private void loadOrders() {

        requests.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String key=ds.getKey();
                    OrderHistory orderHistory=new OrderHistory();
                    orderHistory.setOrderId(key);
                    orderHistory.setDelAddress(ds.child("Address").getValue(String.class));
                    orderHistory.setRestName(ds.child("Restaurant").getValue(String.class));
                    orderHistory.setOrderPrice(ds.child("OrderPrice").getValue(String.class));

                    orderHistories.add(orderHistory);
                }

                OrderStatusAdapter orderStatusAdapter=new OrderStatusAdapter(orderHistories,getApplicationContext());
                recyclerView.setAdapter(orderStatusAdapter);
                orderStatusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private String convertToStatus(String status) {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}