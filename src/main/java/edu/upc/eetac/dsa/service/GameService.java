package edu.upc.eetac.dsa.service;

import edu.upc.eetac.dsa.GameManager;
import edu.upc.eetac.dsa.GameManagerImpl;
import edu.upc.eetac.dsa.models.Game;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {
    private GameManager gm;

    public GameService() {
        this.gm = GameManagerImpl.getInstance();
        this.gm.addGame("LoL","League of Legends",11);
        this.gm.addGame("WoW","World of Warcraft",13);
        this.gm.addGame("AC","Assasins Creed",7);
    }

    @POST
    @ApiOperation(value = "add a new game", notes = "New Game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Game.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Game game) {

        if (game.getIdGame()==null || game.getDescripcion()==null)  return Response.status(500).entity(game).build();
        this.gm.addGame(game.getIdGame(), game.getDescripcion(), game.getNiveles());
        return Response.status(201).entity(game).build();
    }

    @GET
    @ApiOperation(value = "get all Players of a Game", notes = "Ordered by points")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
    })
    @Path("/players")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayersbyPoints(Game game) {
        return Response.status(201).entity(game).build();
    }

    @POST
    @ApiOperation(value = "Add a new User", notes = "New User to the system")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Game.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Game game) {
        return Response.status(201).entity(game).build();
    }
}

