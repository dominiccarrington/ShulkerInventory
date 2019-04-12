/*
 * Copyright (C) 2019 Dominic Carrington
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ragegamingpe.shulkerinvent.common;

import com.ragegamingpe.shulkerinvent.common.network.GuiHandler;
import com.ragegamingpe.shulkerinvent.common.network.MessageHandler;
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
