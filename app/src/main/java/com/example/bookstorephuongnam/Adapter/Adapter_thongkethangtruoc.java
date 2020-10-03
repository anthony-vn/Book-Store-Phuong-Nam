package com.example.bookstorephuongnam.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstorephuongnam.Modal.thongkethangtruoc_class;
import com.example.bookstorephuongnam.R;

import java.util.ArrayList;

public class Adapter_thongkethangtruoc extends RecyclerView.Adapter<Adapter_thongkethangtruoc.FeaturedViewHolder>{
    ArrayList<thongkethangtruoc_class> featuredLocations;

    public Adapter_thongkethangtruoc(ArrayList<thongkethangtruoc_class> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design_thongke, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        thongkethangtruoc_class featuredHelperClass = featuredLocations.get(position);
        holder.img.setImageResource(featuredHelperClass.getImage());
        holder.money.setText(featuredHelperClass.getMoney());
        holder.date.setText(featuredHelperClass.getDate());
    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }
    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView money, date;

        public FeaturedViewHolder(@NonNull View itemView){
            super(itemView);

            //Hooks
            img = itemView.findViewById(R.id.image_tktr);
            money = itemView.findViewById(R.id.tv_money_tktr);
            date = itemView.findViewById(R.id.tv_ngay_tktr);
        }
    }
}