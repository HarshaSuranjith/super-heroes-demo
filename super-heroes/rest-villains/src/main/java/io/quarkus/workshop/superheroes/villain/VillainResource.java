package io.quarkus.workshop.superheroes.villain;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/api/villains")
public class VillainResource {

    Logger logger;
    VillainService service;

    public VillainResource(Logger logger, VillainService service) {
        this.service = service;
        this.logger = logger;
    }

    @GET
    @Path("/random")
    public Response getRandomVillain() {
        Villain villain = service.findRandomVillain();
        logger.debug("Found random villain " + villain);
        return Response.ok(villain).build();
    }

    @GET
    public Response getAllVillains() {
        List<Villain> villains = service.findAllVillains();
        logger.debug("Total number of villains " + villains);
        return Response.ok(villains).build();
    }

    @GET
    @Path("/{id}")
    public Response getVillain(@RestPath Long id) {
        Villain villain = service.findVillainById(id);
        if (villain != null) {
            logger.debug("Found villain " + villain);
            return Response.ok(villain).build();
        } else {
            logger.debug("No villain found with id " + id);
            return Response.noContent().build();
        }
    }

    @POST
    public Response createVillain(@Valid Villain villain, @Context UriInfo uriInfo) {
        villain = service.persistVillain(villain);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(villain.id));
        logger.debug("New villain created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @PUT
    public Response updateVillain(@Valid Villain villain) {
        villain = service.updateVillain(villain);
        logger.debug("Villain updated with new valued " + villain);
        return Response.ok(villain).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVillain(@RestPath Long id) {
        service.deleteVillain(id);
        logger.debug("Villain deleted with " + id);
        return Response.noContent().build();
    }

    @GET
    @Path("/hello")
    @Produces({MediaType.TEXT_PLAIN})
    public String hello() {
        return "Hello Villain Resource";
    }
}
