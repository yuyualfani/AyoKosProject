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

import com.example.yuyu.ayokos.DetailKamar;
import com.example.yuyu.ayokos.DetailKamarKos;
import com.example.yuyu.ayokos.DetailPemilik;
import com.example.yuyu.ayokos.R;
import com.example.yuyu.ayokos.model.Kamar;
import com.example.yuyu.ayokos.model.Pemesanan;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by YUYU on 21/01/2018.
 */

public class KamarAdapter extends  RecyclerView.Adapter <KamarAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Kamar> rvData;

    public KamarAdapter(Context context, ArrayList<Kamar> rvData) {

        this.context = context;
        this.rvData = rvData;
    }

    public KamarAdapter() {
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamar, parent, false);
        return new KamarAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Kamar kamar = rvData.get(position);

        holder.nama.setText(rvData.get(position).getNama());

        loadImageFromUrl(rvData.get(position).getUrll(), holder.img);

        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(view.getContext(), kamar.getId().toString(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(),DetailKamar.class);
                Bundle b = new Bundle();
                b.putString("kamarId", kamar.getId());
                b.putString("nama",kamar.getNama());
                b.putString("lattt", kamar.getLatitude());
                b.putString("longg", kamar.getLongitude());
                b.putString("harga",kamar.getHarga());
                b.putString("stock",kamar.getStock());
                b.putString("foto",kamar.getUrll());

                b.putString("namapemilik",kamar.getNamaPemilik());

                i.putExtras(b);
                view.getContext().startActivity(i);

//                System.out.println(kamar.getLatitude() + " ghfgjkhtgbvfdcx");
            }
        });


    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,stock,harga;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView. findViewById(R.id.namakos);
            stock = (TextView) itemView.findViewById(R.id.stock);
            img = (ImageView) itemView.findViewById(R.id.fotokamar1);
            harga = (TextView) itemView.findViewById(R.id.harga);
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
