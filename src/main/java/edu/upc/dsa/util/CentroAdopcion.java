package edu.upc.dsa.util;
import edu.upc.dsa.models.Mascota;
import java.util.List;

public interface CentroAdopcion {
    public Mascota AnadirMascota(String nombre, String tipo);
    public Mascota AnadirMascota(Mascota m);
    public Mascota ConsultarMascota(String id);
    public Mascota ConsultarMascotaPorNombre(String nombre);
    public Mascota AdoptarMascota(String id);
    public List<Mascota> findAll();
    public void LiberarRecursos();
    public int size();

}
