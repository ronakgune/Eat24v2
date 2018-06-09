package com.main.eat24;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.main.eat24.Common.Common;
import com.main.eat24.adapters.RestaurantAdapter;
import com.main.eat24.Model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * So this tutorial will go in order of
 * 1: setting up gradle dependencies
 * 2: Designing row item for recyclerview
 * 3: Creating model need RecyclerView Adapter
 * 4: Creating RecyclerViewAdapter
 * 5: Creating RecyclerView in layout file in MainActivity
 * 6: Pulling Json data and setting up recyclerview
 */
public class home extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, LocationListener {
    int innerCount = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.search:
                    // Call search
                    Toast.makeText(home.this, "Search", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.orders:
                    Toast.makeText(home.this, "Orders", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.location_home:
                    Intent location = new Intent(home.this, location.class);
                    startActivity(location);
                    return true;
                case R.id.cart:
                    Intent cart = new Intent(home.this, Cart.class);
                    startActivity(cart);
                    return true;
            }
            return false;
        }
    };


    LocationManager locationManager;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRestaurantRecyclerView;
    private RestaurantAdapter mAdapter;
    private ArrayList<Restaurant> mRestaurantCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if(innerCount == 0){
//            Common.count = 0;
//            innerCount++;
//        }else{
//            Common.count = 1;
//        }
        getLocation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        init();
        new FetchDataTask().execute();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //Toast.makeText(this, "Current location is" + Common.longitude + Common.latitude + Common.count, Toast.LENGTH_SHORT).show();
    }


    void getLocation() {
        try {
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }

    }
    private void init() {
        if(Common.count == 0){
        Common.longitude = "-121.938987";
        Common.latitude = "37.349642";
        Common.count++;
    }

        mRestaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurant_recycler);
        mRestaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRestaurantRecyclerView.setHasFixedSize(true);
        mRestaurantCollection = new ArrayList<>();

        mAdapter = new RestaurantAdapter(mRestaurantCollection, this);
        mRestaurantRecyclerView.setAdapter(mAdapter);

    }


    // Called after Swipe to refresh
    @Override
    public void onRefresh() {
        Common.count = 10;
        init();
        new FetchDataTask().execute();
    }

    @Override
    public void onLocationChanged(Location location) {
        Common.latitude =  String.valueOf(location.getLatitude());
        Common.longitude = String.valueOf(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(home.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

    }





    // Fetch Data Using Recycler

    public class FetchDataTask extends AsyncTask<Void, Void, Void>  {

        String mZomatoString;
        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String requestURL = String.format("https://api.eatstreet.com/publicapi/v1/restaurant/search?latitude=%s&longitude=%s&method=delivery&access-token=a260495775120148", Common.latitude,Common.longitude);
            //Uri builtUri = Uri.parse(getString(R.string.zomato_api));
            URL url;
            try {
                url = new URL(requestURL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //urlConnection.setRequestProperty("user-key", "a9f2b3a292aae939ea358f43062c1f8b");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Nothing to do
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                mZomatoString = buffer.toString();
                JSONObject jsonObject = new JSONObject(mZomatoString);
                //Toast.makeText(home.this, mZomatoString, Toast.LENGTH_LONG).show();
                //Log.v("Response", jsonObject.toString());

                JSONArray restaurantsArray = jsonObject.getJSONArray("restaurants");

                //list = new ArrayList<>();
                for (int i = 0; i < restaurantsArray.length(); i++) {


                    JSONObject jRestaurant = restaurantsArray.getJSONObject(i);
                    String name = jRestaurant.getString("name");
                    String address = jRestaurant.getString("streetAddress");
                    String waitTime = jRestaurant.getString("minWaitTime");
                    String cost = jRestaurant.getString("deliveryPrice");
                    String id = jRestaurant.getString("apiKey");

                    Restaurant restaurant = new Restaurant();
                    restaurant.setName(name);
                    restaurant.setAddress(address);
                    restaurant.setId(id);
                    //restaurant.setLatitiude(lat);
                    //restaurant.setLongitude(lon);
//                    restaurant.setCurrency(currency);
                    restaurant.setCost(String.valueOf(cost));
//                    restaurant.setImageUrl(imageUrl);
                    restaurant.setRating(String.valueOf(waitTime));

                    mRestaurantCollection.add(restaurant);

//                    //Log.v("BRAD_", i + "");
//                    String id;
//                    String name;
//                    String address;
//                    String currency;
//                    String imageUrl;
//                    long lon;
//                    long lat;
//                    long cost;
//                    float rating;
//
//
//
//                    JSONObject jRestaurant = restaurantsArray.getJSONObject(i);
//
//                    //jRestaurant = jRestaurant.getJSONObject("restaurant");
//
//                    //JSONObject jLocattion = jRestaurant.getJSONObject("location");
//                    //JSONObject jRating = jRestaurant.getJSONObject("user_rating");
//
//                    //id = jRestaurant.getString("id");
//                    name = jRestaurant.getString("name");
//                    Log.w("name", name);
//                    //address = jLocattion.getString("address");
//                    //lat = jLocattion.getLong("latitude");
//                    //lon = jLocattion.getLong("longitude");
//                    currency = jRestaurant.getString("currency");
//                    cost = jRestaurant.getInt("average_cost_for_two");
//                    imageUrl = jRestaurant.getString("featured_image");
//                    //rating = (float) jRating.getDouble("aggregate_rating");
//                    Restaurant restaurant = new Restaurant();
//                    restaurant.setName(name);
//                    //restaurant.setAddress(address);
//                    //restaurant.setLatitiude(lat);
//                    //restaurant.setLongitude(lon);
//                    restaurant.setCurrency(currency);
//                    restaurant.setCost(String.valueOf(cost));
//                    restaurant.setImageUrl(imageUrl);
//                    //restaurant.setRating(String.valueOf(rating));
//
//                    mRestaurantCollection.add(restaurant);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(mSwipeRefreshLayout.isRefreshing()){
                mSwipeRefreshLayout.setRefreshing(false);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}