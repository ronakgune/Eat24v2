package com.main.eat24;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class Paypal extends AppCompatActivity {


    TextView m_response;

    PayPalConfiguration m_configuration;
    String m_paypalClientId = "AVBSCZ3u7-ptL_uUhtewblNvR3k2q6-DwDI5G_gf7OVoXku-WTS8nmh16PHF-H06QZRTKyW-vaUVzxdy";
    Intent m_service;
    int m_paypalRequestCode = 999;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private FirebaseDatabase database;
    private DatabaseReference requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        // m_response = (TextView) findViewById(R.id.response);

        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(m_paypalClientId);

        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");
        m_service = new Intent(this, PayPalService.class);

        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        startService(m_service);

    }

    public void pay(View view) {

        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        requests.child(currentUser.getUid()).child("OrderPrice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PayPalPayment payment = new PayPalPayment(BigDecimal.valueOf(Double.parseDouble(dataSnapshot.getValue().toString())),"USD","Test Payment with Paypal", PayPalPayment.PAYMENT_INTENT_SALE);

                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
                startActivityForResult(intent,m_paypalRequestCode);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == m_paypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK){

                //Confirming that payment has worked
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation != null) {
                    String state = confirmation.getProofOfPayment().getState();

                    if (state.equals("approved"))
                        // m_response.setText("Payment approved");
                        Toast.makeText(this,"Payment Approved",Toast.LENGTH_SHORT).show();
                    else
                        // m_response.setText("error in the payment");
                        Toast.makeText(this,"Error in Payment",Toast.LENGTH_SHORT).show();

                }

                else
                    m_response.setText("confirmation is null");

            }
        }
    }

}

