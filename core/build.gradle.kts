plugins {
	id("notifier.common-conventions")
	id("notifier.publishing-conventions")
}

dependencies {
	compileOnly(libs.annotation)
	compileOnly(libs.minimessage)
	compileOnly(libs.adventure)
}
