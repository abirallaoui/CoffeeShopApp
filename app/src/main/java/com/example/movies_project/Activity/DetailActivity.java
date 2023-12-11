package com.example.movies_project.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movies_project.Domain.Datum;
import com.example.movies_project.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity implements  View.OnClickListener{

    private ImageView backToCoffeeList;

    private ProgressBar progressBar;
    private TextView titleProduct, productRate, NutritionalValues, descriptionProduct, priceProduct;
    private NestedScrollView scrollView;

    private ImageView posterBigImg, posterNormalImg,btn_favorite;
    private RecyclerView.Adapter adapterImgList;
    private RecyclerView recyclerView;

    Dialog dialog;


    Button btn_add,btn_amount,btn_remove,choose;
    int amount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backToCoffeeList = findViewById(R.id.backToCoffeeList);

        titleProduct = findViewById(R.id.titleProduct);
        priceProduct = findViewById(R.id.product_price);

        productRate = findViewById(R.id.productRate);
        NutritionalValues = findViewById(R.id.NutritionalValues);
        descriptionProduct = findViewById(R.id.detailCoffee);
        scrollView = findViewById(R.id.scrollView3);
        posterBigImg = findViewById(R.id.posterBigImg);
        posterNormalImg = findViewById(R.id.posterNormalImg);
        btn_favorite = findViewById(R.id.btn_favorite);


        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        int image = getIntent().getIntExtra("image", 0);
        String price = getIntent().getStringExtra("price");
        double Nutritional = getIntent().getDoubleExtra("NutritionalValues", 0);
        double Rate = getIntent().getDoubleExtra("productRate", 0);


        titleProduct.setText(title);
        priceProduct.setText(price);

        descriptionProduct.setText(description);

        posterBigImg.setImageResource(image);
        posterNormalImg.setImageResource(image);

        productRate.setText(String.valueOf(Rate));
        NutritionalValues.setText(String.valueOf(Nutritional));

        btn_add = findViewById(R.id.btn_add);
        btn_amount = findViewById(R.id.btn_amount);
        btn_remove= findViewById(R.id.btn_remove);
        choose = findViewById(R.id.choose);

        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_amount.setOnClickListener(this);


        btn_amount.setText(String.valueOf(amount));




        backToCoffeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_favorite.isSelected()){
                    btn_favorite.setSelected(false);
                }else {
                    btn_favorite.setSelected(true);

                }
            }
        });




    }




    public void enregister(View view) {
        AlertDialog.Builder confirm=new  AlertDialog.Builder(DetailActivity.this);

        String priceText = priceProduct.getText().toString();
        priceText = priceText.replace("$", ""); // Remove the "$" symbol
        double price = Double.parseDouble(priceText);
        double totalPrice = price * amount;

        // Display a toast with the total amount to pay
        String confirmMessage = "Total to pay: $" + totalPrice+"\n Delivred After 15min";
        confirm.setMessage(confirmMessage)
                .setTitle("Confirmation")
                .setIcon(R.drawable.confirm)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DetailActivity.this, "Check your Email to confirm adress", Toast.LENGTH_SHORT).show();
                    }
                })


                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .show();

    }

    @Override
    public void onClick(View v) {

            if (v.getId() == R.id.btn_add) {
                amount += 1;
                btn_amount.setText(String.valueOf(amount));
            } else if (v.getId() == R.id.btn_remove) {
                amount -= 1;
                if (amount < 0) {
                    amount = 0;
                    btn_amount.setText(String.valueOf(amount));
                }
                btn_amount.setText(String.valueOf(amount));
            }

        /* else if (v.getId() == R.id.choose) {
            // Calculate the amount to pay based on the price and quantity
            double price = Double.parseDouble(priceProduct.getText().toString()); // Get the price
            double totalPrice = price * amount; // Calculate the total amount to pay

            // Display a toast with the total amount to pay
            String toastMessage = "Total to pay: $" + totalPrice;
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }*/
        btn_amount.setText(String.valueOf(amount));

    }


}