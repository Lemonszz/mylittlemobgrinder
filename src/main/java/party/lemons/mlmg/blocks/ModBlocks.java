package party.lemons.mlmg.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import party.lemons.mlmg.config.ModConstants;
import sun.security.provider.SHA;

/**
 * Created by Sam on 8/01/2018.
 */
@Mod.EventBusSubscriber
@ObjectHolder(ModConstants.MODID)
public class ModBlocks
{
	@ObjectHolder("iron_spike")
	public static BlockSpike IRON_SPIKES = null;

	@ObjectHolder("diamond_spike")
	public static BlockSpike DIAMOND_SPIKE = null;

	@ObjectHolder("shadow_glass")
	public static BlockShadowGlass SHADOW_GLASS = null;

	@ObjectHolder("shadow_Stone")
	public static BlockShadowStone SHADOW_STONE = null;

	@ObjectHolder("mob_ooze")
	public static BlockMobOoze MOB_OOZE = null;

	@ObjectHolder("basic_conveyor")
	public static BlockConveyor BASIC_CONVEYOR = null;

	@ObjectHolder("advanced_conveyor")
	public static BlockConveyor ADVANCED_CONVEYOR = null;

	@ObjectHolder("extreme_conveyor")
	public static BlockConveyor EXREME_CONVEYOR = null;

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(
				new BlockSpike(Material.IRON, "iron_spike"),
				new BlockSpike(Material.IRON,"diamond_spike"),
				new BlockShadowGlass(),
				new BlockShadowStone(),
				new BlockMobOoze(),
				new BlockConveyor(0.25F, "basic_conveyor"),
				new BlockConveyor(2F, "advanced_conveyor"),
				new BlockConveyor(0.7F, "extreme_conveyor")
		);
	}

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				new ItemBlock(IRON_SPIKES).setRegistryName(IRON_SPIKES.getRegistryName()),
				new ItemBlock(DIAMOND_SPIKE).setRegistryName(DIAMOND_SPIKE.getRegistryName()),
				new ItemBlock(SHADOW_GLASS).setRegistryName(SHADOW_GLASS.getRegistryName()),
				new ItemBlock(SHADOW_STONE).setRegistryName(SHADOW_STONE.getRegistryName()),
				new ItemBlock(MOB_OOZE).setRegistryName(MOB_OOZE.getRegistryName()),
				new ItemBlock(BASIC_CONVEYOR).setRegistryName(BASIC_CONVEYOR.getRegistryName()),
				new ItemBlock(ADVANCED_CONVEYOR).setRegistryName(ADVANCED_CONVEYOR.getRegistryName()),
				new ItemBlock(EXREME_CONVEYOR).setRegistryName(EXREME_CONVEYOR.getRegistryName())
		);
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(IRON_SPIKES), 0, new ModelResourceLocation(IRON_SPIKES.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(DIAMOND_SPIKE), 0, new ModelResourceLocation(DIAMOND_SPIKE.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SHADOW_GLASS), 0, new ModelResourceLocation(SHADOW_GLASS.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(SHADOW_STONE), 0, new ModelResourceLocation(SHADOW_STONE.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MOB_OOZE), 0, new ModelResourceLocation(MOB_OOZE.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BASIC_CONVEYOR), 0, new ModelResourceLocation(BASIC_CONVEYOR.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ADVANCED_CONVEYOR), 0, new ModelResourceLocation(ADVANCED_CONVEYOR.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(EXREME_CONVEYOR), 0, new ModelResourceLocation(EXREME_CONVEYOR.getRegistryName(), "inventory"));
	}
}
