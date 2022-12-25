package org.example

import io.quarkus.logging.Log
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.example.randomUser.Api
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource(
    @RestClient val api: Api
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        Log.info("Hello")
        return "Hello from RESTEasy Reactive"
    }

    @GET
    @Path("/random")
    @Produces(MediaType.TEXT_PLAIN)
    fun helloRandom(): String {
        Log.info("Hello random")
        val user = api.getUsers().results.firstOrNull()
        return "Hello, ${user?.name?.first}"
    }
}