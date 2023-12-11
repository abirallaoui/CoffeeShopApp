package com.example.movies_project.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.content.DialogInterface;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.movies_project.Adapter.CoffeeAdapter;
import com.example.movies_project.Adapter.CoffeeListAdapter;
import com.example.movies_project.Model.Product;
import com.example.movies_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  RecyclerViewInterface{
    private RecyclerView coffeeRecyclerView, cookiesRecyclerView;
    private ProgressBar loading1;
    private ImageView logout;
    private FloatingActionButton toCookies;
    private SearchView searchView;



    private FirebaseAuth auth;
    private FirebaseUser user;
    private TextView nom;
    private ImageView profilBtn;
    List<Product> listCoffee= new ArrayList<>();
    Dialog dialog;
    CoffeeAdapter coffeeAdapter=new CoffeeAdapter(MainActivity.this,listCoffee,this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        nom = findViewById(R.id.textView7);
        logout = findViewById(R.id.logout);
        toCookies=findViewById(R.id.toCookies);
        loading1 = findViewById(R.id.loading1);
        profilBtn=findViewById(R.id.profilBtn);

        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });



        //list coffee
        coffeeRecyclerView = findViewById(R.id.coffeeRecyclerView);
        coffeeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        listCoffee.add(new Product(R.drawable.espresso,"Espresso"," A concentrated coffee brewed by forcing hot water through finely-ground coffee beans. It's served in small shots and has a strong, rich flavor.","$3",2,4.7));
        listCoffee.add(new Product(R.drawable.capuccino,"Cappuccino","Made with equal parts of espresso, steamed milk, and foam. It's known for its creamy texture and often topped with a sprinkle of cocoa or cinnamon.","$4.50",70,4.5));
        listCoffee.add(new Product(R.drawable.icedcoffee,"Iced Coffee","Chilled coffee brewed with care, poured over ice for a refreshing, caffeinated drink. Customizable with milk or flavor syrups.","$3.50",150,4.1));
        listCoffee.add(new Product(R.drawable.whipped,"Whipped coffee","A creamy, frothy treat made by whipping together instant coffee, sugar, and hot water. A trendy delight!","$5",250,4.8));

        coffeeRecyclerView.setAdapter(coffeeAdapter);




        if (user == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
           // nom.setText(user.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        toCookies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListCookiesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.alertbox);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView yes=dialog.findViewById(R.id.yes);
        TextView no=dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void filterList(String text) {
        List<Product> filteredList=new ArrayList<>();

        for (Product product:listCoffee){
            if (product.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(product);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No coffee found", Toast.LENGTH_SHORT).show();
        }else {
            coffeeAdapter.setFilteredList(filteredList);
        }

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("title",listCoffee.get(position).getTitle());
        intent.putExtra("image",listCoffee.get(position).getImage());
        intent.putExtra("description",listCoffee.get(position).getDescription());
        intent.putExtra("price",listCoffee.get(position).getPrice());
        intent.putExtra("productRate",listCoffee.get(position).getProductRate());
        intent.putExtra("NutritionalValues",listCoffee.get(position).getNutritionalValues());


        startActivity(intent);


    }



}
