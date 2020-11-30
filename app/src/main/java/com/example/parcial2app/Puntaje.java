package com.example.parcial2app;

public class Puntaje {

    String id, puntaje;

    public Puntaje(){

    }

    public Puntaje(String id, String puntaje) {
        this.id = id;
        this.puntaje = puntaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }
}
