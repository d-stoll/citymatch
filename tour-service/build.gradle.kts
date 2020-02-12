import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.google.cloud.tools.jib")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
    kotlin("kapt")
}

version = "0.2.0"
group = "de.clines"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven { url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local/") }
}

jib {
    to.image = "${extra["registry.url"]}/${name}"
    container {
        ports = listOf("8081")
        creationTime = "USE_CURRENT_TIMESTAMP"
    }
}

extra["springCloudVersion"] = "Hoxton.SR1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.okta.spring:okta-spring-boot-starter:1.3.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.cloud:spring-cloud-gcp-pubsub-stream-binder")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")
    implementation("io.springfox:springfox-swagger2:3.0.0-SNAPSHOT")
    implementation("io.springfox:springfox-spring-webflux:3.0.0-SNAPSHOT")
    implementation("io.springfox:springfox-bean-validators:3.0.0-SNAPSHOT")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
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
