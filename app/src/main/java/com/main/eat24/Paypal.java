package com.main.eat24;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        // m_response = (TextView) findViewById(R.id.response);

        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(m_paypalClientId);

        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        startService(m_service);

    }

    public void pay(View view) {

        PayPalPayment payment = new PayPalPayment(new BigDecimal(10), "USD","Test Payment with Paypal", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,m_paypalRequestCode);


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

