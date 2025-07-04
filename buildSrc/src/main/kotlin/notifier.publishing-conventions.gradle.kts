plugins {
	id("notifier.common-conventions")
	`maven-publish`
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["java"])
		}
	}
}