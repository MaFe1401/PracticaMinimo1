package edu.upc.dsa;
import edu.upc.dsa.models.*;
import edu.upc.dsa.util.CentroAdopcionImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.*;

public class CentroAdopcionTest {
    public CentroAdopcionImpl centro = null;
    List<Mascota> lista;
    @Before
    public void SetUp(){
        centro=CentroAdopcionImpl.getInstance();
        lista=new LinkedList<Mascota>();
    }
    @Test
    public void AddMascota(){
        Assert.assertEquals(0,centro.size());
        centro.AnadirMascota("Nemo", "pez");
        centro.AnadirMascota("Pancho","perro");
        Assert.assertEquals(2,centro.size());
    }
    @After
    public void TearDown(){
        centro.LiberarRecursos();
    }

}
