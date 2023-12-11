package com.example.movies_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_project.Activity.DetailActivity;
import com.example.movies_project.Activity.RecyclerViewInterface;
import com.example.movies_project.Model.Product;
import com.example.movies_project.R;
import com.google.android.gms.common.internal.service.Common;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private  final RecyclerViewInterface recyclerViewInterface;
    private List<Product> coffeeList;
    private Context mContext;

    public void setFilteredList(List<Product> filteredList){
        this.coffeeList=filteredList;
         notifyDataSetChanged();
    }

    public  CoffeeAdapter(Context context,List<Product> coffeeList,RecyclerViewInterface recyclerViewInterface){
        this.mContext = context;

        this.coffeeList=coffeeList;
        this.recyclerViewInterface=recyclerViewInterface;
    }


    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return  new CoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        holder.coffeeTitle.setText(coffeeList.get(position).getTitle());
        holder.coffeeImage.setImageResource(coffeeList.get(position).getImage());
        holder.coffeePrice.setText(coffeeList.get(position).getPrice());

        //favorite system





    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public class  CoffeeViewHolder extends  RecyclerView.ViewHolder{

        private TextView coffeeTitle,coffeePrice;
        private ImageView coffeeImage;
        private Button choose,btn_favorite;
        private CardView card;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);

            coffeeTitle=itemView.findViewById(R.id.product_title);
            coffeeImage=itemView.findViewById(R.id.product_image);
            coffeePrice=itemView.findViewById(R.id.product_price);
            btn_favorite=itemView.findViewById(R.id.btn_favorite);

            choose=itemView.findViewById(R.id.choose);
            card=itemView.findViewById(R.id.card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }


    }

}
