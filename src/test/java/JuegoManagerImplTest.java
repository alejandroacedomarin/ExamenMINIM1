import edu.upc.eetac.dsa.JuegoManager;
import edu.upc.eetac.dsa.JuegoManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JuegoManagerImplTest {
    JuegoManager jm;

    @Before
    public void setUp() {
        jm = new JuegoManagerImpl();
        jm.addUsuario("kiko");

        jm.addJuego("salermo","sal",4);

    }

    @After
    public void tearDown() {
        this.jm = null;
    }

    @Test
    public void testAddJuegoUsu(){
        Assert.assertEquals(1,this.jm.numJuegos());
        Assert.assertEquals(1,this.jm.numUsuarios());

        this.jm.addUsuario("MA");
        this.jm.addJuego("po","bdb",6);

        Assert.assertEquals(2,this.jm.numJuegos());
        Assert.assertEquals(2,this.jm.numUsuarios());
    }



}
