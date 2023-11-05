package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Juego;
import edu.upc.eetac.dsa.models.Usuario;
import edu.upc.eetac.dsa.models.Partida;

import java.util.List;

public interface JuegoManager {
    public void addJuego(String IdJuego, String descripcion, int niveles);
    public void addUsuario(String IdUsuario);
    public void addPartida(String IdJuego, String IdUsuario);
    public int numUsuarios();
    public int numJuegos();
    public int getJugando(String idusu);
    public Juego getJuego(String IdJuego);
    public Usuario getUsuario(String IdUsuario);
    public String getNivel(String IdUsuario);
    public Partida getPartidaActual(String IdUsuario);
    public String getPunt(String IdUsuario);

    public Usuario endPartida(String IdUsuario);

}
