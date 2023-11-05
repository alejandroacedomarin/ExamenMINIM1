package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Juego;
import edu.upc.eetac.dsa.models.Usuario;
import edu.upc.eetac.dsa.models.Partida;
import org.apache.log4j.Logger;
import java.util.*;
import static java.util.stream.Collectors.toList;

public class JuegoManagerImpl implements JuegoManager {
    private static JuegoManager instance;
    List<Juego> juegos;
    List<Usuario> usuarios;

    List<Partida> partidas;


    final static Logger logger = Logger.getLogger(JuegoManagerImpl.class);

    public static JuegoManager getInstance() {
        if (instance==null) instance = new JuegoManagerImpl();
        return instance;
    }

    public JuegoManagerImpl(){
        this.juegos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    @Override
    public void addJuego(String IdJuego, String descripcion, int niveles) {
        this.juegos.add(new Juego(IdJuego,descripcion, niveles));
    }

    @Override
    public void addUsuario(String IdUsuario) {
        this.usuarios.add(new Usuario(IdUsuario));
    }

    @Override
    public void addPartida(String IdJuego,String IdUsuario) {
        this.partidas.add(new Partida(IdJuego,IdUsuario));
    }


    @Override
    public int numUsuarios() {
        return usuarios.size();
    }

    @Override
    public int numJuegos() {
        return juegos.size();
    }
    @Override
    public int getJugando(String idusu) {
        for (Usuario u: this.usuarios) {
            if (u.getIdUsuario().equals(idusu) && u.getJugando() == 0) {

                return 0;
            }

        }
        return 1;
    }
    @Override
    public Juego getJuego(String IdJuego) {
        for (Juego j : this.juegos){
            if(j.getIdJuego().equals(IdJuego)){
                logger.info(j);
                return j;
            }
        }
        return null;
    }
    @Override
    public Usuario getUsuario(String IdUsuario) {
        for (Usuario u: this.usuarios) {
            if (u.getIdUsuario().equals(IdUsuario) ) {

                return u;
            }

        }
        return null;
    }
    @Override
    public String getNivel(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);
        if(p==null)return null;
        else return String.valueOf(p.getNivelActual());
    }
    @Override
    public Partida getPartidaActual(String IdUsuario) {
        Usuario user = getUsuario(IdUsuario);
        List<Partida> partidasjugadas = (List<Partida>) user.getPartidasJugadas().values().stream().collect(toList());
        logger.info("Partida actual: "+partidasjugadas.get(partidasjugadas.size()-1));
        return partidasjugadas.get(partidasjugadas.size()-1);
    }
    @Override
    public String getPunt(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);
        if(p==null)return null;
        else return String.valueOf(p.getPunt());
    }

    @Override
    public Usuario endPartida(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);

        if (p==null) {

            return null;
        }
        else {


            for (Usuario u: this.usuarios) {
                if (u.getIdUsuario().equals(IdUsuario) ) {
                    this.partidas.remove(p);
                    u.setJugando(0);
                    return u;
                }

            }
            return null;
        }

    }

}

