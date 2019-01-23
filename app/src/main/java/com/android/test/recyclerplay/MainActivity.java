package com.android.test.recyclerplay;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.android.test.recyclerplay.adapters.CarAdapter;
import com.android.test.recyclerplay.models.Car;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener {

    RecyclerView recyclerView;
    CarAdapter adapter;
    List<Car> cars;
    FloatingActionButton fab;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(this);
        createCars();
        adapter = new CarAdapter(cars, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void createCars() {
        cars = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            cars.add(new Car("brand # " + i, "model # " + i));
        }
    }

    @Override
    public void onItemClicked(String itemName) {
        Log.d("TAG", "onItemClicked: " + itemName);
    }

    @Override
    public void onItemRemoved(int position) {
        cars.remove(position);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fabAdd) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
            builder.setView(view1);
            final EditText brandEdit = view1.findViewById(R.id.addItemBrandEdit);
            final EditText modelEdit = view1.findViewById(R.id.addItemModelEdit);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Car car = new Car(String.valueOf(brandEdit.getText()),
                            String.valueOf(modelEdit.getText()));
                    cars.add(4, car);
                    dialog.dismiss();
                }
            });

            dialog = builder.show();
        }
    }
}
