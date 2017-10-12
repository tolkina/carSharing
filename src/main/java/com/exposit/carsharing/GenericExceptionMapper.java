package com.exposit.carsharing;

import com.exposit.carsharing.exception.ExceptionInfo;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

/**
 * Created by Sergei on 10/12/2017.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(409).entity(new ExceptionInfo(409, exception.getMessage())).build();
    }
}
