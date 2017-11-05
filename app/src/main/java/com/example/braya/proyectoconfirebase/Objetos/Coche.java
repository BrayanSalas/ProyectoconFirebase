package com.example.braya.proyectoconfirebase.Objetos;

/**
 * Created by braya on 12/10/2017.
 */

public class Coche {
    String marca;
    String dueno;
    int puertas;
    int ruedas;

    public Coche() {
    }

    public Coche(String marca, String dueño, int puertas, int ruedas) {
        this.marca = marca;
        this.dueno = dueño;
        this.puertas = puertas;
        this.ruedas = ruedas;
    }

    public String getMarca() {
        return marca;
    }

    public String getDueño() {
        return dueno;
    }

    public int getPuertas() {
        return puertas;
    }

    public int getRuedas() {
        return ruedas;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setDueño(String dueño) {
        this.dueno = dueño;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public void setRuedas(int ruedas) {
        this.ruedas = ruedas;
    }
}
