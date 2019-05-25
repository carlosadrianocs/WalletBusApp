package com.walletbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walletbus.R;
import com.walletbus.model.Movimentacao;

import java.util.List;


/**
 * Created by Carlos Adriano
 */

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Movimentacao> movimentacoes;
    Context context;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movimentacao movimentacao = movimentacoes.get(position);


        holder.categoria.setText(movimentacao.getCategoria());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.data.setText(movimentacao.getData());


    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  valor, categoria, data;


        public MyViewHolder(View itemView) {
            super(itemView);

            categoria = itemView.findViewById(R.id.textAdapterCategoria);
            valor = itemView.findViewById(R.id.textAdapterValor);
            data = itemView.findViewById(R.id.textAdapterData);

        }

    }

}
