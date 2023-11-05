package edu.upc.eetac.dsa.service;

import edu.upc.eetac.dsa.JuegoManager;
import edu.upc.eetac.dsa.JuegoManagerImpl;
import edu.upc.eetac.dsa.models.Juego;
import edu.upc.eetac.dsa.models.Partida;
import edu.upc.eetac.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to JuegoService")
@Path("/game")
public class JuegoService {
    private JuegoManager jm;

    public JuegoService() {
        this.jm = JuegoManagerImpl.getInstance();
        this.jm.addJuego("la cenicienta", "deluxe", 7);
        this.jm.addJuego("el corredor", "maraton", 42);
        this.jm.addJuego("la escoba", "de madera", 8);
        this.jm.addJuego("el pomelo", "naranja", 18);
        this.jm.addUsuario("lola");
        this.jm.addUsuario("juan");
        this.jm.addUsuario("mari");
    }

    @POST
    @ApiOperation(value = "juego nuevo", notes = "New Game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Juego.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addJuego(Juego juego) {


        if (juego.getIdJuego()==null || juego.getDescripcion()==null)  return Response.status(500).entity(juego).build();
        this.jm.addJuego(juego.getIdJuego(), juego.getDescripcion(), juego.getNiveles());
        return Response.status(201).entity(juego).build();
    }

    @POST
    @ApiOperation(value = "nueva partida", notes = "Partida Nueva")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Partida.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/{idj}/{idu}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPartida(@PathParam("idj") String idj,@PathParam("idu") String idu) {
        Usuario u = this.jm.getUsuario(idu);
        Juego j = this.jm.getJuego(idj);
        int jugando = this.jm.getJugando(idu);
        if (u==null || j==null || jugando == 1)  return Response.status(500).build();
        this.jm.addPartida(idj, idu);
        u.setJugando(1);
        return Response.status(201).build();

    }

    @GET
    @ApiOperation(value = "nivel actual", notes = "Nivel de jugador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/jugador/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNivel(@PathParam("id") String id) {
        Usuario u = this.jm.getUsuario(id);
        int jugando = this.jm.getJugando(id);
        if (u==null || jugando == 0)  return Response.status(500).build();
        return Response.status(201).entity(this.jm.getNivel(id)).build();
    }

    @GET
    @ApiOperation(value = "puntuacion actual", notes = "Puntuacion de jugador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/partida/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPunt(@PathParam("id") String id) {
        Usuario u = this.jm.getUsuario(id);
        int jugando = this.jm.getJugando(id);
        if (u==null || jugando == 0)  return Response.status(500).build();
        return Response.status(201).entity(this.jm.getPunt(id)).build();
    }


    @DELETE
    @ApiOperation(value = "acabar partida", notes = "Finalizar partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No existe la partida")
    })
    @Path("/{id}")
    public Response endPartida(@PathParam("id") String id) {
        Usuario u = this.jm.endPartida(id);
        if (u == null) return Response.status(404).build();
        else return Response.status(201).build();
    }











    }








































