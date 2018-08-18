package party.lemons.mlmg.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import party.lemons.mlmg.blocks.BlockSpike;
import party.lemons.mlmg.config.ModConstants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Sam on 9/01/2018.
 */
@Mod.EventBusSubscriber
public class GeneralEventHandler
{
	@SubscribeEvent
	public static void onDrops(LivingDropsEvent event)
	{
		Entity entity = event.getEntity();

		if(!entity.world.isRemote && event.getEntityLiving() != null && !(event.getEntity() instanceof EntityPlayer))
		{
			if(!event.isRecentlyHit() && event.getSource()  instanceof BlockSpike.DamageSourceSpike)
			{
				BlockSpike.DamageSourceSpike ss = (BlockSpike.DamageSourceSpike) event.getSource();
				if(ss.dropXP())
				{
					try
					{
						int xp = (Integer) getExperienceMethod.invoke(event.getEntityLiving(), FakePlayerFactory.getMinecraft((WorldServer) entity.world));
						if(xp > 0)
						{
							while(xp > 0)
							{
								int split = EntityXPOrb.getXPSplit(xp);
								xp -= split;

								EntityXPOrb orb = new EntityXPOrb(entity.world, entity.posX, entity.posY, entity.posZ, split);
								event.getEntity().world.spawnEntity(orb);
							}
						}

					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	//reflection bs
	public static final Method getExperienceMethod = getExperiencePoints();
	public static Method getExperiencePoints()
	{
		Method met = ReflectionHelper.findMethod(EntityLiving.class, "getExperiencePoints", "func_70693_a",EntityPlayer.class);
		met.setAccessible(true);
		return met;
	}
}
