package party.lemons.mlmg.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Sam on 22/01/2018.
 */
@Mod.EventBusSubscriber
public class ModConfig
{
	@SubscribeEvent
	public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(ModConstants.MODID))
		{
			ConfigManager.sync(ModConstants.MODID, Config.Type.INSTANCE);
		}
	}

	@Config(modid = ModConstants.MODID)
	public static class GameplayConfig
	{
		@Config.RangeDouble(min = 0.001D)
		public static double speedBasicConveyor = 0.1D;

		@Config.RangeDouble(min = 0.001D)
		public static double speedAdvancedConveyor = 0.25D;

		@Config.RangeDouble(min = 0.001D)
		public static double speedExtremeConveyor = 1D;

		public static boolean ironSpikesDestroyItems = true;
		public static boolean diamondSpikesDestroyItems = false;

		public static boolean ironSpikesDropXP = true;
		public static boolean diamondSpikesDropXP = true;

		public static double damageIronSpike = 2;
		public static double damageDiamondSpike = 5;
	}
}
