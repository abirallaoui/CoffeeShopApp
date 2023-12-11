package com.example.movies_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_project.Activity.DetailActivity;
import com.example.movies_project.Domain.CoffeeItem;
import com.example.movies_project.Domain.DetailCoffee;
import com.example.movies_project.R;

public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.ViewHolder> {
    DetailCoffee items;
    Context context;

    public CoffeeListAdapter(DetailCoffee items) {
        this.items = items;
    }


    @NonNull
    @Override
    public CoffeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        context=parent.getContext();

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.getData().get(position).getName());
        //holder.ScoreTxt.setText(""+items.getData().get(position).getImdbRating());

        Glide.with(holder.itemView.getContext())
                .load(items.getData().get(position).getImage())
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent= new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id",items.getData().get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.getData().size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
    TextView titleTxt,ScoreTxt;
    ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            ScoreTxt=itemView.findViewById(R.id.scoreTxt);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
