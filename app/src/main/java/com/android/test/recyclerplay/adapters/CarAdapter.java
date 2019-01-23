package com.android.test.recyclerplay.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.test.recyclerplay.ItemClickListener;
import com.android.test.recyclerplay.R;
import com.android.test.recyclerplay.models.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ItemHolder> {

    private List<Car> cars;
    private ItemClickListener listener;

    public CarAdapter(List<Car> cars, ItemClickListener listener) {
        this.cars = cars;
        this.listener = listener;
    }

    public CarAdapter(List<Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.car_item, viewGroup, false );
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder itemHolder, int position) {
        Car car = cars.get(position);
        itemHolder.brandNameItem.setText(car.getBrandName());
        itemHolder.modelNameItem.setText(car.getModelName());

        itemHolder.itemClickContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(cars.get(itemHolder.getAdapterPosition()).getBrandName()
                        + " "
                        + cars.get(itemHolder.getAdapterPosition()).getModelName());
            }
        });

        itemHolder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemRemoved(itemHolder.getAdapterPosition());
                notifyItemRemoved(itemHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView brandNameItem, modelNameItem;
        ImageView removeItem;
        View itemClickContainer;

        ItemHolder(@NonNull View itemView) {
            super(itemView);

            brandNameItem = itemView.findViewById(R.id.brandNameItem);
            modelNameItem = itemView.findViewById(R.id.modelNameItem);
            removeItem = itemView.findViewById(R.id.removeItem);
            itemClickContainer = itemView.findViewById(R.id.itemClickContainer);
        }
    }
}
