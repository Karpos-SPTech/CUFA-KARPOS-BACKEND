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
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Flyway
	implementation("org.flywaydb:flyway-core:9.22.3")
	implementation("org.flywaydb:flyway-mysql:9.22.3")
	implementation("mysql:mysql-connector-java:8.0.33")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
