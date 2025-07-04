rootProject.name = "notifier"

val subprojects = listOf("core", "configurate-yml")

subprojects.forEach { includePrefixed(it) }

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

fun includePrefixed(name: String) {
	val kebabName = name.replace(":", "-")
	val path = name.replace(":", "/")
	val baseName = "${rootProject.name}-$kebabName"

	include(baseName)
	project(":$baseName").projectDir = file(path)
}