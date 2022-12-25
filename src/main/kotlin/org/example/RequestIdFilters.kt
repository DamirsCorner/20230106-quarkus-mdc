package org.example

import org.jboss.logging.MDC
import org.jboss.resteasy.reactive.server.ServerRequestFilter
import org.jboss.resteasy.reactive.server.ServerResponseFilter
import java.util.*
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.ext.Provider

private const val REQUEST_ID_HEADER = "X-Request-ID"
private const val REQUEST_ID_MDC_KEY = "requestId"

@Provider
class RequestIdFilters : ClientRequestFilter {

    @ServerRequestFilter
    fun getRequestFilter(requestContext: ContainerRequestContext) {
        val requestId = requestContext.getHeaderString(REQUEST_ID_HEADER)
        MDC.put(REQUEST_ID_MDC_KEY, requestId ?: UUID.randomUUID().toString())
    }

    @ServerResponseFilter
    fun getResponseFilter(responseContext: ContainerResponseContext) {
        MDC.remove(REQUEST_ID_MDC_KEY)
    }

    override fun filter(requestContext: ClientRequestContext) {
        val requestId = MDC.get(REQUEST_ID_MDC_KEY)
        if (requestId != null) {
            requestContext.headers[REQUEST_ID_HEADER] = listOf(requestId)
        }
    }
}