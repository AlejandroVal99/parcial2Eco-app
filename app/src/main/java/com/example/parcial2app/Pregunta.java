package com.example.parcial2app;

public class Pregunta {
    String pregunta,id;

    public  Pregunta(){

    }

    public Pregunta(String pregunta, String id) {
        this.pregunta = pregunta;
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
