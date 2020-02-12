package de.clines.documentation.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.clines.documentation.store.ServiceDocumentationStore
import de.clines.documentation.properties.SwaggerProperties
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Service
class DocumentationUpdateService(
        private val swaggerProperties: SwaggerProperties,
        private val documentationStore: ServiceDocumentationStore,
        private val restTemplate: RestTemplate
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedDelayString = "\${clines.swagger.refresh-rate}")
    fun refreshSwaggerConfigurations() {
        swaggerProperties.services.forEach { (name, uri) ->
            val docs = getSwaggerDocumentation(getSwaggerUri(uri))
            docs?.let {
                documentationStore.addServiceDocumentation(
                        service = name,
                        documentation = getJsonString(docs)
                )
            }
        }
    }

    private fun getSwaggerUri(domain: String) = domain + SWAGGER_URL

    private fun getSwaggerDocumentation(uri: String): Any? =
        try {
            restTemplate.getForObject(uri, Any::class.java)
        } catch (rex: RestClientException) {
            log.warn("Could not retrieve documentation from: $uri")
            null
        }

    private fun getJsonString(json: Any) = jacksonObjectMapper().writeValueAsString(json)


    companion object {
        private const val SWAGGER_URL = "/v2/api-docs"
    }
}