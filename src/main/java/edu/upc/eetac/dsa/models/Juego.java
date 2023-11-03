package edu.upc.eetac.dsa.models;

public class Juego {
    private String IdJuego;
    private String descripcion;
    private int niveles;

    public Juego(){}

    public Juego(String idJuego, String descripcion, int niveles) {
        this.IdJuego = idJuego;
        this.descripcion = descripcion;
        this.niveles = niveles;
    }

    public String getIdJuego() {
        return this.IdJuego;
    }

    public void setIdJuego(String idJuego) {
        this.IdJuego = idJuego;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNiveles() {
        return this.niveles;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }
}
