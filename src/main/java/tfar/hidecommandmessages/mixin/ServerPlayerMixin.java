package tfar.hidecommandmessages.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
	@Inject(at = @At("HEAD"), method = "sendSystemMessage(Lnet/minecraft/network/chat/Component;Z)V",cancellable = true)
	private void init(Component pComponent, boolean pBypassHiddenChat, CallbackInfo ci) {
		if (pComponent instanceof MutableComponent mutableComponent) {
			if (mutableComponent.getContents() instanceof TranslatableContents translatableContents) {
				if (translatableContents.getKey().contains("commands.gamemode")) {
					ci.cancel();
				}
			}
		}
	}
}
