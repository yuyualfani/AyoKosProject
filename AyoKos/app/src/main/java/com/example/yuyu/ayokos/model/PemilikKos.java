package com.example.yuyu.ayokos.model;

/**
 * Created by YUYU on 07/01/2018.
 */

public class PemilikKos {
    private String id,nama,alamat,email,url;

    public PemilikKos() {

    }

    public PemilikKos(String id, String nama, String alamat, String email, String url) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.url = url;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

