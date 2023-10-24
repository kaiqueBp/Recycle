package com.kaique.atividade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> {
    private List<Pessoa> dados;
    public MeuAdapter(List dados) {
        this.dados=dados;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha,parent,false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        holder.nome.setText(dados.get(position).getNome());
        holder.telefone.setText(dados.get(position).getTelefone());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public  class MeuViewHolder extends  RecyclerView.ViewHolder{
        TextView nome;
        TextView telefone;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            nome= itemView.findViewById(R.id.nome);
            telefone= itemView.findViewById(R.id.telefone);
        }
    }
}
