package party.lemons.mlmg.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import party.lemons.mlmg.config.ModConfig;
import party.lemons.mlmg.config.ModConstants;
import party.lemons.mlmg.crafting.GrinderTab;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Sam on 8/01/2018.
 */
public class BlockSpike extends Block
{
	public static final PropertyDirection FACING = BlockDirectional.FACING;

	private static final double SIZE_OFFSET = 0.025D;
	private static AxisAlignedBB[] DIRECTION_AABBS = {
			new AxisAlignedBB(0D, SIZE_OFFSET, 0D, 1D, 1D, 1D),
			new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - SIZE_OFFSET, 1D),
			new AxisAlignedBB(0D, 0D, SIZE_OFFSET, 1D, 1D, 1D),
			new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 1D - SIZE_OFFSET),
			new AxisAlignedBB(SIZE_OFFSET, 0D, 0D, 1D, 1D, 1D),
			new AxisAlignedBB(0D, 0D, 0D, 1D - SIZE_OFFSET, 1D, 1D)
	};

	public BlockSpike(Material material, String name)
	{
		super(material);

		this.setUnlocalizedName(ModConstants.MODID + "."  + name);
		this.setRegistryName(name);
		this.setCreativeTab(GrinderTab.TAB);

		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		this.setHardness(2F);
		this.setResistance(0.25F);
		this.setSoundType(SoundType.ANVIL);
	}

	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return DIRECTION_AABBS[blockState.getValue(FACING).ordinal()];
	}

	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultDirection(worldIn, pos, state);
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		if(entityIn instanceof EntityItem && !destroysItems())
		{
			return;
		}
		if(entityIn instanceof EntityXPOrb)
		{
			return;
		}

		entityIn.attackEntityFrom(new DamageSourceSpike(this), (float) getDamage());
	}

	public boolean destroysItems()
	{
		if(this == ModBlocks.IRON_SPIKES)
		{
			return ModConfig.GameplayConfig.ironSpikesDestroyItems;
		}
		if(this == ModBlocks.DIAMOND_SPIKE)
		{
			return ModConfig.GameplayConfig.diamondSpikesDestroyItems;
		}

		return false;
	}

	public double getDamage()
	{
		if(this == ModBlocks.IRON_SPIKES)
		{
			return ModConfig.GameplayConfig.damageIronSpike;
		}
		if(this == ModBlocks.DIAMOND_SPIKE)
		{
			return ModConfig.GameplayConfig.damageDiamondSpike;
		}
		return 0.0D;
	}

	public boolean dropsXP()
	{
		if(this == ModBlocks.IRON_SPIKES)
		{
			return ModConfig.GameplayConfig.ironSpikesDropXP;
		}
		if(this == ModBlocks.DIAMOND_SPIKE)
		{
			return ModConfig.GameplayConfig.diamondSpikesDropXP;
		}
		return false;
	}

	private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			EnumFacing enumfacing = state.getValue(FACING);
			boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
			boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

			if (enumfacing == EnumFacing.NORTH && flag && !flag1)
			{
				enumfacing = EnumFacing.SOUTH;
			}
			else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag)
			{
				enumfacing = EnumFacing.NORTH;
			}
			else
			{
				boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
				boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

				if (enumfacing == EnumFacing.WEST && flag2 && !flag3)
				{
					enumfacing = EnumFacing.EAST;
				}
				else if (enumfacing == EnumFacing.EAST && flag3 && !flag2)
				{
					enumfacing = EnumFacing.WEST;
				}
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing));
		}
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, facing);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | state.getValue(FACING).getIndex();
		return i;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	public static class DamageSourceSpike extends DamageSource
	{
		BlockSpike spike;

		public DamageSourceSpike(BlockSpike spike)
		{
			super("spike_damage");

			this.spike = spike;
		}

		public boolean dropXP()
		{
			return spike.dropsXP();
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
	{
		String desItems = ""+ TextFormatting.BLUE;
		if(destroysItems())
		{
			desItems += I18n.format("tile.mylittlemobgrinder.spike.destroyitems");
		}
		else
		{
			desItems += I18n.format("tile.mylittlemobgrinder.spike.dropitems");
		}
		tooltip.add(desItems);

		if(dropsXP())
		{
			tooltip.add(TextFormatting.BLUE + I18n.format("tile.mylittlemobgrinder.spike.dropxp"));
		}

		tooltip.add(I18n.format("tile.mylittlemobgrinder.spike.damage") + ": " + getDamage());
	}
}
