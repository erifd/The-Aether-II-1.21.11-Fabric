<p align="center"><img width="250" height="250" src="docs/assets/logo.webp" alt="Cumulus Logo"></p>

# Cumulus (Fabric 1.21.11)

[![Modloader: Fabric](https://img.shields.io/badge/mod%20loader-fabric-1F6FEB?style=flat-square)](https://fabricmc.net/use/installer/)
[![Minecraft: 1.21.11](https://img.shields.io/badge/minecraft-1.21.11-blue?style=flat-square)](https://www.minecraft.net/)
[![Java: 21](https://img.shields.io/badge/java-21-ED8B00?style=flat-square)](https://adoptium.net/)
[![Code license (LGPL v3.0)](https://img.shields.io/badge/code%20license-LGPL%20v3.0-green.svg?style=flat-square)](LICENSE.txt)

This repository is a fork of [roggy666/Cumulus-26.3](https://github.com/roggy666/Cumulus-26.3), adapted for **Minecraft 1.21.11** on **Fabric**, running on **Java 21**. Cumulus was originally created by The Aether Team.

---

## About Cumulus

**Cumulus** is a menu customization library used by The Aether and other mods. It provides:
- Custom main menu screen registration and theme switching
- Customizable title screen layouts, logos, splash texts, and music
- Background panorama and dirt texture swapping
- In-game menu selection button on the main title screen

---

## Changes in the Fabric 1.21.11 Port

* **Minecraft 1.21.11 and Fabric API:** Adapted to Minecraft 1.21.11 and Fabric API `0.141.3+1.21.11`.
* **Java 21 requirement:** Configured for Java 21 through Gradle toolchains (`JavaLanguageVersion.of(21)`).
* **Project structure:** Reduced from the multi-module layout to a single Fabric project.
* **Config management:** Uses Forge Config API Port for Fabric (`forgeconfigapiport-fabric`).
* **Mixins:** Title screen and menu mixins updated for the 1.21.11 client rendering pipeline.

---

## Building and Dependency Usage

### Requirements
* JDK 21

### Build and publish to Maven Local
```bash
./gradlew publishToMavenLocal
```

This publishes `com.aetherteam.cumulus:cumulus_menus:1.21.11-2.0.15-fabric` to your local Maven cache.

### Using Cumulus as a dependency (`build.gradle`)
```groovy
repositories {
    mavenLocal()
}

dependencies {
    modImplementation "com.aetherteam.cumulus:cumulus_menus:${project.cumulus_version}"
}
```

Set `cumulus_version=1.21.11-2.0.15-fabric` in your `gradle.properties`.

---

## License

The code is distributed under the [LGPL v3.0](LICENSE.txt) license.
Original project: [The-Aether-Team/Cumulus](https://github.com/The-Aether-Team/Cumulus).
