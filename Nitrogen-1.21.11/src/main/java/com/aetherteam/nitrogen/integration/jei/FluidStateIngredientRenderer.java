package com.aetherteam.nitrogen.integration.jei;

import com.aetherteam.nitrogen.client.renderer.state.FluidStateRenderState;
import mezz.jei.api.fabric.ingredients.fluids.IJeiFluidIngredient;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record FluidStateIngredientRenderer<T>(IPlatformFluidHelper<T> fluidHelper) implements IIngredientRenderer<T> {
    @Override
    public void render(GuiGraphics graphics, @Nullable T ingredient) {
        if (ingredient instanceof IJeiFluidIngredient fluidIngredient) {
            FluidState fluidState = fluidIngredient.getFluidVariant().getFluid().defaultFluidState();

            //grab the x and y translation from the matrix for proper positioning
            int x = (int) graphics.pose().m20();
            int y = (int) graphics.pose().m21();

            graphics.guiRenderState.submitPicturesInPictureState(new FluidStateRenderState(fluidState, x, y, x + 256, y + 256, graphics.scissorStack.peek()));
        }
    }

    @Override
    public List<Component> getTooltip(T ingredient, TooltipFlag tooltipFlag) {
        List<Component> components = new ArrayList<>();
        if (ingredient instanceof IJeiFluidIngredient fluidIngredient) {
            Fluid fluid = fluidIngredient.getFluidVariant().getFluid();
            if (fluid.isSame(Fluids.EMPTY)) {
                return List.of();
            }

            Component displayName = FluidVariantAttributes.getName(fluidIngredient.getFluidVariant());
            components.add(displayName);

            if (tooltipFlag.isAdvanced()) {
                Identifier resourceLocation = BuiltInRegistries.FLUID.getKey(fluid);
                if (resourceLocation != null && resourceLocation != BuiltInRegistries.FLUID.getDefaultKey()) {
                    MutableComponent advancedId = Component.literal(resourceLocation.toString())
                        .withStyle(ChatFormatting.DARK_GRAY);
                    components.add(advancedId);
                }
            }
        }
        return components;
    }
}
