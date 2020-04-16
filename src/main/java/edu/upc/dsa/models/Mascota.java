package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;
public class Mascota {
    public String id;
    public String nombre;
    public String tipo;

    public Mascota(){
        this.id=RandomUtils.getId();
    }

    public Mascota(String nombre, String tipo)
    {
        this();
        this.nombre=nombre;
        this.tipo=tipo;
    }
    public String GetId()
    {
       return this.id;
    }
    public String GetNombre()
    {
        return this.nombre;
    }
    public String GetTipo()
    {
        return this.tipo;
    }
}
