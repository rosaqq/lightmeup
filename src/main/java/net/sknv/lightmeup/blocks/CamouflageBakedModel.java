package net.sknv.lightmeup.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

/**
 * This class is used to customise the rendering of the camouflage block, based on the block it is copying.
 * Credit TheGreyGhost:
 * https://github.com/TheGreyGhost/MinecraftByExample/blob/1-12-2-final/src/main/java/minecraftbyexample/mbe04_block_dynamic_block_model1/CamouflageBakedModel.java
 */
public class CamouflageBakedModel implements IBakedModel {

    // create a blockstates tag (MRL) for the block
    public static final ModelResourceLocation blockStatesFileName = new ModelResourceLocation("lightmeup:light_block");
    // create a variant tag (MRL) for the block
    public static final ModelResourceLocation variantTag = new ModelResourceLocation("lightmeup:light_block", "normal");

    private IBakedModel modelWhenNotCamouflaged;

    public CamouflageBakedModel(IBakedModel unCamouflagedModel) {
        modelWhenNotCamouflaged = unCamouflagedModel;
    }

    // return a list of the quads making up the model.
    // We choose the model based on the IBlockstate provided by the caller
    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return handleBlockState(state).getQuads(state, side, rand);
    }

    // this method is used to create a suitable IBakedModel based on the IBlockState of the block being rendered.
    // if IBlockState is an instance of IExtendedBlockState, you can use it to pass i any information you want
    private IBakedModel handleBlockState(@Nullable IBlockState iBlockState) {
        IBakedModel retval = modelWhenNotCamouflaged;
        IBlockState UNCAMOUFLAGED_BLOCK = Blocks.AIR.getDefaultState();

        // extract the block to be copied from the IExtendedBlockState, previously set by Block.getExtendedState()
        // if the block is null, the block is not camouflaged so use the uncamouflaged model.
        if(iBlockState instanceof IExtendedBlockState) {
            IExtendedBlockState iExtendedBlockState = (IExtendedBlockState) iBlockState;
            IBlockState copiedBlockIBlockState = iExtendedBlockState.getValue(BlockLight.COPIEDBLOCK);

            if (copiedBlockIBlockState != UNCAMOUFLAGED_BLOCK) {
                // retrieve the IBakedModel of the copied block and return it
                Minecraft mc = Minecraft.getMinecraft();
                BlockRendererDispatcher rendererDisp = mc.getBlockRendererDispatcher();
                BlockModelShapes modelShapes = rendererDisp.getBlockModelShapes();
                IBakedModel copiedBlockModel = modelShapes.getModelForState(copiedBlockIBlockState);
                retval = copiedBlockModel;
            }
        }
        return retval;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return modelWhenNotCamouflaged.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return modelWhenNotCamouflaged.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return modelWhenNotCamouflaged.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return modelWhenNotCamouflaged.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return modelWhenNotCamouflaged.getOverrides();
    }

    /**
     * this method is necessary because Forge has deprecated getItemCameraTransforms(), and modelCore.getItemCameraTransforms()
     * may not return anything meaningful.
     */
    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        Matrix4f matrix = modelWhenNotCamouflaged.handlePerspective(cameraTransformType).getRight();
        return Pair.of(this, matrix);
    }
}
