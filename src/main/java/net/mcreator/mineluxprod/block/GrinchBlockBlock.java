
package net.mcreator.mineluxprod.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;

@MineluxprodModElements.ModElement.Tag
public class GrinchBlockBlock extends MineluxprodModElements.ModElement {

	@ObjectHolder("mineluxprod:grinch_block")
	public static final Block block = null;

	public GrinchBlockBlock(MineluxprodModElements instance) {
		super(instance, 6);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(MineluxItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.GROUND).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0));

			setRegistryName("grinch_block");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(DynamiteItem.block));
		}

	}

}
