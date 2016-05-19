package br.eti.vagnergross.todo.oauth.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vagner on 05/05/16.
 */
@Path("/oauth")
public class ToDoOAuthRestAPI {

    @GET
    @Produces("application/json")
    public Response authentication(){

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        SecureRandom secureRandom = new SecureRandom();
        String random = new BigInteger(500, secureRandom).toString(32);


        String keySource = dateFormat.format(Calendar.getInstance().getTime()) + random;
        byte [] tokenByte = Base64.getEncoder().encode(keySource.getBytes());
        String token = new String(tokenByte);

        return Response.status(200).entity(token).build();
    }

}
