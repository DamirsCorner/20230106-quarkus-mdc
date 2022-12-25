package org.example.randomUser

import io.quarkus.rest.client.reactive.ClientQueryParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET

@RegisterRestClient(baseUri = "https://randomuser.me/api")
interface Api {

    @GET
    @ClientQueryParam(name = "inc", value = ["name"])
    fun getUsers(): ApiResponse
}