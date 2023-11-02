package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Game;
import edu.upc.eetac.dsa.models.User;
import edu.upc.eetac.dsa.models.Partida;

import java.util.List;

public interface GameManager {
    public void addGame(String IdGame, String descripcion, int niveles);
    public void addUser(String IdUser);
    public int numUsers();
    public int numGames();
    public void NextLevel(String IdUser,int puntos, String fecha);

    public Partida EmpezarPartida(String IdGame, String IdUser);
    public Game getGame(String IdGame);
    public String getNivelActual(String IdUser);
    public Partida getPartidaActual(String IdUser);
    public User getUser(String IdUser);
    public Partida getPartida (String IdGame, String IdUser);
    public List<Partida> getPartidas (String IdGame, String IdUser);
    public int sizeGames();
    public String getPuntuacionActual(String IdUser);
    public User endPartida(String IdUser);
    public List<User> sortPlayers(Game game);
    public List<Partida> getPartidasPlayer(String IdUser);

}
