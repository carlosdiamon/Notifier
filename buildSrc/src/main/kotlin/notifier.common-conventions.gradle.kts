plugins {
	id("java-library")
}

repositories {
	mavenCentral()
	maven("https://papermc.io/repo/repository/maven-public/")
}

tasks {
	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(11))
		}
	}

	compileJava {
		options.compilerArgs.add("-parameters")
	}
}