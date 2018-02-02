package com.example.yuyu.ayokos.model;

/**
 * Created by YUYU on 14/01/2018.
 */

public class Pemesanan {

    private String id_pemesanan,id_pencari,id_kamar,namappencari,
            namakos,namapemilik,jumlah,status,tanggal,tandabukti,total;

    public Pemesanan(String id_pemesanan, String id_pencari, String id_kamar,
                     String namappencari, String namakos, String namapemilik,
                     String jumlah, String status, String tanggal, String tandabukti,String total) {
        this.id_pemesanan = id_pemesanan;
        this.id_pencari = id_pencari;
        this.id_kamar = id_kamar;
        this.namappencari = namappencari;
        this.namakos = namakos;
        this.namapemilik = namapemilik;
        this.jumlah = jumlah;
        this.status = status;
       this.tanggal = tanggal;
        this.tandabukti = tandabukti;
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTandabukti() {
        return tandabukti;
    }

    public void setTandabukti(String tandabukti) {
        this.tandabukti = tandabukti;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamapemilik() {
        return namapemilik;
    }

    public void setNamapemilik(String namapemilik) {
        this.namapemilik = namapemilik;
    }

    public String getNamakos() {
        return namakos;
    }

    public void setNamakos(String namakos) {
        this.namakos = namakos;
    }

    public Pemesanan() {
    }

    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
    }

    public String getId_pencari() {
        return id_pencari;
    }

    public void setId_pencari(String id_pencari) {
        this.id_pencari = id_pencari;
    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getNamappencari() {
        return namappencari;
    }

    public void setNamappencari(String namappencari) {
        this.namappencari = namappencari;
    }
}
