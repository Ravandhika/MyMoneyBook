package com.example.mymoneybook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList nominal, keterangan, tanggal;

    RecyclerViewAdapter(Context context, ArrayList nominal, ArrayList keterangan, ArrayList tanggal) {
        this.context = context;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }
        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_cashflow, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_nominal.setText(String.valueOf(nominal.get(position)));
        holder.txt_keterangan.setText(String.valueOf(keterangan.get(position)));
        holder.txt_tanggal.setText(String.valueOf(tanggal.get(position)));
    }

    @Override
    public int getItemCount() {
        return nominal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nominal, txt_keterangan, txt_tanggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nominal = itemView.findViewById(R.id.text_nominal);
            txt_keterangan = itemView.findViewById(R.id.text_keterangan);
            txt_tanggal = itemView.findViewById(R.id.text_tanggal);
        }
    }
}
