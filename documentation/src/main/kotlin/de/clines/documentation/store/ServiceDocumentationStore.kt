package de.clines.documentation.store

import org.springframework.stereotype.Component
import springfox.documentation.swagger.web.SwaggerResource
import java.util.concurrent.ConcurrentHashMap

@Component
class ServiceDocumentationStore {

    private val documentations = ConcurrentHashMap<String, String>()

    fun addServiceDocumentation(service: String, documentation: String) {
        documentations[service] = documentation
    }

    fun getServiceDocumentation(service: String) = documentations[service]

    fun getSwaggerResources() =
            documentations.map {
                SwaggerResource().apply {
                    location = "/service/" + it.key
                    name = it.key
                    swaggerVersion = "2.0"
                }
            }
}