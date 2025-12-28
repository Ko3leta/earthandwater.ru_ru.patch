package potatowolfie.earth_and_water.mixin;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.ShieldDecorationRecipe;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import potatowolfie.earth_and_water.util.ModTags;

// Code used from "More Shield Variants" by hypothetiKal and pnku under a MIT License

@Mixin(ShieldDecorationRecipe.class)
public abstract class ShieldDecorationRecipeMixin extends SpecialCraftingRecipe {

    public ShieldDecorationRecipeMixin(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Inject(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At("HEAD"), cancellable = true)
    private void injectedMatches(RecipeInputInventory input, World world, CallbackInfoReturnable<Boolean> cir) {
        ItemStack shield = ItemStack.EMPTY;
        ItemStack banner = ItemStack.EMPTY;
        boolean hasSpikedShield = false;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getStack(i);
            if (stack.isEmpty()) continue;

            if (stack.isIn(ModTags.Item.SPIKED_SHIELD)) {
                hasSpikedShield = true;
                break;
            }
        }

        if (!hasSpikedShield) {
            return;
        }

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getStack(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof BannerItem) {
                if (!banner.isEmpty()) {
                    cir.setReturnValue(false);
                    return;
                }
                banner = stack;
            } else if (stack.isIn(ModTags.Item.SPIKED_SHIELD)) {
                if (!shield.isEmpty()) {
                    cir.setReturnValue(false);
                    return;
                }

                if (BlockItem.getBlockEntityNbt(stack) != null) {
                    cir.setReturnValue(false);
                    return;
                }

                shield = stack;
            } else {
                cir.setReturnValue(false);
                return;
            }
        }

        boolean result = !shield.isEmpty() && !banner.isEmpty();
        cir.setReturnValue(result);
    }

    @Inject(method = "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void injectedCraft(RecipeInputInventory input, DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack banner = ItemStack.EMPTY;
        ItemStack shield = ItemStack.EMPTY;
        boolean hasSpikedShield = false;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getStack(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof BannerItem) {
                banner = stack;
            } else if (stack.isIn(ModTags.Item.SPIKED_SHIELD)) {
                hasSpikedShield = true;
                shield = stack.copy();
            }
        }

        if (!hasSpikedShield) {
            return;
        }

        if (shield.isEmpty()) {
            cir.setReturnValue(shield);
            return;
        }

        NbtCompound bannerNbt = BlockItem.getBlockEntityNbt(banner);
        NbtCompound shieldNbt = bannerNbt == null ? new NbtCompound() : bannerNbt.copy();
        shieldNbt.putInt("Base", ((BannerItem) banner.getItem()).getColor().getId());
        BlockItem.setBlockEntityNbt(shield, BlockEntityType.BANNER, shieldNbt);

        cir.setReturnValue(shield);
    }
}