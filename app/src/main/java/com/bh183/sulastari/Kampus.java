package com.bh183.sulastari;

public class Kampus {

    private int idKampus;
    private String nama;
    private String alamat;
    private String gambar;
    private String caption;
    private String telepon;
    private String sejarah;
    private String link;

    public Kampus(int idKampus, String nama, String alamat, String gambar, String caption, String telepon, String sejarah, String link) {
        this.idKampus = idKampus;
        this.nama = nama;
        this.alamat = alamat;
        this.gambar = gambar;
        this.caption = caption;
        this.telepon = telepon;
        this.sejarah = sejarah;
        this.link = link;
    }

    public int getIdKampus() {
        return idKampus;
    }

    public void setIdKampus(int idKampus) {
        this.idKampus = idKampus;
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

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getSejarah() {
        return sejarah;
    }

    public void setSejarah(String sejarah) {
        this.sejarah = sejarah;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
