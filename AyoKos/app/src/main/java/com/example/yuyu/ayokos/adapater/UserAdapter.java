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

import com.example.yuyu.ayokos.DetailPencari;
import com.example.yuyu.ayokos.R;
import com.example.yuyu.ayokos.model.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by YUYU on 06/01/2018.
 */

public class UserAdapter extends RecyclerView.Adapter <UserAdapter.ViewHolder> {

    private Context context;
    private ArrayList<User> rvData;


    public UserAdapter() {
    }

    public UserAdapter(Context context, ArrayList<User> rvData) {
        this.context = context;
        this.rvData = rvData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder (v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User pencari = rvData.get(position);

        holder.nama.setText(rvData.get(position).getNama());

        loadImageFromUrl(rvData.get(position).getUrl(), holder.foto);

        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), pencari.getId().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DetailPencari.class);

                i.putExtra("pencariId", pencari.getId());
                i.putExtra("nama",pencari.getNama());
                i.putExtra("alamat",pencari.getAlamat());
                i.putExtra("email",pencari.getEmail());
                i.putExtra("foto",pencari.getUrl());


                Bundle b = new Bundle();
                b.putString("id", pencari.getId());

                b.putString("n", pencari.getNama());
                b.putString("a", pencari.getAlamat());
                b.putString("e", pencari.getEmail());
                b.putString("u", pencari.getUrl());

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
        public TextView nama;
        public ImageView foto;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView. findViewById(R.id.nama);
            foto = (ImageView)itemView.findViewById(R.id.foto);
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
