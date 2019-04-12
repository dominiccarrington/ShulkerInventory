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

package com.ragegamingpe.shulkerinvent.common.network.message;

import com.ragegamingpe.shulkerinvent.common.ShulkerInvent;
import com.ragegamingpe.shulkerinvent.common.network.GuiHandler;
import com.ragegamingpe.shulkerinvent.common.network.Message;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SlotMessage extends Message
{
    private final Block[] SHULKER_BOXES = new Block[]{
            Blocks.WHITE_SHULKER_BOX,
            Blocks.ORANGE_SHULKER_BOX,
            Blocks.MAGENTA_SHULKER_BOX,
            Blocks.LIGHT_BLUE_SHULKER_BOX,
            Blocks.YELLOW_SHULKER_BOX,
            Blocks.LIME_SHULKER_BOX,
            Blocks.PINK_SHULKER_BOX,
            Blocks.GRAY_SHULKER_BOX,
            Blocks.SILVER_SHULKER_BOX,
            Blocks.CYAN_SHULKER_BOX,
            Blocks.PURPLE_SHULKER_BOX,
            Blocks.BLUE_SHULKER_BOX,
            Blocks.BROWN_SHULKER_BOX,
            Blocks.GREEN_SHULKER_BOX,
            Blocks.RED_SHULKER_BOX,
            Blocks.BLACK_SHULKER_BOX
    };

    public int slot;

    public SlotMessage()
    {

    }

    public SlotMessage(int slot)
    {
        this.slot = slot;
    }

    @Override
    public void handleMessage(MessageContext context)
    {
        EntityPlayerMP player = context.getServerHandler().player;
        Slot slot = player.openContainer.getSlot(this.slot);
        ItemStack stack = null;

        for (Block shulkerbox : SHULKER_BOXES) {
            if (slot.getStack().getItem() == Item.getItemFromBlock(shulkerbox)) {
                stack = slot.getStack();
                break;
            }
        }

        if (stack != null) {
            player.openGui(ShulkerInvent.instance, GuiHandler.SHULKER_BOX, player.world, this.slot, 0, 0);
        }
    }
}
