package com.exposit.carsharing.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.security.InvalidParameterException;

@Provider
public class MyExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof EntityNotFoundException) {
            return Response.status(404).entity(new ExceptionResponse(404, exception.getMessage())).build();
        }
        if (exception instanceof EntityAlreadyExistException) {
            return Response.status(409).entity(new ExceptionResponse(409, exception.getMessage())).build();
        }
        if (exception instanceof PrivilegeException) {
            return Response.status(409).entity(new ExceptionResponse(409, exception.getMessage())).build();
        }
        if (exception instanceof UnauthorizedException) {
            return Response.status(401).entity(new ExceptionResponse(401, exception.getMessage())).build();
        }
        if (exception instanceof ConfirmProfileException) {
            return Response.status(409).entity(new ExceptionResponse(409, exception.getMessage())).build();
        }
        if (exception instanceof DealException) {
            return Response.status(409).entity(new ExceptionResponse(409, exception.getMessage())).build();
        }
        if (exception instanceof AdException) {
            return Response.status(409).entity(new ExceptionResponse(409, exception.getMessage())).build();
        }
        if (exception instanceof InvalidParameterException) {
            return Response.status(400).entity(new ExceptionResponse(400, exception.getMessage())).build();
        }
        if (exception instanceof PasswordException) {
            return Response.status(400).entity(new ExceptionResponse(400, exception.getMessage())).build();
        }
        return Response.status(500).entity(new ExceptionResponse(500, exception.getMessage())).build();
    }
}
