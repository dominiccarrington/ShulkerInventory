package com.ragegamingpe.shulkerinvent.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ShulkerInvent.MODID, name = ShulkerInvent.NAME, version = ShulkerInvent.VERSION)
public class ShulkerInvent
{
    public static final String MODID = "shulkerinvent";
    public static final String NAME = "Shulker Invent";
    public static final String VERSION = "GRADLE:VERSION";

    @SidedProxy(clientSide = "com.ragegamingpe.shulkerinvent.client.ClientProxy", serverSide = "com.ragegamingpe.shulkerinvent.common.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}
