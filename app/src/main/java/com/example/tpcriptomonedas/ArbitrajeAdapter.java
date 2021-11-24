package com.example.tpcriptomonedas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArbitrajeAdapter extends RecyclerView.Adapter<ArbitrajeViewHolder> {

    private List<Arbitraje> lista;
    private IOnItemClick listener;

    public ArbitrajeAdapter(List<Arbitraje> lista, IOnItemClick listener) {
        this.lista = lista;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ArbitrajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ArbitrajeViewHolder arbitrajeViewHolder = new ArbitrajeViewHolder(view, listener);

        return arbitrajeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArbitrajeViewHolder holder, int position) {
        Arbitraje arbitraje = this.lista.get(position);

        holder.exchangeA.setText(arbitraje.getExchangeA());
        holder.exchangeB.setText(arbitraje.getExchangeB());
        holder.precioCompra.setText(arbitraje.getPrecioCompra().toString());
        holder.precioVenta.setText(arbitraje.getPrecioVenta().toString());
        holder.porcentajeGanancia.setText(arbitraje.getPorcentajeGanancia().toString());
        holder.moneda.setText(arbitraje.getMoneda());

        holder.setPosition(position);

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }
}
