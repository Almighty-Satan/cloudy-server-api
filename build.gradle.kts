plugins {
    id("java")
    id("checkstyle")
    id("maven-publish")
}

group = "io.github.almighty-satan.cloudy-server-api"
version = "1.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

checkstyle {
    configDirectory.set(File("checkstyle"))
    toolVersion = "9.3"
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
            pom {
                name.set("cloudy-server-api")
                description.set("Cloudy Server API")
                url.set("https://github.com/Almighty-Satan/cloudy-server-api")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        name.set("Almighty-Satan")
                        url.set("https://github.com/Almighty-Satan")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Almighty-Satan/cloudy-server-api.git")
                    developerConnection.set("scm:git:ssh://github.com:Almighty-Satan/cloudy-server-api.git")
                    url.set("https://github.com/Almighty-Satan/cloudy-server-api")
                }
            }
        }
        repositories {
            maven {
                setUrl("https://maven.pkg.github.com/Almighty-Satan/cloudy-server-api")
                credentials {
                    username = "Almighty-Satan"
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
