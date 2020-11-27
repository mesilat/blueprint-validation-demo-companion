package com.mesilat.cube;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.node.ObjectNode;

public abstract class BaseResource {
    private final BaseDataService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return Response.ok(service.get(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response list() {
        return Response.ok(service.list()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/")
    public Response post(ObjectNode data) {
        service.save(data);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/")
    public Response put(ObjectNode data) {
        service.save(data);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @Inject
    public BaseResource(BaseDataService dataService) {
        this.service = dataService;
    }
}