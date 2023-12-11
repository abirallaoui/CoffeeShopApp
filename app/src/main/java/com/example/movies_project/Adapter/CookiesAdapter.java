


package com.example.movies_project.Adapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.movies_project.Activity.RecyclerViewInterface;
        import com.example.movies_project.Model.Product;
        import com.example.movies_project.R;

        import java.util.List;

public class CookiesAdapter extends RecyclerView.Adapter<CookiesAdapter.CookiesViewHolder> {
    private  final RecyclerViewInterface recyclerViewInterface;


    private List<Product> cookiesList;
    private Context mContext;

    public void setFilteredList(List<Product> filteredList){
        this.cookiesList=filteredList;
        notifyDataSetChanged();
    }



    public  CookiesAdapter(Context context, List<Product> cookiesList, RecyclerViewInterface recyclerViewInterface){
        this.mContext = context;

        this.cookiesList=cookiesList;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public CookiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return  new CookiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CookiesViewHolder holder, int position) {
        holder.cookiesTitle.setText(cookiesList.get(position).getTitle());
        holder.cookiesImage.setImageResource(cookiesList.get(position).getImage());
        holder.cookiesPrice.setText(cookiesList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return cookiesList.size();
    }

    public class  CookiesViewHolder extends  RecyclerView.ViewHolder{

        private TextView cookiesTitle,cookiesPrice;
        private ImageView cookiesImage;

        private Button choose;
        private CardView card;

        public CookiesViewHolder(@NonNull View itemView) {
            super(itemView);

            cookiesTitle=itemView.findViewById(R.id.product_title);
            cookiesImage=itemView.findViewById(R.id.product_image);
            cookiesPrice=itemView.findViewById(R.id.product_price);

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
