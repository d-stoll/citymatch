package de.clines.places.configuration

import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerConfiguration(
        private val buildProperties: BuildProperties
) {

    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("de.clines.places"))
                    .paths(PathSelectors.any()).build()
                    .pathMapping("/")
                    .useDefaultResponseMessages(false)
                    .host("api.clines.de")
                    .apiInfo(apiInfo())

    private fun apiInfo() =
            ApiInfoBuilder()
                    .title("Place Service")
                    .description("Service for retrieving POI around a location.")
                    .contact(Contact("Daniel Stoll", "", "daniel@stoll.cloud"))
                    .version(buildProperties.version)
                    .build()
}