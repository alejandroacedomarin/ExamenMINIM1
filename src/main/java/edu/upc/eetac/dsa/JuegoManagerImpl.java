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
    Map<String, Usuario> usuarios;

    List<Partida> partidas;


    final static Logger logger = Logger.getLogger(JuegoManagerImpl.class);

    public static JuegoManager getInstance() {
        if (instance==null) instance = new JuegoManagerImpl();
        return instance;
    }

    public JuegoManagerImpl(){
        this.juegos = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.partidas = new ArrayList<>();
    }

    @Override
    public void addJuego(String IdJuego, String descripcion, int niveles) {
        this.juegos.add(new Juego(IdJuego,descripcion, niveles));
    }

    @Override
    public void addUsuario(String IdUsuario) {
        this.usuarios.put(IdUsuario,new Usuario(IdUsuario));
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
    public void PasarNivel(String IdUsuario, int puntos, String fecha) {
    }

    @Override
    public Partida EmpezarPartida(String IdJuego, String IdUsuario) {
        Juego juego = getJuego(IdJuego);
        Usuario usuario = this.usuarios.get(IdUsuario);
        Usuario newUsuario = new Usuario(IdUsuario);
        this.usuarios.put(IdUsuario, newUsuario);

        Partida newPartida = new Partida(IdUsuario,IdJuego);
        this.usuarios.get(IdUsuario).addPartida(newPartida);
        return newPartida;
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
    public String getNivel(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);
        if(p==null)return "Error";
        else return String.valueOf(p.getNivelActual());
    }
    @Override
    public String getPunt(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);
        if(p==null)return "Error";
        else return String.valueOf(p.getPunt());
    }
    @Override
    public Partida getPartidaActual(String IdUsuario) {
        Usuario user = getUsuario(IdUsuario);
        List<Partida> partidasjugadas = (List<Partida>) user.getPartidasJugadas().values().stream().collect(toList());
        logger.info("Partida actual: "+partidasjugadas.get(partidasjugadas.size()-1));
        return partidasjugadas.get(partidasjugadas.size()-1);
    }

    @Override
    public Usuario getUsuario(String IdUsuario) {
        return this.usuarios.get(IdUsuario);
    }

    @Override
    public Partida getPartida(String IdJuego, String IdUsuario) {
        List<Partida> listp = getPartidas(IdJuego, IdUsuario);
        if (listp.size()!=0) {
            return listp.get(listp.size()-1);
        }
        return null;
    }

    @Override
    public List<Partida> getPartidas(String IdJuego, String IdUsuario) {
        Juego juego = getJuego(IdJuego);
        Usuario usuario = this.usuarios.get(IdUsuario);
        HashMap<String, Partida> partidas = usuario.getPartidasJugadas();
        List<Partida> partidasjugadas = new ArrayList<>();
        for(Map.Entry<String, Partida> entry : partidas.entrySet()){
            if(entry.getValue().getIdJuego().equals(IdJuego)) {
                partidasjugadas.add(entry.getValue());
            }
        }
        return partidasjugadas;
    }

    @Override
    public int sizeJuegos() {
        return juegos.size();
    }

    @Override
    public String getPuntuacionActual(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);
        return String.valueOf(p.getPunt());
    }

    @Override
    public Usuario endPartida(String IdUsuario) {
        Partida p = getPartidaActual(IdUsuario);
        this.usuarios.get(IdUsuario).setJugando(0);
        return this.usuarios.get(IdUsuario);
    }

    @Override
    public List<Usuario> sortJugadores(Juego juego) {
        Juego jj = getJuego(juego.getIdJuego());
        List<Partida> partidaJuego = new ArrayList<>();
        for(Map.Entry<String , Usuario> entry : this.usuarios.entrySet()){
            try {
                Partida p = getPartida(juego.getIdJuego(),entry.getKey());
                if (p!=null)
                    partidaJuego.add(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(partidaJuego.size()!=0){
            partidaJuego.sort((Partida p1, Partida p2)-> Integer.compare(p2.getPunt(),p1.getPunt()));
            List<Usuario> result = new ArrayList<>();
            for(Partida partida : partidaJuego){
                result.add(this.usuarios.get(partida.getIdJugador()));
            }
            return result;
        }
        return null;
    }

    @Override
    public List<Partida> getPartidasJugador(String IdUsuario) {
        List<Partida> list = getUsuario(IdUsuario).getPartidasJugadas().values().stream().collect(toList());
        return list;
    }
}
