//package com.main.eat24;
//
//import android.support.design.widget.CollapsingToolbarLayout;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
//import com.main.eat24.Common.Common;
//import com.main.eat24.Database.Database;
//import com.main.eat24.Model.Food;
//import com.main.eat24.Model.Order;
//import com.main.eat24.R;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//
//import java.util.Random;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class FoodDetail extends AppCompatActivity {
//
//    @BindView(R.id.food_name)
//    TextView txtfood_name;
//
//    @BindView(R.id.food_price)
//    TextView txtfood_price;
//
////    @BindView(R.id.food_description)
////    TextView txtfood_description;
////
////    @BindView(R.id.img_food)
////    ImageView food_image;
//
//    @BindView(R.id.btnCart)
//    FloatingActionButton btnCart;
//
//    @BindView(R.id.number_button)
//    ElegantNumberButton numberButton;
//
//    @BindView(R.id.collapsing)
//    CollapsingToolbarLayout collapsingToolbarLayout;
//
//    String foodId = "";
//
//    Food currentFood;
//
//    FirebaseDatabase database;
//    DatabaseReference foods;
//
//    Random rand = new Random();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_food_detail);
//
//        ButterKnife.bind(this);
//
//        database = FirebaseDatabase.getInstance();
//        foods = database.getReference("Food");
//
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
//
//
//
//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                        if(Common.currentCart.equals(Common.activeCart)){
//                            new Database(getBaseContext()).addToCart(new Order(
//                                    foodId,
//                                    currentFood.getName(),
//                                    numberButton.getNumber(),
//                                    currentFood.getPrice(),
//                                    currentFood.getDiscount()
//                            ));
//                        }
//                        else{
//                            Toast.makeText(FoodDetail.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//
//
//            }
//
//        });
//        //get Food id from intent
//        if (getIntent() != null) {
//            foodId = getIntent().getStringExtra("FoodId");
//        }
//        if (!foodId.isEmpty()) {
//            getDetailFood(foodId);
//        }
//    }
//
//    private void getDetailFood(final String foodId) {
//        foods.child(foodId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                currentFood = dataSnapshot.getValue(Food.class);
//
//                //set Image
//                //Picasso.get().load(currentFood.getImage()).into(food_image);
//
//                //collapsingToolbarLayout.setTitle(currentFood.getName());
//
//                txtfood_price.setText(currentFood.getPrice());
//
//                //txtfood_description.setText(currentFood.getDescription());
//
//                txtfood_name.setText(currentFood.getName());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
