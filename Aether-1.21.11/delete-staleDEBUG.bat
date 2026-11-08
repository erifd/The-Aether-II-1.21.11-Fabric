@echo off
cd /d "%~dp0"

del /f /q "src\main\java\com\aetherteam\aetherii\client\renderer\block\model\AetherIIModelLoaders.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\client\renderer\block\model\blockstate\BreakingFixModel.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\data\generators\tags\AetherIIFeatureTagData.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\data\providers\AetherIISimpleLootSubProvider.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\data\resources\registries\AetherIIContextIntProviders.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\BlockFeatureRendererMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\FirstPersonHandsAndItemsRendererMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\FluidRendererMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\ItemModelBakeMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\LayerRenderStateMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\SectionCompilerMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\SkyRendererMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\accessor\BlockModelRenderStateAccessor.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\client\accessor\RenderSectionRegionAccessor.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\mixin\mixins\common\SpeleothemBlockMixin.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\world\density\DensitySampling.java" 2>nul
del /f /q "src\main\java\com\aetherteam\aetherii\world\feature\configuration\SimpleBlockConfiguration.java" 2>nul

echo Done.
pause
