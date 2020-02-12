# Backend services for the clines app


[![pipeline status](https://gitlab.com/c-lines/backend/badges/master/pipeline.svg)](https://gitlab.com/c-lines/backend/commits/master)
[![coverage report](https://gitlab.com/c-lines/backend/badges/master/coverage.svg)](https://gitlab.com/c-lines/backend/commits/master)

### Reference Documentation
Currently all services are based Spring Boot and the Spring Cloud ecosystem. You will find an overview of these on

* [Official Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* [Official Spring Cloud Project Overview](https://spring.io/projects/spring-cloud)
* [Spring Boot Tutorials by Baeldung](https://www.baeldung.com/spring-boot)

Instead of Java, we are using Kotlin as JVM Language. Kotlin is concise and safe compared to Java as 
well as 100% interoperable with Java.

The language is very easy and quick to learn if you already have some knowledge of Java and/or Scala. You find 
tutorials and learning tools on

* [Official Kotlin Documentation](https://kotlinlang.org/docs/reference/)
* [Web-IDE for playing around with Kotlin.](https://play.kotlinlang.org/)
* [Spring Boot Tutorial with Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)

### Active Services
Here is a list of all active services:

| Name          | Description   | Version       | Maintainer    | Documentation |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| [documentation](/documentation)  | Service for centralizing Swagger API Documentations | 0.2.0-SNAPSHOT | daniel@stoll.cloud | [docs](https://docs.clines.de/swagger-ui.html)
| [gateway](/gateway)  | An API-Gateway based on Spring Webflux  | 0.2.0-SNAPSHOT | daniel@stoll.cloud | -
| [tour-service](/tour-service)  | Service for creating tours. | 0.2.0 | daniel@stoll.cloud | [docs](https://docs.clines.de/swagger-ui.html#/tour-service)
| [question-service](/question-service)  | Service for managing and retrieving questions for the tour creation phase. | 0.2.0-SNAPSHOT | daniel@stoll.cloud | [docs](https://docs.clines.de/swagger-ui.html#/question-service)
| [place-service](/place-service)  | Service for retrieving POI around a location. | 0.2.0 | daniel@stoll.cloud | [docs](https://docs.clines.de/swagger-ui.html#/place-service)
| [recommendation-service](/recommendation-service)  | (INTERNAL SERVICE) Service for recommending the next place for a tour. | 0.2.0-SNAPSHOT | daniel@stoll.cloud | [docs](https://docs.clines.de/swagger-ui.html#/recommendation-service)

### Security
The clines backend uses the OIDC standard for authentication and authorization of users. The authorization server is 
provided by [Okta](https://www.okta.com/) and can be reached at https://login.clines.de/oauth2/default.

To authenticate against the backend services a valid access token must be passed in the Http-Header field 
"Authorization" with the prefix "Bearer". The access token can be obtained from the authorization server using various 
authentication strategies.




