package com.exposit.carsharing.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityAlreadyExistExceptionMapper implements ExceptionMapper<EntityAlreadyExistException> {

    @Override
    public Response toResponse(EntityAlreadyExistException exception) {
        return Response.status(409).entity(new ExceptionInfo(409, exception.getMessage())).build();
    }
}
