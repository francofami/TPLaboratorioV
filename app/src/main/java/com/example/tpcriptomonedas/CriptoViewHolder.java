package com.example.tpcriptomonedas;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CriptoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nombreCripto;
    TextView exchangeCripto;
    TextView precioCompra;
    TextView precioVenta;

    private IOnItemClick listener;
    private int position;

    public CriptoViewHolder(View view, IOnItemClick listener) {
        super(view);
        this.nombreCripto = view.findViewById(R.id.nombreCripto);

        // this.nombreCripto = view.findViewById(R.id.exchangeCripto);
        // this.nombreCripto = view.findViewById(R.id.precioCompra);
        // this.nombreCripto = view.findViewById(R.id.precioVenta);

        view.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(position);
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
