package net.mcreator.mineluxprod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.mineluxprod.MineluxprodMod;

import java.util.Map;

public class TestprodProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency world for procedure Testprod!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency x for procedure Testprod!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency y for procedure Testprod!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency z for procedure Testprod!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if ((world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == Blocks.AIR) {
			{
				BlockPos _bp = new BlockPos(x, y - 1, z);
				BlockState _bs = Blocks.CRYING_OBSIDIAN.getDefaultState();
				world.setBlockState(_bp, _bs, 3);
			}
		}
	}
}
