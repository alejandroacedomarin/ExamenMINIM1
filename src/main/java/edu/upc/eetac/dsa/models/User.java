package edu.upc.eetac.dsa.models;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class User {
    private String IdUser;
    int Jugando; //0 -> no, 1 -> si
    HashMap<String, Partida> partidasJugadas; //string = idPartida; Partida = partida.

    public User(){}

    public User(String Id){
        this.IdUser = Id;
        this.Jugando = 0;
        this.setPartidasJugadas();
    }

    public String getIdUser() {
        return this.IdUser;
    }

    public void setIdUser(String idUser) {
        this.IdUser = idUser;
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
