package edu.upc.eetac.dsa.models;

public class Game {
    private String IdGame;
    private String descripcion;
    private int niveles;

    public Game(){}

    public Game(String idGame, String descripcion, int niveles) {
        this.IdGame = idGame;
        this.descripcion = descripcion;
        this.niveles = niveles;
    }

    public String getIdGame() {
        return this.IdGame;
    }

    public void setIdGame(String idGame) {
        this.IdGame = idGame;
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
