package com.ragegamingpe.shulkerinvent.common;

import com.ragegamingpe.core.common.network.MessageHandler;
import com.ragegamingpe.shulkerinvent.common.network.GuiHandler;
import com.ragegamingpe.shulkerinvent.common.network.message.SlotMessage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
    protected static boolean registeredEvents = false;

    public void preInit(FMLPreInitializationEvent event)
    {
        if (!registeredEvents) {
            MinecraftForge.EVENT_BUS.register(this);
            registeredEvents = true;
        }

        NetworkRegistry.INSTANCE.registerGuiHandler(ShulkerInvent.instance, new GuiHandler());
        MessageHandler.register(SlotMessage.class, Side.SERVER);
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public void onServerStarting(FMLServerStartingEvent event)
    {
    }
}
