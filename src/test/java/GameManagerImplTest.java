import edu.upc.eetac.dsa.GameManager;
import edu.upc.eetac.dsa.GameManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerImplTest {
    GameManager gm;

    @Before
    public void setUp() {
        gm = new GameManagerImpl();
        gm.addUser("Marcel");
        gm.addUser("Feynman");

        gm.addGame("LoL","League of Legends",11);
        gm.addGame("WoW","World of Warcraft",13);
        gm.addGame("AC","Assasins Creed",7);
    }

    @After
    public void tearDown() {
        this.gm = null;
    }

    @Test
    public void testAddGameandAddUser(){
        Assert.assertEquals(3,this.gm.numGames());
        Assert.assertEquals(2,this.gm.numUsers());

        this.gm.addUser("Green");
        this.gm.addUser("Wilczek");
        this.gm.addGame("O2","Overwatch 2",6);

        Assert.assertEquals(4,this.gm.numGames());
        Assert.assertEquals(4,this.gm.numUsers());
    }

    @Test
    public void testAddJugadoraPartida(){

    }

    @Test
    public void testPuntosJugador(){

    }

}
