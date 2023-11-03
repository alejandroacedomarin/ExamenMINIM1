package edu.upc.eetac.dsa.models;

import java.util.HashMap;

public class Usuario {
    private String IdUsuario;
    int Jugando; //0=no, 1=si
    HashMap<String, Partida> partidasJugadas;

    public Usuario(){}

    public Usuario(String Id){
        this.IdUsuario = Id;
        this.Jugando = 0;
        this.setPartidasJugadas();
    }

    public String getIdUsuario() {
        return this.IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public int getJugando() {
        return this.Jugando;
    }

    public void setJugando(int jugando) {
        this.Jugando = jugando;
    }

    public HashMap<String, Partida> getPartidasJugadas() {
        return partidasJugadas;
    }

    public void addPartida(Partida partida){
        this.partidasJugadas.put(partida.getIdPartida(), partida);
        this.Jugando = 1;
    }

    public void setPartidasJugadas() {
        this.partidasJugadas = new HashMap<>();
    }
}
