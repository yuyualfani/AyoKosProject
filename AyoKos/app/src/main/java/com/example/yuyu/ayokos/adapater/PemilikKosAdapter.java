package com.example.yuyu.ayokos.adapater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuyu.ayokos.DetailPemilik;
import com.example.yuyu.ayokos.DetailPencari;
import com.example.yuyu.ayokos.R;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.example.yuyu.ayokos.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by YUYU on 07/01/2018.
 */

public class PemilikKosAdapter extends  RecyclerView.Adapter <PemilikKosAdapter.ViewHolder> {


    private Context context;
    private ArrayList<PemilikKos> rvData;


    public PemilikKosAdapter(Context context, ArrayList<PemilikKos> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    public PemilikKosAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pemilik, parent, false);
        return new PemilikKosAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PemilikKos pemilik = rvData.get(position);

        holder.nama.setText(rvData.get(position).getNama());

        loadImageFromUrl(rvData.get(position).getUrl(), holder.foto);

        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), pemilik.getId().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DetailPemilik.class);

                i.putExtra("pemilikiId", pemilik.getId());
                i.putExtra("nama",pemilik.getNama());
                i.putExtra("alamat",pemilik.getAlamat());
                i.putExtra("email",pemilik.getEmail());
                i.putExtra("foto",pemilik.getUrl());


                Bundle b = new Bundle();
                b.putString("id", pemilik.getId());
                b.putString("n", pemilik.getNama());
                b.putString("a", pemilik.getAlamat());
                b.putString("e", pemilik.getEmail());
                b.putString("u", pemilik.getUrl());

                i.putExtras(b);
                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,alamat;
        public ImageView foto;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView. findViewById(R.id.nama);
            foto = (ImageView)itemView.findViewById(R.id.foto);
            alamat = (TextView)itemView. findViewById(R.id.alamat);
        }
    }
    private void loadImageFromUrl(String uri,ImageView bukti){
        Picasso.with(context).load(uri).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(bukti, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
