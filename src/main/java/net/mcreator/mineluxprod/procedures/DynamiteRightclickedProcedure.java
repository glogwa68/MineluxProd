package net.mcreator.mineluxprod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import net.mcreator.mineluxprod.item.DynamiteItem;
import net.mcreator.mineluxprod.MineluxprodMod;

import java.util.Map;

public class DynamiteRightclickedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency world for procedure DynamiteRightclicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency x for procedure DynamiteRightclicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency y for procedure DynamiteRightclicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency z for procedure DynamiteRightclicked!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MineluxprodMod.LOGGER.warn("Failed to load dependency entity for procedure DynamiteRightclicked!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (world instanceof World && !world.isRemote()) {
			ItemEntity entityToSpawn = new ItemEntity((World) world,
					(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
							entity.getEyePosition(1f).add(entity.getLook(1f).x * 3, entity.getLook(1f).y * 3, entity.getLook(1f).z * 3),
							RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
					(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
							entity.getEyePosition(1f).add(entity.getLook(1f).x * 3, entity.getLook(1f).y * 3, entity.getLook(1f).z * 3),
							RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
					(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
							entity.getEyePosition(1f).add(entity.getLook(1f).x * 3, entity.getLook(1f).y * 3, entity.getLook(1f).z * 3),
							RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()),
					new ItemStack(DynamiteItem.block));
			entityToSpawn.setPickupDelay((int) 50000);
			world.addEntity(entityToSpawn);
		}
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private IWorld world;

			public void start(IWorld world, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.world = world;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() {
				if (world instanceof World && !((World) world).isRemote) {
					((World) world).createExplosion(null,
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 3, entity.getLook(1f).y * 3, entity.getLook(1f).z * 3),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 3, entity.getLook(1f).y * 3, entity.getLook(1f).z * 3),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 3, entity.getLook(1f).y * 3, entity.getLook(1f).z * 3),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()),
							(float) 2, Explosion.Mode.BREAK);
				}
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) 200);
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(DynamiteItem.block);
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
					((PlayerEntity) entity).container.func_234641_j_());
		}
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos(x, y, z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.tnt.primed")),
					SoundCategory.MASTER, (float) 1, (float) 1);
		} else {
			((World) world).playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.tnt.primed")),
					SoundCategory.MASTER, (float) 1, (float) 1, false);
		}
	}
}
