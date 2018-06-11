package com.main.eat24;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.main.eat24.Common.Common;
import com.main.eat24.Database.Database;
import com.main.eat24.Model.Order;
import com.main.eat24.Model.Request;
import com.main.eat24.R;
import com.main.eat24.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {


    RecyclerView recyclerView;
    TextView txtTotalView;
    FButton btnPlaceOrder;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    FirebaseDatabase database;
    DatabaseReference requests;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ButterKnife.bind(this);

        mAuth=FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        txtTotalView=findViewById(R.id.txtTotalPrice);
        recyclerView=findViewById(R.id.listCart);
        btnPlaceOrder=findViewById(R.id.btnPlaceOrder);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showAlertDialog();
                requests.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("OrderPrice").setValue(String.valueOf(total));
                        dataSnapshot.getRef().child("User").setValue(currentUser.getDisplayName());
                        new Database(getBaseContext()).cleanToCart();
                        Common.count = 0;
                        Common.activeCart = "";
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Cart.this, "Error Placing Order", Toast.LENGTH_SHORT);
                    }
                });
                finish();

                Intent inent = new Intent(com.main.eat24.Cart.this,Paypal.class);
                // calling an activity using <intent-filter> action name
                //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

                startActivity(inent);


            }
        });

        loadListFood();
    }

    private void showAlertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address: ");

        final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //create new request
                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtAddress.getText().toString(),
                        txtTotalView.getText().toString(),
                        cart
                );

                //submit to firebase
                //we will using System.currentTimeMillis to key
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);

                //delete cart
                new Database(getBaseContext()).cleanToCart();
                Toast.makeText(Cart.this, "Thank you, Order Place", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void loadListFood() {
        cart = new Database(this).getCart();
        adapter = new CartAdapter(cart, Cart.this);
        recyclerView.setAdapter(adapter);

        //calculate total price
        for (Order order : cart) {
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
            Locale locale = new Locale("en", "US");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

            txtTotalView.setText(fmt.format(total));
        }



    }
}
