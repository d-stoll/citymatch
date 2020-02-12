import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.google.cloud.tools.jib")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	kotlin("kapt")
}

version = "0.2.0"
group = "de.clines"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "Hoxton.SR1"

jib {
    to.image = "${extra["registry.url"]}/${name}"
    container {
        ports = listOf("8082")
        creationTime = "USE_CURRENT_TIMESTAMP"
    }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.okta.spring:okta-spring-boot-starter:1.3.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.google.maps:google-maps-services:0.10.2")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-bean-validators:2.9.2")
    implementation("org.springframework.cloud:spring-cloud-gcp-pubsub-stream-binder")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

springBoot {
    buildInfo()
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
