package edu.upc.dsa.services;
import edu.upc.dsa.models.Mascota;
import edu.upc.dsa.util.CentroAdopcion;
import edu.upc.dsa.util.CentroAdopcionImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/mascotas", description = "Endpoint al servicio de adopcion de mascotas")
@Path("/mascotas")
public class ServicioAdopcion {
    private CentroAdopcionImpl centroAd;

    public ServicioAdopcion() {
        this.centroAd = CentroAdopcionImpl.getInstance();
        if (this.centroAd.size()==0)
            this.centroAd.AnadirMascota("Pancho", "Perro");
    }

    @GET
    @ApiOperation(value = "Consultar Mascotas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mascota.class, responseContainer = "List"),
    })
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetMascotas(){
        List<Mascota> mascotas = this.centroAd.findAll();
        GenericEntity<List<Mascota>> entity = new GenericEntity<List<Mascota>>(mascotas) {
        };
        return Response.status(201).entity(entity).build();
    }

    @DELETE
    @ApiOperation(value = "Adoptar Mascota", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Adoptada exitosamente"),
            @ApiResponse(code = 404, message = "Mascota not found")
    })
    @Path("/{id}")
    public Response AdoptMascota(@PathParam("id") String id){
        Mascota m = this.centroAd.ConsultarMascota(id);
        if (m==null)
        return Response.status(404).build();
        else this.centroAd.AdoptarMascota(id);
        return Response.status(201).build();
    }
    @POST
    @ApiOperation(value = "Añadir mascota al Centro de adopción", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Mascota.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/addMascota/{tipo}/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevaMascota(@PathParam("tipo")String tipo, @PathParam("nombre") String nombre){
        if (tipo.isEmpty()||nombre.isEmpty())
            return Response.status(500).entity(new Mascota()).build();
        this.centroAd.AnadirMascota(new Mascota(nombre,tipo));
        return Response.status(201).entity(centroAd.ConsultarMascotaPorNombre(nombre)).build();
    }
    @GET
    @ApiOperation(value = "Buscar mascota por nombre", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mascota.class),
            @ApiResponse(code = 404, message = "Mascota not found")
    })
    @Path("/nombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response BuscarPorNombre(@PathParam("nombre") String nombre){
        Mascota m = this.centroAd.ConsultarMascotaPorNombre(nombre);
        if (m==null)
            return Response.status(404).entity(m).build();
        else return Response.status(201).entity(m).build();
    }

}
