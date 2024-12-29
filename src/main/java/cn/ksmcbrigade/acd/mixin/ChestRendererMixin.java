package cn.ksmcbrigade.acd.mixin;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestRenderer.class)
public class ChestRendererMixin {
    @Shadow private boolean xmasTextures;

    @Inject(method = "<init>",at = @At("TAIL"))
    public void init(BlockEntityRendererProvider.Context p_173607_, CallbackInfo ci){
        this.xmasTextures = true;
    }
}
