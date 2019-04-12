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

package com.ragegamingpe.shulkerinvent.common.te;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityShulkerBox;

import javax.annotation.Nullable;

public class DummyTileEntityShulkerBox extends TileEntityShulkerBox
{
    private ItemStack stack;

    public DummyTileEntityShulkerBox(@Nullable EnumDyeColor colorIn, ItemStack stack)
    {
        super(colorIn);
        this.stack = stack;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void markDirty()
    {
        if (!this.world.isRemote) {
            NBTTagCompound comp = stack.getTagCompound();
            NBTTagCompound data = new NBTTagCompound();

            if (comp != null && comp.hasKey("BlockEntityTag", 10)) {
                data = comp.getCompoundTag("BlockEntityTag");
            } else {
                stack.setTagCompound(comp = new NBTTagCompound());
                comp.setTag("BlockEntityTag", data);
            }

            comp.setTag("Items", ItemStackHelper.saveAllItems(data, this.getItems()));
        }
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }
}
