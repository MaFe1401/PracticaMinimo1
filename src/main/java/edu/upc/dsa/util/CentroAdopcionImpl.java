package edu.upc.dsa.util;
import edu.upc.dsa.models.Mascota;
import org.apache.log4j.Logger;
import java.util.LinkedList;


import java.util.List;

public class CentroAdopcionImpl implements CentroAdopcion {
    //instancia
    private static CentroAdopcionImpl instance;
    protected List<Mascota> lista;
    final static Logger logger =Logger.getLogger(CentroAdopcionImpl.class);
    private CentroAdopcionImpl(){
        this.lista= new LinkedList<Mascota>();
    }
    public static CentroAdopcionImpl getInstance(){
        if(instance==null)
            instance=new CentroAdopcionImpl();
        return instance;
    }


    @Override
    public Mascota AnadirMascota(String nombre, String tipo) {
        return this.AnadirMascota(new Mascota(nombre,tipo));

    }

    @Override
    public Mascota AnadirMascota(Mascota m) {
        logger.info("Nueva mascota "+m);
        lista.add(m);
        return m;
    }

    @Override
    public Mascota ConsultarMascota(String id) {
        for(Mascota t: this.lista)
        {
            if (t.GetId().equals(id)){
                logger.info("ConsultarMascota("+id+"): "+t);
                return t;
            }
        }
        logger.warn("not found "+id);
        return null;
    }

    @Override
    public Mascota ConsultarMascotaPorNombre(String nombre) {
        for (Mascota m: this.lista)
        {
            if (m.GetNombre().equals(nombre)){
                logger.info("ConsultarMascotaPorNombre("+nombre+") :"+m);
                return m;
            }
        }
        logger.warn("No encontrada "+nombre);
        return null;
    }

    @Override
    public Mascota AdoptarMascota(String id) {
       Mascota m = this.ConsultarMascota(id);
       if (m==null){
           logger.warn("no ha sido encontrada la mascota "+id);
           return null;
       }
       else {
           logger.info("Mascota " + id + " adoptada");
           this.lista.remove(m);
           return m;
       }

    }

    @Override
    public List<Mascota> findAll() {
        return this.lista;
    }

    @Override
    public void LiberarRecursos() {
        this.lista.clear();
    }


    @Override
    public int size() {
        int ret =this.lista.size();
        logger.info("size "+ret);
        return ret;
    }
}
