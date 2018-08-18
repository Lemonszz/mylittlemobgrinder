package party.lemons.mlmg.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import party.lemons.mlmg.config.ModConstants;
import party.lemons.mlmg.crafting.GrinderTab;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sam on 9/01/2018.
 */
public class BlockMobOoze extends BlockSpiderProof
{
	public BlockMobOoze()
	{
		super(Material.ROCK);

		this.setUnlocalizedName(ModConstants.MODID + ".mob_ooze");
		this.setRegistryName("mob_ooze");
		this.setCreativeTab(GrinderTab.TAB);

		this.setTickRandomly(true);

		this.setHardness(3F);
		this.setResistance(100F);
	}

	public void spawnRandom(World world, double x, double y, double z)
	{
		if(world.isRemote)
			return;

		WorldServer worldServer = (WorldServer) world;
		Biome biome = world.getBiome(new BlockPos(x, y, z));
		List<Biome.SpawnListEntry> spawns = biome.getSpawnableList(EnumCreatureType.MONSTER);
		Biome.SpawnListEntry entry = spawns.get(world.rand.nextInt(spawns.size()));
		BlockPos pos =  new BlockPos(x,y, z);

		if (worldServer.canCreatureTypeSpawnHere(EnumCreatureType.MONSTER, entry, pos) && WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, world, pos))
			try
			{
				Entity entity = entry.newInstance(world);
				entity.setPosition(x + 0.5, y + 0.25, z + 0.5);

				int k = world.getEntitiesWithinAABB(entity.getClass(), new AxisAlignedBB(x, y, z, x+ 1, y + 1, z + 1).grow((double) 10)).size();

				if(k >= 10)
				{
					entity.setDead();
					return;
				}
				else
				{
					world.spawnEntity(entity);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if(worldIn.getDifficulty() == EnumDifficulty.PEACEFUL)
			return;

		spawnRandom(worldIn, pos.getX(), pos.getY() + 1, pos.getZ());
	}
}
