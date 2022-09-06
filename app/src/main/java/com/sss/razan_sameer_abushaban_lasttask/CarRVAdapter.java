package com.sss.razan_sameer_abushaban_lasttask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarRVAdapter  extends RecyclerView.Adapter<CarRVAdapter.carViewHolder>{
    private ArrayList<Car> cars;
    private OnRecyclerViewItemClickListener listener;

    public ArrayList<Car> getCars(ArrayList<Car> cars) {
        return this.cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public CarRVAdapter(ArrayList<Car> cars, OnRecyclerViewItemClickListener listener){
        this.cars = cars;
        this.listener=listener;
    }

    @NonNull
    @Override
    public carViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car_layout,null, false);
        carViewHolder viewHolder = new carViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull carViewHolder holder, int position) {
        Car c = cars.get(position);
        if (c.getImage() != null && c.getImage().isEmpty())
          holder.iv.setImageURI(Uri.parse(c.getImage()));
        else {
            holder.iv.setImageResource(R.drawable._);
        }
        holder.tv_model.setText(c.getModel());
        holder.tv_color.setText(c.getColor());
        try{
            holder.tv_color.setTextColor(Color.parseColor(c.getColor()));

        }
        catch (Exception e){

        }
        holder.tv_dbl.setText(String.valueOf(c.getDpl()));

        holder.iv.setTag(c.getId());

    }

    @Override
    public int getItemCount() {

        return cars.size();
    }

    class carViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv_model, tv_color, tv_dbl;

        public carViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.custom_car_iv);
            tv_model = itemView.findViewById(R.id.custom_car_tv_model);
            tv_color = itemView.findViewById(R.id.custom_car_tv_color);
            tv_dbl = itemView.findViewById(R.id.custom_car_tv_dbl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int) iv.getTag();
                    listener.onItemClick(10);
                }
            });

        }
    }
}
