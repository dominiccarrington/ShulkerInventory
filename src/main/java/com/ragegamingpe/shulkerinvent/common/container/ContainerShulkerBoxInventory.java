package com.ragegamingpe.shulkerinvent.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotShulkerBox;
import net.minecraft.item.ItemStack;

public class ContainerShulkerBoxInventory extends Container
{
    private final IInventory inventory;

    public ContainerShulkerBoxInventory(EntityPlayer player, Slot slot, IInventory shulker)
    {
        this.inventory = shulker;
        shulker.openInventory(player);

        int j1;
        int k1;
        for (j1 = 0; j1 < 3; ++j1) {
            for (k1 = 0; k1 < 9; ++k1) {
                this.addSlotToContainer(new SlotShulkerBox(shulker, k1 + j1 * 9, 8 + k1 * 18, 18 + j1 * 18));
            }
        }

        for (j1 = 0; j1 < 3; ++j1) {
            for (k1 = 0; k1 < 9; ++k1) {
                this.addSlotToContainer(new Slot(player.inventory, k1 + j1 * 9 + 9, 8 + k1 * 18, 84 + j1 * 18)
                {
                    @Override
                    public boolean canTakeStack(EntityPlayer playerIn)
                    {
                        return !(this.isSameInventory(slot) && this.getSlotIndex() == slot.getSlotIndex());
                    }
                });
            }
        }

        for (j1 = 0; j1 < 9; ++j1) {
            this.addSlotToContainer(new Slot(player.inventory, j1, 8 + j1 * 18, 142)
            {
                @Override
                public boolean canTakeStack(EntityPlayer playerIn)
                {
                    return !(this.isSameInventory(slot) && this.getSlotIndex() == slot.getSlotIndex());
                }
            });
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.inventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }
}
