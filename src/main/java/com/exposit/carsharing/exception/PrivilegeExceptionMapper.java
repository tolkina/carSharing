package com.exposit.carsharing.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PrivilegeExceptionMapper implements ExceptionMapper<PrivilegeException> {

    @Override
    public Response toResponse(PrivilegeException exception) {
        return Response.status(409).entity(new ExceptionInfo(409, exception.getMessage())).build();
    }
}
