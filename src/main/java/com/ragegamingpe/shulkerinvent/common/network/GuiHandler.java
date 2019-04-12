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

package com.ragegamingpe.shulkerinvent.common.network;

import com.ragegamingpe.shulkerinvent.client.gui.container.GuiContainerShulkerBoxInventory;
import com.ragegamingpe.shulkerinvent.common.container.ContainerShulkerBoxInventory;
import com.ragegamingpe.shulkerinvent.common.te.DummyTileEntityShulkerBox;
import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler
{
    public static final int SHULKER_BOX = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        switch (i) {
            case SHULKER_BOX:
                Slot slot = entityPlayer.openContainer.getSlot(x);
                return new ContainerShulkerBoxInventory(entityPlayer, slot, createShuklerBoxInventory(entityPlayer, slot.getStack()));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        switch (i) {
            case SHULKER_BOX:
                Slot slot = entityPlayer.openContainer.getSlot(x);
                return new GuiContainerShulkerBoxInventory(entityPlayer.inventory, slot, createShuklerBoxInventory(entityPlayer, slot.getStack()));
        }
        return null;
    }

    private IInventory createShuklerBoxInventory(EntityPlayer player, ItemStack stack)
    {
        TileEntityShulkerBox box = new DummyTileEntityShulkerBox(
                BlockShulkerBox.getColorFromBlock(Block.getBlockFromItem(stack.getItem())),
                stack
        );
        box.setWorld(player.getEntityWorld());

        NBTTagCompound nbttagcompound = stack.getTagCompound();

        NBTTagCompound data = new NBTTagCompound();

        if (nbttagcompound != null && nbttagcompound.hasKey("BlockEntityTag", 10)) {
            data = nbttagcompound.getCompoundTag("BlockEntityTag");
        }

        box.readFromNBT(data);

        return box;
    }
}
