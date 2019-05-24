import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "0.6.0.RELEASE"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
}

group = "engineer.oleske"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator") {
		exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
	}
	implementation("org.springframework.boot:spring-boot-starter-web") {
		exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
	}
	implementation("org.springframework.geode:spring-geode-starter:1.0.0.RELEASE")
	implementation("org.springframework.geode:spring-geode-starter-actuator:1.0.0.RELEASE")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("PASSED", "FAILED", "SKIPPED")
		setExceptionFormat("full")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
