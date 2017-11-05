package com.example.braya.proyectoconfirebase.Objetos;

/**
 * Created by braya on 16/10/2017.
 */

public class Bebe {
    String nombre;
    int años;
    long telefono;
    String cuidados;
    String ubicacion;
    int horas;
    int total;

    public Bebe() {
    }

    public Bebe(String nombre, int años, long telefono, String cuidados, String ubicacion, int horas, int total) {
        this.nombre = nombre;
        this.años = años;
        this.telefono = telefono;
        this.cuidados = cuidados;
        this.ubicacion = ubicacion;
        this.horas = horas;
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAños() {
        return años;
    }

    public void setAños(int años) {
        this.años = años;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCuidados() {
        return cuidados;
    }

    public void setCuidados(String cuidados) {
        this.cuidados = cuidados;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getHoras() { return horas;}

    public void setHoras(int horas) {this.horas = horas;}

    public int getTotal() {return total;}

    public void setTotal(int total) {this.total = total;}
}
