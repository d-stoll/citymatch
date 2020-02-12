package de.clines.documentation.configuration

import de.clines.documentation.store.ServiceDocumentationStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestTemplate
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SwaggerResourcesProvider
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration(
        private val documentationStore: ServiceDocumentationStore
) {

    @Bean
    fun restTemplate() = RestTemplate()

    @Primary
    @Bean
    @Lazy
    fun swaggerResourceProvider(): SwaggerResourcesProvider {
        return SwaggerResourcesProvider(documentationStore::getSwaggerResources)
    }

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)

}