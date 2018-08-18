package party.lemons.mlmg.crafting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import party.lemons.mlmg.blocks.ModBlocks;
import party.lemons.mlmg.config.ModConstants;

/**
 * Created by Sam on 8/01/2018.
 */
public class GrinderTab extends CreativeTabs
{
	public static final GrinderTab TAB = new GrinderTab();

	public GrinderTab()
	{
		super(ModConstants.MODID);
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return  new ItemStack(ModBlocks.DIAMOND_SPIKE);
	}
}
