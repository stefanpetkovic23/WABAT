package com.example.swev2.model;

public class Booking {
    public String idleta, kartica,sediste,korisnikId;
    public Integer prtljag,ukupnaCena;

    public Booking(){}

    public Booking(String idleta, String kartica, String sediste, String korisnikId, Integer prtljag, Integer ukupnaCena) {
        this.idleta = idleta;
        this.kartica = kartica;
        this.sediste = sediste;
        this.korisnikId = korisnikId;
        this.prtljag = prtljag;
        this.ukupnaCena = ukupnaCena;
    }

    public String getIdleta() {
        return idleta;
    }

    public void setIdleta(String idleta) {
        this.idleta = idleta;
    }

    public String getKartica() {
        return kartica;
    }

    public void setKartica(String kartica) {
        this.kartica = kartica;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

    public Integer getPrtljag() {
        return prtljag;
    }

    public void setPrtljag(Integer prtljag) {
        this.prtljag = prtljag;
    }

    public Integer getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(Integer ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
