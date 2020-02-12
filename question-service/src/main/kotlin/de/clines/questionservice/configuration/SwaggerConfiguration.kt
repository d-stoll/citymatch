package de.clines.questionservice.configuration

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
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@EnableSwagger2WebFlux
@Configuration
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerConfiguration(
        private val buildProperties: BuildProperties
) {

    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("de.clines.questionservice"))
                    .paths(PathSelectors.any()).build()
                    .pathMapping("/")
                    .useDefaultResponseMessages(false)
                    .host("https://api.clines.de/questions")
                    .apiInfo(apiInfo())

    private fun apiInfo() =
            ApiInfoBuilder()
                    .title("Question Service")
                    .description("Service for managing and retrieving questions for the tour creation phase.")
                    .contact(Contact("Daniel Stoll", "", "daniel@stoll.cloud"))
                    .version(buildProperties.version)
                    .build()
}