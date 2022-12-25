package org.example

import io.quarkus.logging.Log
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        Log.info("Hello")
        return "Hello from RESTEasy Reactive"
    }
}