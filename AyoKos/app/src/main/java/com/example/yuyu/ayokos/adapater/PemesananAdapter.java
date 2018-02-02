package com.example.yuyu.ayokos.adapater;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuyu.ayokos.DetailPemesanan;
import com.example.yuyu.ayokos.DetailPemilik;
import com.example.yuyu.ayokos.KonfirmasiPencari;
import com.example.yuyu.ayokos.LihatPemesananPencari;
import com.example.yuyu.ayokos.R;
import com.example.yuyu.ayokos.model.Pemesanan;
import com.example.yuyu.ayokos.model.PemilikKos;

import java.util.ArrayList;

/**
 * Created by YUYU on 21/01/2018.
 */

public class PemesananAdapter extends  RecyclerView.Adapter <PemesananAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Pemesanan> rvData;
    SharedPreferences sharedPreferences;


    public PemesananAdapter(Context context, ArrayList<Pemesanan> rvData, SharedPreferences sharedPreferences) {
        this.context = context;
        this.rvData = rvData;
        this.sharedPreferences = sharedPreferences;
    }

    public PemesananAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pemesanan, parent, false);
        return new PemesananAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pemesanan pemesanan = rvData.get(position);

        holder.namapemesan.setText(rvData.get(position).getNamappencari());
     //   holder.namakos.setText(rvData.get(position).getNamakos());
        holder.namapemesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), pemesanan.getId_pemesanan().toString(), Toast.LENGTH_SHORT).show();

               String jenisUser = sharedPreferences.getString("user","");

                if (jenisUser.toString().equals("biasa")){
                    Intent i = new Intent(view.getContext(), KonfirmasiPencari.class);
                    Bundle b = new Bundle();

                    b.putString("id_kmr", pemesanan.getId_kamar());
                    b.putString("pemesananId_", pemesanan.getId_pemesanan());
                    b.putString("id_pecari", pemesanan.getId_pencari());
                    b.putString("jumlah", pemesanan.getJumlah());
                    b.putString("namaKost", pemesanan.getNamakos());
                    b.putString("namapemilik", pemesanan.getNamapemilik());
                    b.putString("namapencari", pemesanan.getNamappencari());
                    b.putString("foto", pemesanan.getTandabukti());

                    b.putString("tanggal", pemesanan.getTanggal());

                    b.putString("status__",pemesanan.getStatus());

                    i.putExtra("pemesananId", pemesanan.getId_pemesanan());
                    //  i.putExtra("namakos",pemesanan.getNamakos());
                    i.putExtra("namapencari",pemesanan.getNamappencari());
                    i.putExtra("namapemilik",pemesanan.getNamapemilik());
                    i.putExtra("jumlah",pemesanan.getJumlah());
                    i.putExtra("status",pemesanan.getStatus());
                    i.putExtra("foto",pemesanan.getTandabukti());
                    i.putExtra("tanggal",pemesanan.getTanggal());
                    i.putExtras(b);
                    view.getContext().startActivity(i);
                }else {
                    Intent i = new Intent(view.getContext(), DetailPemesanan.class);
                    Bundle b = new Bundle();

                    b.putString("id_kmr", pemesanan.getId_kamar());
                    b.putString("pemesananId_", pemesanan.getId_pemesanan());
                    b.putString("id_pecari", pemesanan.getId_pencari());
                    b.putString("jumlah", pemesanan.getJumlah());
                    b.putString("namaKost", pemesanan.getNamakos());
                    b.putString("namapemilik", pemesanan.getNamapemilik());
                    b.putString("namapencari", pemesanan.getNamappencari());
                    b.putString("tanggal", pemesanan.getTanggal());
                    b.putString("foto", pemesanan.getTandabukti());
                    b.putString("status__",pemesanan.getStatus());


                    i.putExtra("pemesananId", pemesanan.getId_pemesanan());
                    //  i.putExtra("namakos",pemesanan.getNamakos());
                    i.putExtra("namapencari",pemesanan.getNamappencari());
                    i.putExtra("namapemilik",pemesanan.getNamapemilik());
                    i.putExtra("jumlah",pemesanan.getJumlah());
                    i.putExtra("status",pemesanan.getStatus());
                    i.putExtra("foto",pemesanan.getTandabukti());
                    i.putExtra("tanggal",pemesanan.getTanggal());
                    i.putExtras(b);
                    view.getContext().startActivity(i);
                }













            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namapemesan,namakos,namapemilik,jumlah,tanggal,status;
        public ViewHolder(View itemView) {
            super(itemView);
            namapemesan = (TextView)itemView. findViewById(R.id.namapemesan);
            namakos = (TextView) itemView.findViewById(R.id.namakos);
         //   namapemilik = (TextView)itemView. findViewById(R.id.alamat);
        }
    }
}
