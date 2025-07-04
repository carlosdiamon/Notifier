plugins {
	id("notifier.common-conventions")
	id("notifier.publishing-conventions")
}

dependencies {
	api(projects.notifierCore)

	compileOnly(libs.annotation)
	compileOnly(libs.adventure)
	compileOnly(libs.configurate.yaml)
}
