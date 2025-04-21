plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "10.18.0"
}

group = "cufa.conecta.com.br"
version = "0.0.1-SNAPSHOT"


java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("mysql:mysql-connector-java:8.0.33")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	//Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
