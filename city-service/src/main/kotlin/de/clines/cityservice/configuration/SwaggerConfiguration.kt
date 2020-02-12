package de.clines.cityservice.configuration

import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfiguration(
        private val buildProperties: BuildProperties
) {

    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("de.clines.cityservice"))
                    .paths(PathSelectors.any()).build()
                    .pathMapping("/")
                    .useDefaultResponseMessages(false)
                    .host("https://api.clines.de/cities")
                    .apiInfo(apiInfo())

    private fun apiInfo() =
            ApiInfoBuilder()
                    .title("City Service")
                    .description("Service for querying cities around the world.")
                    .contact(Contact("Daniel Stoll", "", "daniel@stoll.cloud"))
                    .version(buildProperties.version)
                    .build()
}