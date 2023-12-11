package com.example.movies_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.movies_project.Adapter.CoffeeListAdapter;
import com.example.movies_project.Adapter.CookiesAdapter;
import com.example.movies_project.Model.Product;
import com.example.movies_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ListCookiesActivity extends AppCompatActivity implements  RecyclerViewInterface{

    private FloatingActionButton toCoffee;
    private ImageView logout;
    private CoffeeListAdapter  adapterCookies;
    private RecyclerView  cookiesRecyclerView;
    private ProgressBar  loading2;
    private ImageView profilBtn;


    private FirebaseAuth auth;
    private FirebaseUser user;
    private TextView nom;
    private SearchView searchView;

    List<Product> listCookies= new ArrayList<>();
    Dialog dialog;
    CookiesAdapter cookiesAdapter=new CookiesAdapter(ListCookiesActivity.this,listCookies,this);






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cookies);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        nom = findViewById(R.id.textView7);

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

        toCoffee=findViewById(R.id.toCoffee);
        logout = findViewById(R.id.logout);
        profilBtn=findViewById(R.id.profilBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        toCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCookiesActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCookiesActivity.this,ProfilActivity.class);
                startActivity(intent);
                finish();
            }
        });


        cookiesRecyclerView = findViewById(R.id.cookiesRecyclerView);
        cookiesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        listCookies.add(new Product(R.drawable.cookies,"Cookies","Sweet, baked treats often made with ingredients like chocolate chips, nuts, or dried fruits. They come in various flavors and textures, perfect for a sweet accompaniment to your coffee.","$2.5",150,4.9));
        listCookies.add(new Product(R.drawable.croissant,"Croissant"," A delicious French pastry known for its flaky, buttery layers. Often enjoyed plain or filled with butter or jam, it pairs well with the rich flavor of coffee.","$3",240,3.7));
        listCookies.add(new Product(R.drawable.browny,"Brownie"," A dense, rich, and chocolatey baked dessert, often containing nuts or chocolate chips. Its fudgy texture and indulgent flavor make it a delightful treat with coffee.","$5.5",280,4.2));
        listCookies.add(new Product(R.drawable.donult,"Donult"," A round, deep-fried pastry often glazed, filled, or topped with various flavors like chocolate, vanilla, or fruit jams. Its soft and sweet texture makes it a popular choice alongside coffee.","$3.5",300,2.9));

        cookiesRecyclerView.setAdapter(cookiesAdapter);

        loading2 = findViewById(R.id.loading2);

        dialog=new Dialog(ListCookiesActivity.this);
        dialog.setContentView(R.layout.alertbox);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView yes=dialog.findViewById(R.id.yes);
        TextView no=dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ListCookiesActivity.this, LoginActivity.class);
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

        for (Product product:listCookies){
            if (product.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(product);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No cake found", Toast.LENGTH_SHORT).show();
        }else {
            cookiesAdapter.setFilteredList(filteredList);

        }

    }


    public void onItemClick(int position) {
        Intent intent = new Intent(ListCookiesActivity.this, DetailActivity.class);

        intent.putExtra("title",listCookies.get(position).getTitle());
        intent.putExtra("image",listCookies.get(position).getImage());
        intent.putExtra("description",listCookies.get(position).getDescription());
        intent.putExtra("price",listCookies.get(position).getPrice());
        intent.putExtra("productRate",listCookies.get(position).getProductRate());
        intent.putExtra("NutritionalValues",listCookies.get(position).getNutritionalValues());


        startActivity(intent);


    }

}