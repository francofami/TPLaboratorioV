package com.example.tpcriptomonedas;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ArbitrajeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView exchangeA;
    TextView exchangeB;
    TextView precioCompra;
    TextView precioVenta;
    TextView porcentajeGanancia;
    TextView moneda;

    private IOnItemClick listener;
    private int position;

    public ArbitrajeViewHolder(View view, IOnItemClick listener) {
        super(view);

        this.exchangeA = view.findViewById(R.id.nombreExchangeA);
        this.exchangeB = view.findViewById(R.id.nombreExchangeB);
        this.precioCompra = view.findViewById(R.id.precioCompra);
        this.precioVenta = view.findViewById(R.id.precioVenta);
        this.porcentajeGanancia = view.findViewById(R.id.porcentajeGanancia);
        this.moneda = view.findViewById(R.id.moneda);

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
