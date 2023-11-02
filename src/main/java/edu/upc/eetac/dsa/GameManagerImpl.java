package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Game;
import edu.upc.eetac.dsa.models.User;
import edu.upc.eetac.dsa.models.Partida;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class GameManagerImpl implements GameManager{
    private static GameManager instance;
    List<Game> games;
    Map<String, User> users;


    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public GameManagerImpl(){
        this.games = new ArrayList<>();
        this.users = new HashMap<>();
    }

    @Override
    public void addGame(String IdGame, String descripcion, int niveles) {
        this.games.add(new Game(IdGame,descripcion, niveles));
    }

    @Override
    public void addUser(String IdUser) {
        this.users.put(IdUser,new User(IdUser));
    }


    @Override
    public int numUsers() {
        return users.size();
    }

    @Override
    public int numGames() {
        return games.size();
    }

    @Override
    public void NextLevel(String IdUser, int puntos, String fecha) {
    }

    @Override
    public Partida EmpezarPartida(String IdGame, String IdUser) {
        Game game = getGame(IdGame);
        User user = this.users.get(IdUser);
        User newUser = new User(IdUser);
        this.users.put(IdUser, newUser);

        Partida newPartida = new Partida(IdUser,IdGame);
        this.users.get(IdUser).addPartida(newPartida);
        return newPartida;
    }

    @Override
    public Game getGame(String IdGame) {
        for (Game j : this.games){
            if(j.getIdGame().equals(IdGame)){
                logger.info(j);
                return j;
            }
        }
        return null;
    }

    @Override
    public String getNivelActual(String IdUser) {
        Partida p = getPartidaActual(IdUser);
        return String.valueOf(p.getNivelActual());
    }

    @Override
    public Partida getPartidaActual(String IdUser) {
        User user = getUser(IdUser);
        List<Partida> partidasjugadas = (List<Partida>) user.getPartidasJugadas().values().stream().collect(toList());
        logger.info("Partida actual: "+partidasjugadas.get(partidasjugadas.size()-1));
        return partidasjugadas.get(partidasjugadas.size()-1);
    }

    @Override
    public User getUser(String IdUser) {
        return this.users.get(IdUser);
    }

    @Override
    public Partida getPartida(String IdGame, String IdUser) {
        List<Partida> list = getPartidas(IdGame, IdUser);
        if (list.size()!=0) {
            return list.get(list.size()-1);
        }
        return null;
    }

    @Override
    public List<Partida> getPartidas(String IdGame, String IdUser) {
        Game game = getGame(IdGame);
        User user = this.users.get(IdUser);
        HashMap<String, Partida> partidas = user.getPartidasJugadas();
        List<Partida> partidasplayed = new ArrayList<>();
        for(Map.Entry<String, Partida> entry : partidas.entrySet()){
            if(entry.getValue().getIdJuego().equals(IdGame)) {
                partidasplayed.add(entry.getValue());
            }
        }
        return partidasplayed;
    }

    @Override
    public int sizeGames() {
        return games.size();
    }

    @Override
    public String getPuntuacionActual(String IdUser) {
        Partida p = getPartidaActual(IdUser);
        return String.valueOf(p.getPuntos());
    }

    @Override
    public User endPartida(String IdUser) {
        Partida p = getPartidaActual(IdUser);
        this.users.get(IdUser).setJugando(0);
        return this.users.get(IdUser);
    }

    @Override
    public List<User> sortPlayers(Game game) {
        Game jj = getGame(game.getIdGame());
        List<Partida> partidaJuego = new ArrayList<>();
        for(Map.Entry<String , User> entry : this.users.entrySet()){
            try {
                Partida p = getPartida(game.getIdGame(), entry.getKey());
                if (p!=null)
                    partidaJuego.add(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(partidaJuego.size()!=0){
            partidaJuego.sort((Partida p1, Partida p2) -> Integer.compare(p2.getPuntos(),p1.getPuntos()));
            List<User> result = new ArrayList<>();
            for(Partida partida : partidaJuego){
                result.add(this.users.get(partida.getIdJugador()));
            }
            return result;
        }
        return null;
    }

    @Override
    public List<Partida> getPartidasPlayer(String IdUser) {
        List<Partida> list = getUser(IdUser).getPartidasJugadas().values().stream().collect(toList());
        return list;
    }
}
