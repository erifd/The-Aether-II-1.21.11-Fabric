<div align="center">

# The Aether II (Fabric 1.21.11)

[![Minecraft 1.21.11](https://img.shields.io/badge/Minecraft-1.21.11-blue?style=for-the-badge&logo=minecraft)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Modloader-Fabric-dbb879?style=for-the-badge&logo=fabric)](https://fabricmc.net/)
[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![License: LGPL v3.0](https://img.shields.io/badge/Code_License-LGPL_v3.0-green.svg?style=for-the-badge)](https://www.gnu.org/licenses/lgpl-3.0.html)
[![Assets](https://img.shields.io/badge/Assets-All_Rights_Reserved-red.svg?style=for-the-badge)](https://en.wikipedia.org/wiki/All_rights_reserved)

*A community port of **The Aether II** to Minecraft 1.21.11 powered by the Fabric modloader.*

</div>

---

> [!WARNING]
> **Work in progress.** This port is under active development. It may not compile
> cleanly or run yet, and nothing here should be treated as a stable release.

## Overview

**The Aether II** is the sequel to the legendary dimension mod, *The Aether*. Ascend to the
Hostile and Holy Isles of the Aether dimension, featuring new biomes, unique mobs, mysterious
dungeons, expansive progression systems, and custom world generation.

This repository is a fork of
[roggy666/The-Aether-II-26.3-Fabric](https://github.com/roggy666/The-Aether-II-26.3-Fabric),
adapted to **Minecraft 1.21.11** on **Fabric**. It targets 1.21.11 only. The mod lives in
[`Aether-1.21.11/`](Aether-1.21.11).

### Key Features in this Port
- **Minecraft 1.21.11 and Fabric:** Built for Fabric Loader `0.18.4` and Fabric API `0.141.3+1.21.11`.
- **Java 21:** Uses the Java 21 toolchain.
- **Libraries included:** The Nitrogen and Cumulus libraries the mod depends on are part of
  this repository ([`Nitrogen-1.21.11/`](Nitrogen-1.21.11) and
  [`Cumulus-1.21.11/`](Cumulus-1.21.11)), so no extra downloads are needed.
- **Data generation:** A `runDatagen` task is available for regenerating recipes, tags, loot
  tables, blockstates, and models.
- **Localization:** English (`en_us`) and Russian (`ru_ru`).

---

## Prerequisites

Before building, ensure you have:
1. **JDK 21** (Java Development Kit 21) installed and configured in your `PATH` / `JAVA_HOME`.
   - Recommended: OpenJDK 21 or Eclipse Temurin 21.
2. **Git** for cloning the repository.

---

## Setting Up Dependencies (mavenLocal)

The mod requires **Nitrogen** and **Cumulus**. Both are included in this repository and are
resolved through `mavenLocal()`, so publish them to your local Maven cache before building the
mod:

- `com.aetherteam.nitrogen:nitrogen_internals-fabric:1.21.11-1.3.5-fabric`
- `com.aetherteam.cumulus:cumulus_menus:1.21.11-2.0.15-fabric`

> [!NOTE]
> All other dependencies (Fabric Loader, Fabric API, Forge Config API Port, JEI, MixinExtras)
> are fetched automatically from public repositories. No other manual downloads are required.

### Automated Setup

#### Option A: PowerShell (Windows)
Run from the repository root:

```powershell
foreach ($project in "Nitrogen-1.21.11", "Cumulus-1.21.11") {
    Push-Location $project
    .\gradlew publishToMavenLocal
    Pop-Location
}
Write-Host "Local Maven dependencies installed successfully!" -ForegroundColor Green
```

#### Option B: Bash (Linux / macOS)
Run from the repository root:

```bash
for project in Nitrogen-1.21.11 Cumulus-1.21.11; do
    (cd "$project" && ./gradlew publishToMavenLocal)
done
echo "Local Maven dependencies installed successfully!"
```

#### Option C: Maven CLI (Alternative)
If you have Maven (`mvn`) installed, build each library's jar first (`./gradlew build` inside
`Nitrogen-1.21.11/` and `Cumulus-1.21.11/`), then install the jars from their `build/libs/`
folders:

```bash
mvn install:install-file -Dfile=nitrogen_internals-fabric-1.21.11-1.3.5-fabric.jar -DgroupId=com.aetherteam.nitrogen -DartifactId=nitrogen_internals-fabric -Dversion=1.21.11-1.3.5-fabric -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=cumulus_menus-1.21.11-2.0.15-fabric.jar -DgroupId=com.aetherteam.cumulus -DartifactId=cumulus_menus -Dversion=1.21.11-2.0.15-fabric -Dpackaging=jar -DgeneratePom=true
```

You only need to repeat this step when you change Nitrogen or Cumulus.

---

## Building from Source

Once the dependencies are installed into `mavenLocal`, work from the `Aether-1.21.11` folder:

```bash
cd Aether-1.21.11
```

### 1. Build the Mod JAR
```bash
# Windows
.\gradlew build

# Linux / macOS
./gradlew build
```
The compiled mod JAR will be located in `Aether-1.21.11/build/libs/`
(for example `aether_ii-fabric-1.21.11-1.0-alpha.4.1-fabric.jar`).

### 2. Run Minecraft Client in Development
To launch the client and test directly from source:
```bash
# Windows
.\gradlew runClient

# Linux / macOS
./gradlew runClient
```

### 3. Run Data Generation (Datagen)
To regenerate recipes, tags, blockstates, or models:
```bash
# Windows
.\gradlew runDatagen

# Linux / macOS
./gradlew runDatagen
```

---

## Installing the Built Mod

Nitrogen and Cumulus are separate mods, so place all of these in your `mods` folder on
Minecraft 1.21.11 with Fabric Loader 0.18.4 or newer:

1. The Aether II jar (from `Aether-1.21.11/build/libs/`)
2. The Nitrogen jar (from `Nitrogen-1.21.11/build/libs/`)
3. The Cumulus jar (from `Cumulus-1.21.11/build/libs/`)
4. [Fabric API](https://modrinth.com/mod/fabric-api) for 1.21.11
5. [Forge Config API Port](https://modrinth.com/mod/forge-config-api-port) for 1.21.11

---

## Localization

- **English (`en_us`)**: Generated by datagen into `src/generated/resources`.
- **Russian (`ru_ru`)**: Provided in `src/main/resources/assets/aether_ii/lang/`.

---

## Credits and Licensing

- **Original Creators**: [The Aether Team](https://github.com/The-Aether-Team). Support the
  official team on [Patreon](https://patreon.com/TheAetherTeam) and visit the
  [Official Aether Discord](https://discord.gg/aethermod).
- **Upstream fork**: [roggy666/The-Aether-II-26.3-Fabric](https://github.com/roggy666/The-Aether-II-26.3-Fabric).
- **Code License**: Licensed under the [GNU Lesser General Public License v3.0 (LGPL v3.0)](https://www.gnu.org/licenses/lgpl-3.0.html).
- **Asset License**: All rights reserved by The Aether Team and respective authors.
