package com.example.tpcriptomonedas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CriptoAdapter extends RecyclerView.Adapter<CriptoViewHolder>{

    private List<Cripto> lista;
    private IOnItemClick listener;

    public CriptoAdapter(List<Cripto> lista, IOnItemClick listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    @NonNull
    @Override
    public CriptoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        CriptoViewHolder criptoViewHolder = new CriptoViewHolder(view, listener);

        return criptoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CriptoViewHolder holder, int position) {
        Cripto cripto = this.lista.get(position);

        holder.nombreCripto.setText(cripto.getCripto());
        // holder.exchangeCripto.setText(cripto.getExchange());
        holder.precioCompra.setText(cripto.getTotalAsk().toString());
        holder.precioVenta.setText(cripto.getTotalBid().toString());
        holder.setPosition(position);
    }
}
