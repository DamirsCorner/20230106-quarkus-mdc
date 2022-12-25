package org.example

import org.jboss.logging.MDC
import org.jboss.resteasy.reactive.server.ServerRequestFilter
import org.jboss.resteasy.reactive.server.ServerResponseFilter
import java.util.*
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext

private const val REQUEST_ID_HEADER = "X-Request-ID"
private const val REQUEST_ID_MDC_KEY = "requestId"

class RequestIdFilters {

    @ServerRequestFilter
    fun getRequestFilter(requestContext: ContainerRequestContext) {
        val requestId = requestContext.getHeaderString(REQUEST_ID_HEADER)
        MDC.put(REQUEST_ID_MDC_KEY, requestId ?: UUID.randomUUID().toString())
    }

    @ServerResponseFilter
    fun getResponseFilter(responseContext: ContainerResponseContext) {
        MDC.remove(REQUEST_ID_MDC_KEY)
    }
}