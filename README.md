<div align="center">

# The Aether II (Fabric 1.21.11)

[![Minecraft 1.21.11](https://img.shields.io/badge/Minecraft-1.21.11-blue?style=for-the-badge&logo=minecraft)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Modloader-Fabric-dbb879?style=for-the-badge&logo=fabric)](https://fabricmc.net/)
[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![License: LGPL v3.0](https://img.shields.io/badge/Code_License-LGPL_v3.0-green.svg?style=for-the-badge)](https://www.gnu.org/licenses/lgpl-3.0.html)
[![Assets](https://img.shields.io/badge/Assets-All_Rights_Reserved-red.svg?style=for-the-badge)](https://en.wikipedia.org/wiki/All_rights_reserved)

*An unofficial community port of **The Aether II** to Minecraft 1.21.11 on the Fabric modloader.*

</div>

---

> [!WARNING]
> **Work in progress.** This port is under active development. It may not compile
> cleanly or run yet, and nothing here should be treated as a stable release. Expect
> missing features, crashes, and breaking changes between commits.

## Overview

**The Aether II** is the sequel to the original dimension mod, *The Aether*. It adds the
Aether dimension with new biomes, mobs, dungeons, progression systems, and custom world
generation.

This repository is a fork of [roggy666/The-Aether-II-26.3-Fabric](https://github.com/roggy666/The-Aether-II-26.3-Fabric),
adapted to run on **Minecraft 1.21.11 with the Fabric modloader**. That means updating the
code, Mixins, and dependencies for the Minecraft 1.21.11 changes.

This project is **not affiliated with or endorsed by The Aether Team**.

---

## Repository Layout

Everything needed to build the mod is in this one repository. **Nitrogen and Cumulus, the
two library mods that The Aether II depends on, are included here as their own projects,**
so there is nothing extra to download.

| Directory | What it is |
|---|---|
| [`Aether-1.21.11/`](Aether-1.21.11) | The Aether II mod itself (Fabric, Minecraft 1.21.11) |
| [`Nitrogen-1.21.11/`](Nitrogen-1.21.11) | Nitrogen, the Aether Team's shared library, ported to Fabric 1.21.11 |
| [`Cumulus-1.21.11/`](Cumulus-1.21.11) | Cumulus, the Aether Team's menu library, ported to Fabric 1.21.11 |
| [`Aether-26.3/`](Aether-26.3) | The upstream Minecraft 26.3 version this fork started from (not covered by this README) |

---

## Versions

| Component | Version |
|---|---|
| Minecraft | 1.21.11 |
| Java | 21 |
| Fabric Loader | 0.18.4 |
| Fabric API | 0.141.3+1.21.11 |
| Fabric Loom | 1.15 |
| Forge Config API Port | 21.11.1 |
| MixinExtras | 0.5.3 |
| JEI (compile-only) | 27.4.0.15 |
| Nitrogen (this repo) | 1.21.11-1.3.5-fabric |
| Cumulus (this repo) | 1.21.11-2.0.15-fabric |

Fabric Loader, Fabric API, Forge Config API Port, JEI, and MixinExtras are downloaded
automatically by Gradle from public repositories.

---

## Prerequisites

- **JDK 21** installed and available on your `PATH` / `JAVA_HOME`
  (OpenJDK 21, Eclipse Temurin 21, or Oracle JDK 21).
- **Git** to clone the repository.

---

## Building from Source

The mod resolves Nitrogen and Cumulus from your local Maven cache (`mavenLocal`), so build
and publish those two **first**, then build the mod.

### 1. Clone the repository

```bash
git clone https://github.com/erifd/The-Aether-II-1.21.11-Fabric.git
cd The-Aether-II-1.21.11-Fabric
```

### 2. Publish Nitrogen and Cumulus to your local Maven cache

```bash
# Linux / macOS
cd Nitrogen-1.21.11 && ./gradlew publishToMavenLocal && cd ..
cd Cumulus-1.21.11  && ./gradlew publishToMavenLocal && cd ..
```

```powershell
# Windows (PowerShell)
cd Nitrogen-1.21.11; .\gradlew publishToMavenLocal; cd ..
cd Cumulus-1.21.11;  .\gradlew publishToMavenLocal; cd ..
```

This installs the following into `~/.m2/repository`:

- `com.aetherteam.nitrogen:nitrogen_internals-fabric:1.21.11-1.3.5-fabric`
- `com.aetherteam.cumulus:cumulus_menus:1.21.11-2.0.15-fabric`

You only need to repeat this step when you change Nitrogen or Cumulus.

### 3. Build the mod

```bash
# Linux / macOS
cd Aether-1.21.11
./gradlew build
```

```powershell
# Windows (PowerShell)
cd Aether-1.21.11
.\gradlew build
```

The compiled jar is written to `Aether-1.21.11/build/libs/`
(for example `aether_ii-fabric-1.21.11-1.0-alpha.4.1-fabric.jar`).

### 4. Run the client in development

```bash
./gradlew runClient      # Linux / macOS
.\gradlew runClient      # Windows
```

### 5. Run data generation (datagen)

To regenerate recipes, tags, loot tables, blockstates, and models into
`src/generated/resources`:

```bash
./gradlew runDatagen     # Linux / macOS
.\gradlew runDatagen     # Windows
```

---

## Installing the Built Mod

Nitrogen and Cumulus are separate mods, so a normal install needs all of these in your
`mods` folder:

1. `aether_ii-fabric-*.jar` (from `Aether-1.21.11/build/libs/`)
2. `nitrogen_internals-fabric-*.jar` (from `Nitrogen-1.21.11/build/libs/`)
3. `cumulus_menus-*.jar` (from `Cumulus-1.21.11/build/libs/`)
4. [Fabric API](https://modrinth.com/mod/fabric-api) for 1.21.11
5. [Forge Config API Port](https://modrinth.com/mod/forge-config-api-port) for 1.21.11

Use Fabric Loader 0.18.4 or newer on Minecraft 1.21.11.

---

## Status & Known Issues

- The port is being worked through incrementally; some systems may still fail to compile
  or not work correctly yet.
- Nitrogen and Cumulus have been ported for Fabric 1.21.11 but have had little to no
  in-game testing.
- Parchment mappings are not available for 1.21.11, so the project uses the official
  Mojang mappings only.
- Please open an issue with the full log if you hit a crash or a build error.

---

## Localization

- **English (`en_us`)**: generated by datagen into `src/generated/resources`.
- **Russian (`ru_ru`)**: provided in `src/main/resources/assets/aether_ii/lang/`.

---

## Credits and Licensing

- **Original creators**: [The Aether Team](https://github.com/The-Aether-Team). Support
  the official team on [Patreon](https://patreon.com/TheAetherTeam) and visit the
  [Official Aether Discord](https://discord.gg/aethermod).
- **Upstream fork**: this repository is a fork of
  [roggy666/The-Aether-II-26.3-Fabric](https://github.com/roggy666/The-Aether-II-26.3-Fabric).
- **Nitrogen** and **Cumulus** are forks of The Aether Team's libraries of the same names,
  adapted for Fabric 1.21.11.
- **Code license**: [GNU Lesser General Public License v3.0 (LGPL v3.0)](https://www.gnu.org/licenses/lgpl-3.0.html).
- **Asset license**: all rights reserved by The Aether Team and the respective authors.
  Assets are not covered by the code license.
