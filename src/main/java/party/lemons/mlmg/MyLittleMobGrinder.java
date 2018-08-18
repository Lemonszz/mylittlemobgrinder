package party.lemons.mlmg;

import net.minecraftforge.fml.common.Mod;
import party.lemons.mlmg.config.ModConstants;

/**
 * Created by Sam on 8/01/2018.
 */
@Mod(modid = ModConstants.MODID, name = ModConstants.NAME, version = ModConstants.VERSION)
public class MyLittleMobGrinder
{
	@Mod.Instance(ModConstants.MODID)
	public static MyLittleMobGrinder instance;
}
