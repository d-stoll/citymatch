package de.clines.documentation.controller

import de.clines.documentation.store.ServiceDocumentationStore
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentationController(
        private val documentationStore: ServiceDocumentationStore
) {

    @GetMapping("/service/{name}")
    fun getServiceDocumentation(@PathVariable("name") name: String) =
            documentationStore.getServiceDocumentation(name)

}