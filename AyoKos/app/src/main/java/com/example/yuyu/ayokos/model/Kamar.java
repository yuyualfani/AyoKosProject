package com.example.yuyu.ayokos.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YUYU on 08/01/2018.
 */

public class Kamar {
//    private String id,nama;
//    private double latitude,longitude;
//
////    public Kamar(String id, String nama, double latitude, double longitude) {
////        this.id = id;
////        this.nama = nama;
////        this.latitude = latitude;
////        this.longitude = longitude;
////    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getNama() {
//        return nama;
//    }
//
//    public void setNama(String nama) {
//        this.nama = nama;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Map<String, Object> toMap()
//    {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("nama",nama);
//        result.put("latitude",latitude);
//        result.put("longitude",longitude);
//
//        return result;
//
//    }
private String id,nama,harga,stock,urll, idPm,namaPemilik,latitude,longitude;


    public Kamar(String id, String nama, String harga, String stock,
                 String urll, String idPm, String namaPemilik, String latitude, String longitude) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stock = stock;
        this.urll = urll;
        this.idPm = idPm;
        this.namaPemilik = namaPemilik;
        this.latitude = latitude;
        this.longitude = longitude;

    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Kamar() {
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getUrll() {
        return urll;
    }

    public void setUrll(String urll) {
        this.urll = urll;
    }

    public String getIdPm() {
        return idPm;
    }

    public void setIdPm(String idPm) {
        this.idPm = idPm;
    }
}