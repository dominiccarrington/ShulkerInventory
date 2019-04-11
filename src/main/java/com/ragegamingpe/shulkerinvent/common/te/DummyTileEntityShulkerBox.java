package com.ragegamingpe.shulkerinvent.common.te;

import com.ragegamingpe.shulkerinvent.common.ShulkerInvent;
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
        ShulkerInvent.logger.info(!this.world.isRemote);
        if (!this.world.isRemote) {
            NBTTagCompound comp = stack.getTagCompound();
            NBTTagCompound data = new NBTTagCompound();

            if (comp != null && comp.hasKey("BlockEntityTag", 10)) {
                data = comp.getCompoundTag("BlockEntityTag");
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
