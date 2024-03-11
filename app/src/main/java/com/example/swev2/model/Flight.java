package com.example.swev2.model;

import java.io.Serializable;
import java.util.List;

public class Flight implements Serializable {

    public Integer cena;
    public String dolazak;
    public String gate;
    public String polazak;
    public String sifra_leta;
    public String vreme_dolaska;
    public String vreme_polaska;
    public String trajanje;
    public String datum_polaska;

    public String getDolazak3() {
        return dolazak3;
    }

    public void setDolazak3(String dolazak3) {
        this.dolazak3 = dolazak3;
    }

    public String getPolazak3() {
        return polazak3;
    }

    public void setPolazak3(String polazak3) {
        this.polazak3 = polazak3;
    }

    public List<List<Integer>> getSedista() {
        return sedista;
    }

    public void setSedista(List<List<Integer>> sedista) {
        this.sedista = sedista;
    }

    public String dolazak3;
    public String polazak3;
    public List<List<Integer>> sedista;



    public Flight(){};

    public String getDatum_polaska() {
        return datum_polaska;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public Integer getCena() {
        return cena;
    }

    public String getDolazak() {
        return dolazak;
    }

    public String getPolazak() {
        return polazak;
    }

    public String getGate() {
        return gate;
    }

    public String getSifra_leta() {
        return sifra_leta;
    }

    public String getVreme_dolaska() {
        return vreme_dolaska;
    }

    public String getVreme_polaska() {
        return vreme_polaska;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public void setDolazak(String dolazak) {
        this.dolazak = dolazak;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public void setPolazak(String polazak) {
        this.polazak = polazak;
    }

    public void setSifra_leta(String sifra_leta) {
        this.sifra_leta = sifra_leta;
    }

    public void setVreme_dolaska(String vreme_dolaska) {
        this.vreme_dolaska = vreme_dolaska;
    }

    public void setVreme_polaska(String vreme_polaska) {
        this.vreme_polaska = vreme_polaska;
    }
}
