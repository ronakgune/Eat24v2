//package com.main.eat24;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.main.eat24.Common.Common;
//import com.main.eat24.Model.Restaurant;
//import com.main.eat24.Model.menuModel;
//import com.main.eat24.adapters.MenuAdapter;
//
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
///**
// * Created by ronakgune on 6/9/18.
// */
//
//public class Menu extends AppCompatActivity {
//    private RecyclerView mMenuRecyclerView;
//    private MenuAdapter mAdapter;
//    private ArrayList<menuModel> mMenuCollection;
//
//
//    private void init() {
//        mMenuRecyclerView = (RecyclerView) findViewById(R.id.menu_recycler);
//        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mMenuRecyclerView.setHasFixedSize(true);
//        mMenuCollection = new ArrayList<>();
//
//        mAdapter = new MenuAdapter(mMenuCollection, this);
//        mMenuRecyclerView.setAdapter(mAdapter);
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.menu_main);
//        Toast.makeText(this, "TRIGGERESDA", Toast.LENGTH_LONG).show();
//        init();
//        new FetchDataTask().execute();
//    }
//
//// Fetch Data Using Recycler
//
//    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
//
//        String menuString;
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            String requestURL = String.format("https://api.eatstreet.com/publicapi/v1/restaurant/c85a9e919b422481f15b4f2e73e7618060989f38171f6ca6/menu");
//            //Uri builtUri = Uri.parse(getString(R.string.zomato_api));
//            URL url;
//            try {
//                url = new URL(requestURL);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                //urlConnection.setRequestProperty("user-key", "a9f2b3a292aae939ea358f43062c1f8b");
//                urlConnection.connect();
//
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    //Nothing to do
//                    Log.v("NULL NULL NULL", "NULLL ULLL ASNANAN ANANAN NA NA  _______!!!!!!+++++++" );
//                    return null;
//                }
//
//
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//
//                if (buffer.length() == 0) {
//                    return null;
//                }
//
//                menuString = buffer.toString();
//                JSONObject jsonObject = new JSONObject(menuString);
//                //Toast.makeText(home.this, mZomatoString, Toast.LENGTH_LONG).show();
//                //Log.v("Response", jsonObject.toString());
//
//                JSONArray restaurantsArray = jsonObject.getJSONArray("menu");
//
//                //list = new ArrayList<>();
//                for (int i = 0; i < restaurantsArray.length(); i++) {
//
//
//                    JSONObject jRestaurant = restaurantsArray.getJSONObject(i);
//                    String fname = jRestaurant.getString("foodName");
//                    String cost = jRestaurant.getString("cost");
//
//                    menuModel menu = new menuModel();
//                    menu.setFoodName(fname);
//                    menu.setCost(cost);
//                    mMenuCollection.add(menu);
//                    //menu.setId(id);
//                    //restaurant.setLatitiude(lat);
//                    //restaurant.setLongitude(lon);
////                    restaurant.setCurrency(currency);
//                    //restaurant.setCost(String.valueOf(cost));
////                    restaurant.setImageUrl(imageUrl);
//                    //restaurant.setRating(String.valueOf(waitTime));
//
//
//
////                    //Log.v("BRAD_", i + "");
////                    String id;
////                    String name;
////                    String address;
////                    String currency;
////                    String imageUrl;
////                    long lon;
////                    long lat;
////                    long cost;
////                    float rating;
////
////
////
////                    JSONObject jRestaurant = restaurantsArray.getJSONObject(i);
////
////                    //jRestaurant = jRestaurant.getJSONObject("restaurant");
////
////                    //JSONObject jLocattion = jRestaurant.getJSONObject("location");
////                    //JSONObject jRating = jRestaurant.getJSONObject("user_rating");
////
////                    //id = jRestaurant.getString("id");
////                    name = jRestaurant.getString("name");
////                    Log.w("name", name);
////                    //address = jLocattion.getString("address");
////                    //lat = jLocattion.getLong("latitude");
////                    //lon = jLocattion.getLong("longitude");
////                    currency = jRestaurant.getString("currency");
////                    cost = jRestaurant.getInt("average_cost_for_two");
////                    imageUrl = jRestaurant.getString("featured_image");
////                    //rating = (float) jRating.getDouble("aggregate_rating");
////                    Restaurant restaurant = new Restaurant();
////                    restaurant.setName(name);
////                    //restaurant.setAddress(address);
////                    //restaurant.setLatitiude(lat);
////                    //restaurant.setLongitude(lon);
////                    restaurant.setCurrency(currency);
////                    restaurant.setCost(String.valueOf(cost));
////                    restaurant.setImageUrl(imageUrl);
////                    //restaurant.setRating(String.valueOf(rating));
////
////                    mRestaurantCollection.add(restaurant);
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e("MainActivity", "Error closing stream", e);
//                    }
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
////        if(mSwipeRefreshLayout.isRefreshing()){
////            mSwipeRefreshLayout.setRefreshing(false);
////        }
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//}