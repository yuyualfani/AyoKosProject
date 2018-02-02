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

import com.example.yuyu.ayokos.DetailAdmin;
import com.example.yuyu.ayokos.DetailPemilik;
import com.example.yuyu.ayokos.R;
import com.example.yuyu.ayokos.model.Admin;
import com.example.yuyu.ayokos.model.Kamar;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by YUYU on 26/01/2018.
 */

public class AdminAdapter extends  RecyclerView.Adapter <AdminAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Admin> rvData;

    public AdminAdapter(Context context, ArrayList<Admin> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    public AdminAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin, parent, false);
        return new AdminAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Admin adm = rvData.get(position);

        holder.nama.setText(rvData.get(position).getNama());

        loadImageFromUrl(rvData.get(position).getUrl(), holder.foto);

        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), adm.getId().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DetailAdmin.class);

                i.putExtra("adminiId", adm.getId());
                i.putExtra("nama",adm.getNama());
                i.putExtra("alamat",adm.getAlamat());
                i.putExtra("email",adm.getEmail());
                i.putExtra("foto",adm.getUrl());


                Bundle b = new Bundle();
                b.putString("id", adm.getId());
                b.putString("n", adm.getNama());
                b.putString("a", adm.getAlamat());
                b.putString("e", adm.getEmail());
                b.putString("u", adm.getUrl());

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
            nama = (TextView)itemView. findViewById(R.id.namadmin);
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
