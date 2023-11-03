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

    public Juego getJuego(String IdJuego);
    public String getNivel(String IdUsuario);
    public String getPunt(String IdUsuario);
    public Partida getPartidaActual(String IdUsuario);
    public Usuario getUsuario(String IdUsuario);
    public Partida getPartida (String IdJuego, String IdUsuario);
    public List<Partida> getPartidas (String IdJuego, String IdUsuario);
    public Partida EmpezarPartida(String IdGame, String IdUser);
    public  int sizeJuegos();

    public void PasarNivel(String IdUser,int puntos, String fecha);
    public String getPuntuacionActual(String IdUsuario);
    public Usuario endPartida(String IdUsuario);
    public List<Usuario> sortJugadores(Juego juego);
    public List<Partida> getPartidasJugador(String IdUsuario);

}
