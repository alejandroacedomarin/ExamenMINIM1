package edu.upc.eetac.dsa.models;

import net.moznion.random.string.RandomStringGenerator;

public class Partida {
    private String IdPartida;
    private String IdJuego;
    private String IdJugador;
    private int puntos;
    private int nivelActual;

    public Partida(){}

    public Partida(String IdJuego, String IdJugador){
        this.IdPartida = RandomUtils();
        this.IdJuego = IdJuego;
        this.IdJugador = IdJugador;
        this.puntos = 50;
        this.nivelActual = 1;
    }

    private String RandomUtils() {
        RandomStringGenerator generator = new RandomStringGenerator();
        String randomString = generator.generateByRegex("\\w+\\d*[0-9]{0,8}");
        return randomString;
    }

    public String getIdJuego() {
        return this.IdJuego;
    }

    public void setIdJuego(String idJuego) {
        this.IdJuego = idJuego;
    }

    public String getIdJugador() {
        return this.IdJugador;
    }

    public void setIdJugador(String idJugador) {
        this.IdJugador = idJugador;
    }

    public int getPunt() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getIdPartida() {
        return this.IdPartida;
    }

    public void setIdPartida(String idPartida) {
        this.IdPartida = idPartida;
    }

    public int getNivelActual() {
        return this.nivelActual;
    }


    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

}
