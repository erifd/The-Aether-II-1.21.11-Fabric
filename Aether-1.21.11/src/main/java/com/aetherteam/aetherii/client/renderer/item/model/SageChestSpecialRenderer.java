package com.aetherteam.aetherii.client.renderer.item.model;

import com.aetherteam.aetherii.client.AetherIIAtlases;
import com.aetherteam.aetherii.client.renderer.blockentity.SageChestRenderer;
import com.aetherteam.aetherii.client.renderer.blockentity.model.SageChestModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class SageChestSpecialRenderer implements NoDataSpecialModelRenderer {
    private final MaterialSet materials;
    private final SageChestModel model;
    private final Material sprite;
    private final float openness;

    public SageChestSpecialRenderer(MaterialSet materials, SageChestModel model, Material sprite, float openness) {
        this.materials = materials;
        this.model = model;
        this.sprite = sprite;
        this.openness = openness;
    }

    @Override
    public void submit(ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        submitNodeCollector.submitModel(this.model, this.openness, poseStack, this.sprite.renderType(this.model::renderType), lightCoords, overlayCoords, -1, this.materials.get(this.sprite), outlineColor, null);
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        this.model.setupAnim(this.openness);
        this.model.root().getExtentsForGui(poseStack, output);
    }

    public static record Unbaked(Identifier texture, float openness, ChestType chestType) implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<SageChestSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
                Identifier.CODEC.fieldOf("texture").forGetter(SageChestSpecialRenderer.Unbaked::texture),
                Codec.FLOAT.optionalFieldOf("openness", 0.0F).forGetter(SageChestSpecialRenderer.Unbaked::openness),
                ChestType.CODEC.optionalFieldOf("chest_type", ChestType.SINGLE).forGetter(SageChestSpecialRenderer.Unbaked::chestType)
        ).apply(i, SageChestSpecialRenderer.Unbaked::new));

        public Unbaked(Identifier texture, ChestType chestType) {
            this(texture, 0.0F, chestType);
        }

        public Unbaked(Identifier texture) {
            this(texture, 0.0F, ChestType.SINGLE);
        }

        @Override
        public MapCodec<SageChestSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            SageChestModel model = new SageChestModel(context.entityModelSet().bakeLayer(com.aetherteam.aetherii.client.renderer.blockentity.SageChestRenderer.LAYERS.select(this.chestType)));
            Material fullTexture = AetherIIAtlases.SAGE_CHEST_MAPPER.apply(this.texture);
            return new SageChestSpecialRenderer(context.materials(), model, fullTexture, this.openness);
        }
    }
}
