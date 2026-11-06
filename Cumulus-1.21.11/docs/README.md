<p align="center"><img width="250" height="250" src="assets/logo.webp" alt="Cumulus Logo"></p>

# Cumulus (Fabric 1.21.11)

[![Modloader: Fabric](https://img.shields.io/badge/mod%20loader-fabric-1F6FEB?style=flat-square)](https://fabricmc.net/use/installer/)
[![Minecraft: 1.21.11](https://img.shields.io/badge/minecraft-1.21.11-blue?style=flat-square)](https://www.minecraft.net/)
[![Java: 21](https://img.shields.io/badge/java-21-ED8B00?style=flat-square)](https://adoptium.net/)
[![Code license (LGPL v3.0)](https://img.shields.io/badge/code%20license-LGPL%20v3.0-green.svg?style=flat-square)](../LICENSE.txt)

This repository is a fork of **Cumulus** by The Aether Team, ported and updated for **Fabric Minecraft 1.21.11** (running on **Java 21**).

---

## 📖 About Cumulus

**Cumulus** is a menu customization library used by The Aether and other mods. It provides:
- Custom main menu screen registration and theme switching
- Customizable title screen layouts, logos, splash texts, and music
- Background panorama and dirt texture swapping
- In-game menu selection button on the main title screen

---

## 🛠️ Changes in the Fabric 1.21.11 Port

* **Minecraft 1.21.11 & Fabric API:** Fully adapted to Minecraft 1.21.11 and modern Fabric API (`fabric-api-0.141.3+1.21.11`).
* **Java 21 Requirement:** Configured strictly for Java 21 via Gradle toolchains (`JavaLanguageVersion.of(21)`).
* **Architecture Modernization:** Refactored from the legacy multi-module structure into a unified, clean Fabric project.
* **Config Management:** Powered by Forge Config API Port for Fabric (`forgeconfigapiport-fabric`).
* **Mixin Architecture:** Updated all title screen and menu mixins for 1.21.11 client rendering pipelines.

---

## 📦 Building & Dependency Usage

### Requirements:
* **JDK 21**

### Build and Publish to Maven Local:
```bash
./gradlew publishToMavenLocal
```

### Dependency Setup in Fabric Loom (`build.gradle`):
```groovy
repositories {
    mavenLocal()
}

dependencies {
    modImplementation "com.aetherteam.cumulus:cumulus:${project.cumulus_version}"
}
```

---

## 📄 License

The code and assets are distributed under the [LGPL v3.0](../LICENSE.txt) license.  
Original project: [The-Aether-Team/Cumulus](https://github.com/The-Aether-Team/Cumulus).
