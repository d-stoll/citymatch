import com.google.cloud.tools.jib.gradle.JibExtension as JibExtension

plugins {
    id("com.google.cloud.tools.jib") version "1.8.0" apply false
    id("org.springframework.boot") version "2.2.2.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    kotlin("jvm") version "1.3.61" apply false
    kotlin("plugin.spring") version "1.3.61" apply false
    kotlin("plugin.jpa") version "1.3.61" apply false
    kotlin("kapt") version "1.3.61" apply false
}

subprojects {
    afterEvaluate {
        extensions.configure(JibExtension::class) {
            to {
                image = "${extra["registry.url"]}/${name}"

                val keys = properties["keys"]
                if(keys != null) {
                    auth {
                        username = "_json_key"
                        password = file(keys).readText()
                    }
                } else {
                    credHelper = "gcloud"
                }
            }
        }
    }
}
