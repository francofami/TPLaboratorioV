package com.example.tpcriptomonedas;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class CalcularActivity extends AppCompatActivity implements IOnItemClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Calcular ganancia");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = super.getIntent().getExtras();

        Serializable arbitraje = extras.getSerializable("arbitraje");

        CalcularController calcularController = new CalcularController((Arbitraje) arbitraje);
        CalcularView calcularView = new CalcularView(this, (Arbitraje) arbitraje, calcularController);

        calcularController.setCalcularView(calcularView);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home) {

            super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {

    }


}
