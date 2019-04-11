package com.ragegamingpe.shulkerinvent.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("RGPE_ModdingCore");

    private static int i = 0;

    @SuppressWarnings("unchecked")
    public static void register(Class clazz, Side handlerSide)
    {
        INSTANCE.registerMessage(clazz, clazz, i++, handlerSide);
    }
}
