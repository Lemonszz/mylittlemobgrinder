package party.lemons.mlmg.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import party.lemons.mlmg.config.ModConstants;
import party.lemons.mlmg.crafting.GrinderTab;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Sam on 9/01/2018.
 */
public class BlockShadowStone extends BlockSpiderProof
{
	public BlockShadowStone()
	{
		super(Material.ROCK);

		this.setUnlocalizedName(ModConstants.MODID + ".shadow_stone");
		this.setRegistryName("shadow_stone");
		this.setCreativeTab(GrinderTab.TAB);

		this.setHardness(3F);
		this.setResistance(100F);
	}
}
