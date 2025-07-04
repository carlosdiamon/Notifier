# 🔔 Notifier

A lightweight, flexible library for sending customizable announcements to players in [Kyori Adventure](https://github.com/KyoriPowered/adventure) projects. Notifier lets you define messages in code or external configuration, supports [MiniMessage](https://docs.advntr.dev/minimessage/format.html) styling, and makes internationalization (i18n) a breeze.

---

## 🚶‍♂️‍➡️ Quick Start

### 1. Publish to your local Maven

```bash
./gradlew publishToMavenLocal
```
### 2. Add to your project
```kotlin
repositories { 
	mavenLocal()
}

dependencies {
	implementation("io.github.carlosdiamon:notifier-core:1.0")
	implementation("io.github.carlosdiamon:notifier-configurate-yml:1.0") // Optional - ConfigurateYAML
}
```

## 🥶 Basic Usage

```java
// Optional: supply your own MiniMessage instance
Formatter formatter = new AdventureFormatter(myMiniMessage);
Notifier notifier = new DefaultNotifier(formatter);
```
Now to send an advertisement you have to do the following.

```java
Announcement welcome = Announcement.create()
    .message("Welcome, <player_name>!")
    .actionbar("<gold>Have fun!</gold>")
    .build();

TagResolver resolver = TagResolver.resolver("player_name", Tag.inserting(audience.username()));
notifier.send(audience, welcome, resolver);
```
Of course, hard-coding announcements into the code is not ideal. In this example [Configurate](https://github.com/SpongePowered/Configurate) will be used to load and serialize `Announcement`:

```java
@ConfigSerializable
public class MessagesConfiguration {

    private Announcement welcome = Announcement.message("¡Bienvenido! <player_name>");
    // ...
	
    public Announcement getWelcome() {
        return welcome;
    }
}
```

```java
Announcement welcome = configuration.getWelcome();
notifier.send(audience, welcome, resolver);
```

## 🌐 Internationalization (i18n)
Use `ResourceNotifier` to lookup messages by key:
```java
// Implement your lookup logic (e.g., from YAML files per locale)
AnnouncementProvider provider = (audience, key) -> {
    // return Announcement for the given key and audience
};

Notifier delegate = new DefaultNotifier(formatter);
ResourceNotifier notifier = new DefaultResourceNotifier(delegate, provider);

```
```java
notifier.send(audience, "messages.welcome", resolver);
```